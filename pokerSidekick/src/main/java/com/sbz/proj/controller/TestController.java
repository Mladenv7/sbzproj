package com.sbz.proj.controller;

import com.sbz.proj.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    private TestService testService;


    @GetMapping("/dobioKeca")
    public void MOJAMETODA(){
        testService.nistaSeTiNeSekeraj();
    }

    @GetMapping("/poredimo/{daProdje}")
    public void TVOJAMETODA(@PathVariable boolean daProdje){
        //testService.poredimo2Karte(daProdje);
        testService.checkRoyalFlush();
    }
}
