package com.teamtreehouse.courses.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MitchM on 12/15/2017.
 */
public class SimpleCourseIdeaDAO implements CourseIdeaDAO
{
   private List<CourseIdea> ideas;

   public SimpleCourseIdeaDAO()
   {
      ideas = new ArrayList<>(  );
   }

   @Override
   public boolean add( CourseIdea courseIdea )
   {
      return ideas.add( courseIdea );
   }

   @Override
   public List<CourseIdea> finalAll()
   {
      return new ArrayList<>( ideas );
   }

   @Override
   public CourseIdea findBySlug( String slug )
   {
      return ideas.stream()
            .filter( idea -> idea.getSlug().equals( slug ))
            .findFirst()
            .orElseThrow( NotFoundException::new );
   }
}
