package com.example.sample1app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sample1app.model.TestTable;
import com.example.sample1app.repositories.TestTableRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.executable.ValidateOnExecution;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;


@Controller
public class HelloController {

    @Autowired
    TestTableRepository repository;

    @RequestMapping("/")
    public ModelAndView index(@ModelAttribute("formModel") TestTable TestTable, ModelAndView mav){
        mav.setViewName("index");
        mav.addObject("title", "Hello page");
        mav.addObject("msg", "This is JPA sample data.");
        List<TestTable> list = repository.findAll();
        mav.addObject("data", list);
        return mav;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    @Transactional
    public ModelAndView form(@ModelAttribute("formModel") @Validated TestTable TestTable,BindingResult result,ModelAndView mav){
        ModelAndView res = null;
        System.out.println(result.getFieldErrors());
        if(!result.hasErrors()){
            repository.saveAndFlush(TestTable);
            return new ModelAndView("redirect:/");
        } else {
            mav.setViewName("index");
            mav.addObject("title", "Hello page");
            mav.addObject("msg", "sorry, error is ocurred.");
            List<TestTable> list = repository.findAll();
            mav.addObject("data", list);
            res = mav;
        }
        return res;
    }

    @RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute TestTable TestTable, @PathVariable int id, ModelAndView mav){
        mav.setViewName("edit");
        mav.addObject("title", "edit Test Table");
        Optional<TestTable> data = repository.findById((int)id);
        mav.addObject("formModel",data.get());
        return mav;
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @Transactional
    public ModelAndView update(@ModelAttribute TestTable TestTable, ModelAndView mav){
        repository.saveAndFlush(TestTable);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id, ModelAndView mav){
        mav.setViewName("delete");
        mav.addObject("title","Delete Test Table");
        mav.addObject("msg","Can I delete this record?");
        Optional<TestTable> data = repository.findById(id);
        mav.addObject("formModel",data.get());
        return mav;
    }

    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @Transactional
    public ModelAndView remove(@RequestParam int id,ModelAndView mav){
        repository.deleteById(id);
        return new ModelAndView("redirect:/");
    }
    
}