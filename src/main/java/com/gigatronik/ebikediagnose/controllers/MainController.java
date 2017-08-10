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

    /**
     * URL will be passed as a parameter from the user interface and dynamically
     * can call the rest api in this method
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public String storeData(HttpServletRequest request, Model model) {

        String name = request.getParameter("ipAddress");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8088/ecus/display", String.class);
        EBike bike = new EBike(responseEntity.getBody().substring(0, 240));
        bike.setBattery("batteryyyyyy");
        bike.setMotor("motor");
        System.out.println("==" + responseEntity.getBody());
        bikeRepository.save(bike);
        model.addAttribute("name", bike);

        return "hello";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String showValues(HttpServletRequest request, Model model) {

        List<EBike> bikes = bikeRepository.findAll();
        model.addAttribute("Ebikes", bikes);
        System.out.println("**" + bikes.get(0).getDisplay());
        System.out.println("**" + bikes.get(0).getMotor());
        return "hello";
    }

}
