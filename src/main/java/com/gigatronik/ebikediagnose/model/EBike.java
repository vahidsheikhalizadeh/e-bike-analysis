/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gigatronik.ebikediagnose.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sheiv
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "EBike")
public class EBike implements Serializable {

    //no-argument constructor 
    public EBike() {
    }

    public EBike(String display, String motor, String battery) {

        this.display = display ;
        this.motor = motor ;
        this.battery = battery;

    }
    public EBike (String display){
    
    this.display = display ;
    }

    @Id
    @Column
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String display;
    @Column
    private String motor;
    @Column
    private String battery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

  


}
