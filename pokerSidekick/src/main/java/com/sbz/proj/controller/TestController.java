package com.sbz.proj.controller;

import com.sbz.proj.model.FiveCardsDTO;
import com.sbz.proj.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

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
//        try {
//            testService.checkStraigthFlush();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    @PostMapping("/saljem-karte")
    public void proveravamMogucnosti(@RequestBody FiveCardsDTO fiveCardsDTO) {
        testService.checkWhatWeHave(fiveCardsDTO);
    }
}
