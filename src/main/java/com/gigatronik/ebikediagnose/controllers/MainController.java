/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gigatronik.ebikediagnose.controllers;

import com.gigatronik.ebikediagnose.model.PersonRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author sheiv
 */
@RestController
public class MainController {
    
    @Autowired
    private PersonRepository personRepository;
    
    @GetMapping("/")
    public String test(){
        
    return "hello world!";
    
    }
    
    
    @GetMapping(value="/hello")
    public String storeData(HttpServletRequest request , Model model){
    
    String name = request.getParameter("name");
    
    if(name == null || name == ""){
        name = "world";
    }
    
    model.addAttribute("firstName", name);
    return "hello.html";
    }
    
}
