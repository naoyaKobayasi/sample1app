package com.example.sample1app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HelloController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav){
        
        mav.setViewName("index");

        String[] data = new String[]{"One","two","Three"};

        mav.addObject("data",data);

        return mav;
    }
    
}