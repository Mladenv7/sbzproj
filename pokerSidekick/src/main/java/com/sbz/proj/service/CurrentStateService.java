package com.sbz.proj.service;

import com.sbz.proj.model.*;
import com.sbz.proj.util.KnowledgeSessionHelper;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.TabableView;
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

    private KieSession createSession(String sessionName) {
        System.out.println("Pravimo novi kieSession");
        KieSession kieSession = kieContainer.newKieSession();
        return kieSession;
    }

    public Action consult(TableState ts) {
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");

        kieSession.insert(ts);
        if (ts.getCurrentStage() == StageName.RIVER) {
            Possibility p = new Possibility();
            p.cards = new ArrayList<>();
            p.cards.add(ts.getPlayers().get(0).getCard1());
            p.cards.add(ts.getPlayers().get(0).getCard2());
            p.cards.addAll(ts.getBoard());

            p.setupCards();

            kieSession.insert(p);
        }

        int fired = kieSession.fireAllRules();
        LOGGER.info("Number of fired rules: " + fired);

        return Action.CHECK;
    }

    private void destroySession(KieSession kieSession) {
        kieSession.dispose();
        System.out.println("Gasimo kieSession");
    }


    public Action flopConslut(TableState ts) {
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");

        Possibility p = new Possibility();
        p.cards.add(ts.getPlayers().get(0).getCard1());
        p.cards.add(ts.getPlayers().get(0).getCard2());
        p.cards.addAll(ts.getBoard());

        p.setupCards();

        LOGGER.info("CARDS: " + p.getCards().toString());

        kieSession.insert(ts);
        kieSession.insert(p);

        int fired = kieSession.fireAllRules();
        LOGGER.info("Number of fired rules: " + fired);

        return ts.getPlayers().get(0).getAction().get(ts.getPlayers().get(0).getAction().size() - 1); // vraca poslednju akciju koju smo ubacili u pravilu
    }
}
