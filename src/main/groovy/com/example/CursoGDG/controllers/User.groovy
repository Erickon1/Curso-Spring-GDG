package com.example.CursoGDG.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
//import org.springframework.w

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@RestController
//@Controller
@Configuration
public class HelloController extends WebMvcConfigurerAdapter {
		@RequestMapping("/")
    public String index() {
        //return "index"
        return "Greetings from Spring Boot!";
    }
    
    /*
    @RequestMapping("/home")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model ) {
        model.addAttribute("name", name);
        return "home.html"
    }
    */

    @RequestMapping("/login")
    public String login() {
        return "Este es /login";
    }
    
		@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index")
        registry.addViewController("/home").setViewName("home")
       
    }

}
