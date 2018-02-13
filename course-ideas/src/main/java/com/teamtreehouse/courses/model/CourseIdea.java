package com.teamtreehouse.courses.model;

import com.github.slugify.Slugify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseIdea
{
   private String slug;
   private String title;
   private String creator;
   private Set<String> voters;

   public CourseIdea( String title, String creator )
   {
      voters = new HashSet<>(  );
      this.title = title;
      this.creator = creator;
      Slugify slugify = new Slugify(  );
      slug = slugify.slugify( title );
   }

   @Override
   public boolean equals( Object o )
   {
      if ( this == o )
      {
         return true;
      }
      if ( o == null || getClass() != o.getClass() )
      {
         return false;
      }

      CourseIdea that = (CourseIdea) o;

      if ( getTitle() != null ? !getTitle().equals( that.getTitle() ) : that.getTitle() != null )
      {
         return false;
      }
      return getCreator() != null ? getCreator().equals( that.getCreator() ) : that.getCreator() == null;

   }

   @Override
   public int hashCode()
   {
      int result = getTitle() != null ? getTitle().hashCode() : 0;
      result = 31 * result + ( getCreator() != null ? getCreator().hashCode() : 0 );
      return result;
   }

   public boolean addVoter( String voterUserName )
   {
      return voters.add( voterUserName );
   }

   public List<String> getVoters()
   {
      return new ArrayList<>( voters );
   }

   public int getVoteCount()
   {
      return voters.size();
   }

   public String getTitle()
   {
      return title;
   }

   public String getCreator()
   {
      return creator;
   }

   public String getSlug()
   {
      return slug;
   }
}
