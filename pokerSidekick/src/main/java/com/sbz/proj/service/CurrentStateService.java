package com.sbz.proj.service;

import com.sbz.proj.model.*;
import com.sbz.proj.util.KnowledgeSessionHelper;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CurrentStateService {
    private final static java.util.logging.Logger LOGGER = Logger.getLogger(CurrentStateService.class.getName());

    @Autowired
    private TestService testService;

    private final KieContainer kieContainer;

    @Autowired
    public CurrentStateService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public Action consult(TableState ts) {
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");
        kieSession.insert(ts);

        switch(ts.getCurrentStage()){
            case PRE_FLOP:
                kieSession.getAgenda().getAgendaGroup("pre-flop").setFocus();
                break;
            case FLOP:
                Possibility p = new Possibility();
                p.cards.add(ts.getPlayers().get(0).getCard1());
                p.cards.add(ts.getPlayers().get(0).getCard2());
                p.cards.addAll(ts.getBoard());

                p.setupCards();

                Card.logCards(LOGGER, p.getCards());

                kieSession.insert(p);


                break;
            case TURN:
                List<Card> allCards = new ArrayList<>(ts.board);
                allCards.add(ts.getPlayers().get(0).getCard1());
                allCards.add(ts.getPlayers().get(0).getCard2());
                List<Possibility>  possibilities = Possibility.makeAllPossibilities(allCards);

                for (Possibility po : possibilities) {
                    Card.logCards(LOGGER, po.getCards());
                    kieSession.insert(po);
                }
                break;
            case RIVER:
                allCards = new ArrayList<>(ts.board);
                allCards.add(ts.getPlayers().get(0).getCard1());
                allCards.add(ts.getPlayers().get(0).getCard2());
                possibilities = Possibility.makeAllPossibilities(allCards);

                for (Possibility po : possibilities) {
                    Card.logCards(LOGGER, po.getCards());
                    kieSession.insert(po);
                }
                break;
        }

        kieSession.getAgenda().getAgendaGroup("possibility").setFocus();
        int fired = kieSession.fireAllRules();
        LOGGER.info("Number of fired rules: " + fired);

        return Action.CHECK;
    }

    private void destroySession(KieSession kieSession) {
        kieSession.dispose();
        System.out.println("Gasimo kieSession");
    }


    public Action flopConsult(TableState ts) {
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");

        Possibility p = new Possibility();
        p.cards.add(ts.getPlayers().get(0).getCard1());
        p.cards.add(ts.getPlayers().get(0).getCard2());
        p.cards.addAll(ts.getBoard());

        p.setupCards();

        Card.logCards(LOGGER, p.getCards());

        kieSession.insert(ts);
        kieSession.insert(p);

        kieSession.getAgenda().getAgendaGroup("possibility").setFocus();

        int fired = kieSession.fireAllRules();
        LOGGER.info("Number of fired rules: " + fired);

        return ts.getPlayers().get(0).getAction().get(0);
    }

    public void getAllPossibilitiesAndInsertIntoSession(TableState ts, KieSession kieSession) {
        List<Card> allCards = new ArrayList<>(ts.board);
        allCards.add(ts.getPlayers().get(0).getCard1());
        allCards.add(ts.getPlayers().get(0).getCard2());
        List<Possibility>  possibilities = Possibility.makeAllPossibilities(allCards);

        for (Possibility p : possibilities) {
            LOGGER.info("\u001B[33m==========================================");
            LOGGER.info("CARDS: " + p.getCards().toString());
            LOGGER.info("-  -   -   -   -   -   -   -   -   -   -");
            kieSession.insert(p);
        }

        testService.checkWhatWeHaveList(possibilities);
    }

    public Action consultWithPossibility(TableState ts) {
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");

        List<Card> allCards = new ArrayList<>(ts.board);
        allCards.add(ts.getPlayers().get(0).getCard1());
        allCards.add(ts.getPlayers().get(0).getCard2());
        List<Possibility>  possibilities = Possibility.makeAllPossibilities(allCards);

        for (Possibility p : possibilities) {
            LOGGER.info("\u001B[33m==========================================");
            LOGGER.info("CARDS: " + p.getCards().toString());
            LOGGER.info("-  -   -   -   -   -   -   -   -   -   -");
            kieSession.insert(p);
        }

        kieSession.insert(ts);

        kieSession.getAgenda().getAgendaGroup("possibility").setFocus();

        int fired = kieSession.fireAllRules();
        LOGGER.info("Number of fired rules: " + fired);

        return ts.getPlayers().get(0).getAction().get(0);
    }
}
