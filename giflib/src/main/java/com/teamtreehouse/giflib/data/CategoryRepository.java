package com.teamtreehouse.giflib.data;

import com.teamtreehouse.giflib.model.Category;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class CategoryRepository
{
    private static final List<Category> ALL_CATEGORIES =  Arrays.asList(
            new  Category(1, "Sports"),
            new  Category(2, "Politics"),
            new  Category(3, "Religion") );

    public List<Category> getAllCategories()
    {
        return ALL_CATEGORIES;
    }

    public Category findById( int id )
    {
        for( Category category : ALL_CATEGORIES )
        {
            if( category.getId() == id )
            {
                return category;
            }
        }

        return null;
    }
}
