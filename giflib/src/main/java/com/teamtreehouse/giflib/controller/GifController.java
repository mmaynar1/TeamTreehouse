package com.teamtreehouse.giflib.controller;

import com.teamtreehouse.giflib.data.GifRepository;
import com.teamtreehouse.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GifController
{
    @Autowired
    private GifRepository gifRepository;

    @RequestMapping( value = "/" )
    public String listGifs()
    {
        return "home"; //Thymeleaf will resolve this to the html file
    }

    @RequestMapping( value = "/gif" )
    public String gifDetails( ModelMap modelMap )
    {
        Gif gif = gifRepository.findByName("android-explosion");
        modelMap.put( "gif", gif );
        return "gif-details"; //Thymeleaf will resolve this to the html file
    }
}
