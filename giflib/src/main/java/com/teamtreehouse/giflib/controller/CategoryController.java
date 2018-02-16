package com.teamtreehouse.giflib.controller;

import com.teamtreehouse.giflib.data.CategoryRepository;
import com.teamtreehouse.giflib.data.GifRepository;
import com.teamtreehouse.giflib.model.Category;
import com.teamtreehouse.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CategoryController
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GifRepository gifRepository;

    @RequestMapping( value = "/categories" )
    public String getCategories( ModelMap modelMap )
    {
        List<Category> categories = categoryRepository.getAllCategories();
        modelMap.put( "categories", categories );
        return "categories"; //Thymeleaf will resolve this to the html file
    }

    @RequestMapping( value = "/category/{id}" )
    public String getCategory( @PathVariable int id,  ModelMap modelMap )
    {
        Category category = categoryRepository.findById(id);
        modelMap.put( "category", category );

        List<Gif> gifs = gifRepository.findByCategoryId( id );
        modelMap.put("gifs", gifs);
        return "category"; //Thymeleaf will resolve this to the html file
    }
}
