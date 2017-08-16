/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gigatronik.ebikediagnose.controllers;

import com.gigatronik.ebikediagnose.model.EBike;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gigatronik.ebikediagnose.model.EBikeRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author sheiv
 */
@Controller
public class MainController {

    @Autowired
    private EBikeRepository bikeRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(Map<String, Object> model) {

        model.put("message", "this is the message");
        return "index";

    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String storeData(HttpServletRequest request) {

   
        return "hello";
    }

    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public String showValues(HttpServletRequest request, Model model) {
        int i = 0 ;
        List<EBike> bikes = bikeRepository.findAll();
        
        for (EBike eBike:bikes) {
            System.out.println("****"+bikes.get(i).getId());
            System.out.println(bikes.get(i).getDisplay());
            System.out.println(bikes.get(i).getBattery());
            System.out.println(bikes.get(i).getMotor());
            i++;
        }

        model.addAttribute("Ebikes", bikes);



        return "text";
    }

    @RequestMapping(value = "/hello" , method = RequestMethod.POST)
    public String addDevice(HttpServletRequest request,Model model){

        String address = request.getParameter("address");
        RestTemplate restTemplate = new RestTemplate();
        String ipAdress = "http://"+address+":8088/ecus/display";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(ipAdress, String.class);
        EBike bike = new EBike(responseEntity.getBody().substring(0, 250),"motor","battery");

        System.out.println("hello get has been executed ..");

        bikeRepository.save(bike);
        System.out.println("==="+address);
        return "hello";
    }

}
