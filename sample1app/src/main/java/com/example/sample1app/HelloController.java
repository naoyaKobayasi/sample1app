package com.example.sample1app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.example.sample1app.model.TestTable;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.sample1app.repositories.TestTableRepository;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Optional;



@Controller
public class HelloController {

    @Autowired
    TestTableRepository repository;

    @RequestMapping("/")
    public ModelAndView index(@ModelAttribute("formModel") TestTable TestTable, ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("title", "Hello Page");
        mav.addObject("msg", "This is JPA sample data.");
        List<TestTable> list = repository.findAll();
        mav.addObject("data", list);
        return mav;
    }

    @RequestMapping(value = "/", method=RequestMethod.POST)
    @Transactional
    public ModelAndView form(@ModelAttribute("formModel") TestTable TestTable, ModelAndView mav) {
        repository.saveAndFlush(TestTable);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute("formModel") TestTable TestTable, @PathVariable int id, ModelAndView mav){
        mav.setViewName("edit");
        mav.addObject("title", "Edit Test Table");
        Optional<TestTable> data = repository.findById(id);
        mav.addObject("formModel", data.get());
        return mav;
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @Transactional
    public ModelAndView update(@ModelAttribute("formModel") TestTable TestTable,ModelAndView mav){
        repository.saveAndFlush(TestTable);
        return new ModelAndView("redirect:/");
    }
    
}