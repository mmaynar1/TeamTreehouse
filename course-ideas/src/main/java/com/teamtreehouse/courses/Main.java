package com.teamtreehouse.courses;

import com.teamtreehouse.courses.model.CourseIdea;
import com.teamtreehouse.courses.model.CourseIdeaDAO;
import com.teamtreehouse.courses.model.NotFoundException;
import com.teamtreehouse.courses.model.SimpleCourseIdeaDAO;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

/**
 * Created by MitchM on 12/15/2017.
 */
public class Main
{
   private static final String FLASH_MESSAGE_KEY = "flash_message";
   private static final String MODEL = "model";

   public static void main( String[] args )
   {
      staticFileLocation( "/public" );
      CourseIdeaDAO dao = new SimpleCourseIdeaDAO();

      before((req, res) -> {
         if( req.cookie( "username" ) != null )
         {
            req.attribute( "username", req.cookie( "username" ) );
         }

         Map<String, Object> model = new HashMap<>(  );

         model.put( "flashMessage", captureFlashMessage( req ) );
         req.attribute( MODEL, model );
      });

      before("/ideas", (req, res) ->{
         if( req.attribute( "username" ) == null )
         {
            setFlashMessage( req, "Whoops, please sign in first!" );
            res.redirect( "/" );
            halt();
         }
      });

      get( "/", ( req, res ) ->
      {
         Map<String,Object> model = req.attribute( MODEL );
         model.put( "username", req.attribute( "username" ) );
         return new ModelAndView( model, "index.hbs" );
      }, new HandlebarsTemplateEngine() );

      post( "/sign-in", ( req, res ) ->
      {
         Map<String,Object> model = req.attribute( MODEL );
         String username = req.queryParams( "username" );
         res.cookie( "username", username );
         model.put( "username", username );
         res.redirect( "/" );
         return null;
      } );

      get( "/ideas", ( req, res ) ->
      {
         Map<String,Object> model = req.attribute( MODEL );
         model.put( "ideas", dao.finalAll());
         return new ModelAndView( model, "ideas.hbs" );
      }, new HandlebarsTemplateEngine() );

      post( "/ideas", ( req, res ) ->
      {
         String title = req.queryParams( "title" );
         CourseIdea courseIdea = new CourseIdea( title, req.attribute( "username" ) );
         dao.add( courseIdea );
         res.redirect( "/ideas" );
         return null;
      } );

      post( "/ideas/:slug/vote", ( req, res ) ->
      {
         CourseIdea courseIdea = dao.findBySlug( req.params( "slug" ) );
         boolean added = courseIdea.addVoter( req.attribute( "username" ) );
         if( added )
         {
            setFlashMessage( req, "Thanks for your vote!");
         }
         else
         {
            setFlashMessage( req, "You already voted!" );
         }
         res.redirect( "/ideas" );
         return null;
      } );

      get( "/ideas/:slug", ( req, res ) ->
      {
         Map<String,Object> model = req.attribute( MODEL );
         CourseIdea courseIdea = dao.findBySlug( req.params( "slug" ) );
         model.put( "courseIdea", courseIdea );
         return new ModelAndView( model, "idea.hbs" );
      }, new HandlebarsTemplateEngine(  ));

      exception( NotFoundException.class, (exc, req, res) ->{
         res.status( 404 );
         HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine(  );
         String html = engine.render( new ModelAndView( null, "not-found.hbs" ) );
         res.body(html);
      } );
   }

   private static void setFlashMessage( Request request, String message )
   {
      request.session().attribute( FLASH_MESSAGE_KEY, message );
   }

   private static String getFlashMessage( Request request )
   {
      if( request.session(false) == null )
      {
         return  null;
      }

      if( !request.session().attributes().contains( FLASH_MESSAGE_KEY ))
      {
         return null;
      }

      return (String) request.session().attribute( FLASH_MESSAGE_KEY );
   }

   private static String captureFlashMessage( Request request )
   {
      String message = getFlashMessage( request );
      if( message != null )
      {
         request.session().removeAttribute( FLASH_MESSAGE_KEY );
      }
      return message;
   }

}
