package com.sbz.proj.service;

import com.sbz.proj.model.*;
import com.sbz.proj.util.KnowledgeSessionHelper;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

@Service
public class CurrentStateService {
    private final static java.util.logging.Logger LOGGER = Logger.getLogger(CurrentStateService.class.getName());

    private final KieContainer kieContainer;

    @Autowired
    public CurrentStateService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public Action consult(TableState ts) {
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");
        kieSession.insert(ts);

        Possibility p = new Possibility();
        p.cards = new ArrayList<>();
        p.cards.add(ts.getPlayers().get(0).getCard1());
        p.cards.add(ts.getPlayers().get(0).getCard2());
        p.cards.addAll(ts.getBoard());

        p.setupCards();

        kieSession.insert(p);

        kieSession.getAgenda().getAgendaGroup("pre-flop").setFocus();

        int fired = kieSession.fireAllRules();
        LOGGER.info("Number of fired rules: " + fired);

        return Action.CHECK;
    }

    public void parseCurrentState(HashMap<String, String> currentStateMap){
        // boilerpalte
        System.out.println("Pravimo novi kieSession");
        KieSession kieSession = kieContainer.newKieSession();

        // actual code
        StageName.valueOf(currentStateMap.get("stageName"));

        Player p1 = new Player();

        System.out.println(this.kieContainer);

        //p1.setCard1(new Card(1, Suit.SPADES));
        //p1.setCard2(new Card(1, Suit.HEARTS));

        // insert data for rules
        kieSession.insert(p1);

        // boilerpalte
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println("Gasimo kieSession");
        System.out.println(p1.getMoney());

    }
}
