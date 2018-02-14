package com.teamtreehouse.giflib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GifController
{
    @RequestMapping( value = "/" )
    public String listGifs()
    {
        return "home"; //Thymeleaf will resolve this to the html file
    }
}
