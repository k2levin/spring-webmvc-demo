package com.k2studio.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index() {
        return "home";
    }

    @RequestMapping(value = "/hello")
    public ModelAndView hello(@RequestParam(required = false, defaultValue = "World") String name) {
        ModelAndView ret = new ModelAndView("home");
        // Adds an objet to be used in home.jsp
        ret.addObject("name", name);
        return ret;
    }

    @RequestMapping(value = "/hello2")
    public String hello2() {
        return "redirect:/hello";
    }

}
