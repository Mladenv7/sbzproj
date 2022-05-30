package com.sbz.proj.controller;

import com.sbz.proj.model.Action;
import com.sbz.proj.model.FiveCardsDTO;
import com.sbz.proj.model.Possibility;
import com.sbz.proj.model.TableState;
import com.sbz.proj.service.CurrentStateService;
import com.sbz.proj.service.TestService;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/current-state")
public class CurrentStateController {

    @Autowired
    private CurrentStateService currentStateService;

    @Autowired
    private TestService testService;

    /*
    * TODO - HINT: Ovo posalji u postman-u, menjaj karte i uloge kako os
    *  {
            "players": [
                {
                    "card1": {"rank": "ACE", "suit": "CLUBS"},
                    "card2": {"rank": "R3", "suit": "DIAMONDS"},
                    "action": [
                        "CHECK"
                    ],
                    "money": 10000,
                    "blind": "NONE"
                },
                {
                    "card1": null,
                    "card2": null,
                    "action": [
                        "CHECK"
                    ],
                    "money": 10000,
                    "blind": "BIG_BLIND"
                },
                {
                    "card1": null,
                    "card2": null,
                    "action": [
                        "CHECK"
                    ],
                    "money": 10000,
                    "blind": "SMALL_BLIND"
                },
                {
                    "card1": null,
                    "card2": null,
                    "action": [
                        "CHECK"
                    ],
                    "money": 10000,
                    "blind": "DEALER"
                },
                {
                    "card1": null,
                    "card2": null,
                    "action": [
                        "CHECK"
                    ],
                    "money": 10000,
                    "blind": "NONE"
                }
            ],
            "currentStage": "FLOP",
            "board": [
                {"rank": "KING", "suit": "DIAMONDS"},
                {"rank": "R10", "suit": "CLUBS"},
                {"rank": "R10", "suit": "HEARTS"}
            ]
        }
    * */

    @PostMapping("")
    public ResponseEntity<?> sendCurrentState(@RequestBody TableState currentState){
        if(currentState.getPlayers().size() < 2)
            return new ResponseEntity<String>("Table state needs a least two players with cards", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Action>(currentStateService.consult(currentState), HttpStatus.OK);
    }

    @PostMapping("sort-pos")
    public ResponseEntity<List<Possibility>> compare(@RequestBody List<Possibility> possibilities){
        testService.checkWhatWeHaveList(possibilities);
        return new ResponseEntity<List<Possibility>>(possibilities.stream().sorted(Possibility::compareTo).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/flop")
    public ResponseEntity<Action> sendCurrentStateFlop(@RequestBody TableState currentState){
        return new ResponseEntity<Action>(currentStateService.flopConslut(currentState), HttpStatus.OK);
    }

}
