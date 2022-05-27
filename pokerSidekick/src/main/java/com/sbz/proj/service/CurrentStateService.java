package com.sbz.proj.service;

import com.sbz.proj.model.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.TabableView;
import java.util.HashMap;

@Service
public class CurrentStateService {
    private final KieContainer kieContainer;

    @Autowired
    public CurrentStateService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    private KieSession createSession(String sessionName) {
        System.out.println("Pravimo novi kieSession");
        KieSession kieSession = kieContainer.newKieSession();
        return kieSession;
    }

    private void destroySession(KieSession kieSession) {
        kieSession.dispose();
        System.out.println("Gasimo kieSession");
    }



    public void parseCurrentState(HashMap<String, String> currentStateMap){
        // boilerpalte
        System.out.println("Pravimo novi kieSession");
        KieSession kieSession = kieContainer.newKieSession();

        // actual code
        StageName.valueOf(currentStateMap.get("stageName"));

        Player p1 = new Player();

        System.out.println(this.kieContainer);

        p1.setCard1(new Card(1, Suit.SPADES));
        p1.setCard2(new Card(1, Suit.HEARTS));

        // insert data for rules
        kieSession.insert(p1);

        // boilerpalte
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println("Gasimo kieSession");
        System.out.println(p1.getMoney());

    }
}
