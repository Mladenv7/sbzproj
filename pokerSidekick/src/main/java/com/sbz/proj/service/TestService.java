package com.sbz.proj.service;


import com.sbz.proj.model.*;
import com.sbz.proj.util.DebugAgendaEventListener;
import com.sbz.proj.util.KnowledgeSessionHelper;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class TestService {

    private final static java.util.logging.Logger LOGGER = Logger.getLogger(TestService.class.getName());

    private final KieContainer kieContainer;

    @Autowired
    public TestService(KieContainer kieContainer) {
        LOGGER.info("Initialising a new example session.");
        this.kieContainer = kieContainer;
    }

    public void nistaSeTiNeSekeraj(){
        //KieSession kieSession = kieContainer.newKieSession();
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");
        //StatelessKieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");
        //kieSession.addEventListener(new DebugAgendaEventListener());

        //kieSession.setGlobal("LOGGER", LOGGER);

        Player p1 = new Player();

        //System.out.println(this.kieContainer);

        p1.setCard1(new Card(1, Suit.SPADES));
        p1.setCard2(new Card(1, Suit.HEARTS));

        TableState ts1 = new TableState();
        ts1.setCurrentStage(StageName.PRE_FLOP);

        kieSession.insert(p1);
//        kieSession.insert(ts1);
        int fired = kieSession.fireAllRules();
        //kieSession.dispose();

        LOGGER.info("Number of fired rules: " + fired);

    }
}
