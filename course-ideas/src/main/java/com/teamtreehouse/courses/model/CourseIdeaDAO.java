package com.teamtreehouse.courses.model;

import java.util.List;

/**
 * Created by MitchM on 12/15/2017.
 */
public interface CourseIdeaDAO
{
   boolean add( CourseIdea courseIdea );

   List<CourseIdea> finalAll();

   CourseIdea findBySlug( String slug );
}
