package com.sbz.proj.controller;

import com.sbz.proj.service.CurrentStateService;
import com.sbz.proj.service.TestService;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api/current-state")
public class CurrentStateController {

    @Autowired
    private CurrentStateService currentStateService;


    @PostMapping("/current-state")
    public void sendCurrentState(@RequestBody HashMap<String, String> currentStateMap){
        currentStateService.parseCurrentState(currentStateMap);
    }


}
