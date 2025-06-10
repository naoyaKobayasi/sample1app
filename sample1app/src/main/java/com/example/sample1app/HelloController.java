package com.example.sample1app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HelloController {

    private boolean flag = false;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav){
        flag = !flag;
        mav.setViewName("index");
        mav.addObject("flag",flag);
        mav.addObject("msg", "サンプルメッセージです。");
        return mav;
    }
    
}