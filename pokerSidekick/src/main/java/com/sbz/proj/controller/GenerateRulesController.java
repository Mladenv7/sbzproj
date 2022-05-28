package com.sbz.proj.controller;

import com.sbz.proj.service.GenerateRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/generate-rules")
public class GenerateRulesController {

    @Autowired
    GenerateRulesService generateRulesService;

    @GetMapping("/gen-and-save")
    public void genAndSave(@RequestParam String templatePath, @RequestParam String dataFilePath, @RequestParam String resultPath) {
        try {
            generateRulesService.generateRulesAndSaveThem(templatePath, dataFilePath, resultPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/just-gen")
    public ResponseEntity<String> justGen(@RequestParam String templatePath, @RequestParam String dataFilePath) {
        try {
            String drl = generateRulesService.generateRules(templatePath, dataFilePath);
            return new ResponseEntity<>(drl, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/just-save")
    public ResponseEntity<String> justSave(@RequestParam String drl, @RequestParam String filePath) {
        try {
            generateRulesService.saveRulesInFile(drl, filePath);
            return new ResponseEntity<>(filePath, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
