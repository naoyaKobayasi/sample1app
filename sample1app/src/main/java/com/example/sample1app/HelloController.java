package com.example.sample1app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController {

    @RequestMapping("/{num}")
    public ModelAndView index(ModelAndView mav,@PathVariable int num){
        mav.addObject("msg","This message in Controller.");
        mav.setViewName("index");
        return mav;
    }
    
}