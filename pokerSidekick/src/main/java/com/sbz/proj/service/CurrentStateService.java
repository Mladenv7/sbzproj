package com.sbz.proj.service;

import com.sbz.proj.model.*;
import com.sbz.proj.model.events.StageEvent;
import com.sbz.proj.util.DebugAgendaEventListener;
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

    private final KieSession kieSession;

    @Autowired
    public CurrentStateService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
        this.kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");
    }

    public ConsultResultDTO consult(TableState ts) {

        kieSession.insert(ts);

        switch(ts.getCurrentStage()){
            case PRE_FLOP:
                kieSession.insert(new StageEvent(StageName.PRE_FLOP));
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
                kieSession.insert(new StageEvent(StageName.FLOP));
                kieSession.getAgenda().getAgendaGroup("possibility").setFocus();

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
                kieSession.insert(new StageEvent(StageName.TURN));
                kieSession.getAgenda().getAgendaGroup("possibility").setFocus();

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

                kieSession.insert(new StageEvent(StageName.RIVER));
                kieSession.getAgenda().getAgendaGroup("possibility").setFocus();

                break;
        }

        int fired = kieSession.fireAllRules();
        LOGGER.info("Number of fired rules: " + fired);
        kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();



        if (fired > 0) {
            String rulesFired = ((DebugAgendaEventListener) kieSession.getAgendaEventListeners().toArray()[0]).rulesFired;
            System.out.println(rulesFired);
            String[] all = rulesFired.split("\n");
            String lastRule = all[all.length - 1];
            ((DebugAgendaEventListener) kieSession.getAgendaEventListeners().toArray()[0]).rulesFired = "";
            if (lastRule.startsWith("Bluffing")) {
                if (ts.getCurrentStage().name().equals("RIVER"))
                    return new ConsultResultDTO(Action.ALL_IN.name(), lastRule);
                return new ConsultResultDTO(Action.RAISE.name(), lastRule);
            }

            return new ConsultResultDTO(ts.getPlayers().get(0).getAction().get(0).name(), lastRule);
        }
        return new ConsultResultDTO(ts.getPlayers().get(0).getAction().get(0).name(), "No explanation");
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
