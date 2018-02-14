package com.teamtreehouse.giflib.controller;

import com.teamtreehouse.giflib.data.GifRepository;
import com.teamtreehouse.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GifController
{
    @Autowired
    private GifRepository gifRepository;

    @RequestMapping( value = "/" )
    public String listGifs(ModelMap modelMap)
    {
        List<Gif> allGifs = gifRepository.getAllGifs();
        modelMap.put("gifs", allGifs);
        return "home"; //Thymeleaf will resolve this to the html file
    }

    @RequestMapping( value = "/gif/{name}" )
    public String gifDetails( @PathVariable String name, ModelMap modelMap )
    {
        Gif gif = gifRepository.findByName(name);
        modelMap.put( "gif", gif );
        return "gif-details"; //Thymeleaf will resolve this to the html file
    }
}
