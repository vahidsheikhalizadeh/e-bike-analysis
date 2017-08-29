/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gigatronik.ebikediagnose.controllers;

import com.gigatronik.ebikediagnose.model.EBike;
import com.gigatronik.ebikediagnose.model.EBikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author sheiv
 */
@Controller
public class MainController {

    @Autowired
    private EBikeRepository bikeRepository;
    List<EBike> bikes;


    //////////////////////////////////      root map             ///////////////////////////////////////
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(Map<String, Object> model) {

        model.put("message", "this is the message");
        return "index";

    }

    //////////////////////////////////        insert  data into db           ///////////////////////////////////////

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String storeData(HttpServletRequest request) {

        String name = request.getParameter("ipAddress");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://gturnquist-quoters.cfapps.io/api/random", String.class);
        EBike bike = new EBike(responseEntity.getBody(), "motor", "battery");

        System.out.println("hello get has been executed ..");

        bikeRepository.save(bike);


        return "hello";
    }

    //////////////////////////////////   read data from db                ///////////////////////////////////////

    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public String showValues(HttpServletRequest request, Model model) {
        int i = 0;
        bikes = bikeRepository.findAll();

        model.addAttribute("Ebikes", bikes);

        return "text";
    }

    //////////////////////////////////    add a new device               ///////////////////////////////////////

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String addDevice(HttpServletRequest request, Model model) {

        String address = request.getParameter("address");
        System.out.println("===" + address);
        return "hello";
    }
    //////////////////////////////////      REST API             ///////////////////////////////////////
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public ResponseEntity<List<EBike>> showDevices() {

        bikes = bikeRepository.findAll();


        if (bikes.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<>(bikes, HttpStatus.OK);
    }

}
