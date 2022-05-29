package com.sbz.proj.service;


import com.sbz.proj.model.*;
import com.sbz.proj.util.DebugAgendaEventListener;
import com.sbz.proj.util.KnowledgeSessionHelper;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kie.internal.utils.KieHelper;
import org.kie.api.io.ResourceType;
import org.kie.api.builder.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

        p1.setCard1(new Card(Rank.ACE, Suit.SPADES));
        p1.setCard2(new Card(Rank.ACE, Suit.HEARTS));

        TableState ts1 = new TableState();
        ts1.setCurrentStage(StageName.PRE_FLOP);

        kieSession.insert(p1);
//        kieSession.insert(ts1);
        int fired = kieSession.fireAllRules();
        //kieSession.dispose();

        LOGGER.info("Number of fired rules: " + fired);

    }

    public void poredimo2Karte(boolean daProdje) {
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");
        Card card1 = new Card(Rank.R10, Suit.SPADES);
        Card card2 = new Card(Rank.JACK, Suit.CLUBS);

        if (daProdje){
            kieSession.insert(card2);
            kieSession.insert(card1);
        }
        else {
            kieSession.insert(card1);
            kieSession.insert(card2);
        }

        int fired = kieSession.fireAllRules();
        //kieSession.dispose();

        int res = card1.compareTo(card2);
        if (res == 1) System.out.println("veca je prva");
        else if (res == -1) System.out.println("veca je druga");

        LOGGER.info("Number of fired rules: " + fired);
    }

    public void checkRoyalFlush() {
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");
        Card card1 = new Card(Rank.ACE, Suit.SPADES);
        Card card2 = new Card(Rank.R2, Suit.SPADES);
        Card card3 = new Card(Rank.R3, Suit.SPADES);
        Card card4 = new Card(Rank.R4, Suit.SPADES);
        Card card5 = new Card(Rank.R5, Suit.SPADES);
        Possibility p = new Possibility();
        p.cards = new ArrayList<>();
        p.cards.add(card1);
        p.cards.add(card2);
        p.cards.add(card3);
        p.cards.add(card4);
        p.cards.add(card5);
        p.weKnow = false;
        //p.cards.sort(Card::compareTo);

        p.setupCards();

        //p.cards.stream().filter(c -> c.getSuit() == p.cards.get(0).getSuit()).collect(Collectors.toList()).size() == 5

        kieSession.insert(p);
        kieSession.setGlobal("LOGGER", LOGGER);
        int fired = kieSession.fireAllRules();
        LOGGER.info("Number of fired rules: " + fired);
    }

    public void checkStraigthFlush() throws IOException {
        InputStream template = new FileInputStream("C:\\Users\\isido\\OneDrive\\Desktop\\SBZ_Proj\\sbzproj\\drools-spring-kjar\\src\\main\\resources\\com\\sbz\\template-dtable\\customer-classification-simple.drt");

        List<ClassificationTemplateModel> data = new ArrayList<>();

        data.add(new ClassificationTemplateModel(PokerHand.STRAIGHT_FLUSH, Rank.ACE, Rank.R2, Rank.R3, Rank.R4, Rank.R5, true));
        data.add(new ClassificationTemplateModel(PokerHand.STRAIGHT_FLUSH, Rank.R2, Rank.R3, Rank.R4, Rank.R5, Rank.R6, true));

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

        System.out.println(drl);

        BufferedWriter writer = new BufferedWriter(new FileWriter("rules.drl"));
        writer.write(drl);

        writer.close();

        KieSession ksession = createKieSessionFromDRL(drl);

        doTest(ksession);
    }


    private void doTest(KieSession ksession){
        Card card1 = new Card(Rank.ACE, Suit.SPADES);
        Card card2 = new Card(Rank.R2, Suit.SPADES);
        Card card3 = new Card(Rank.R3, Suit.HEARTS);
        Card card4 = new Card(Rank.R4, Suit.SPADES);
        Card card5 = new Card(Rank.R5, Suit.SPADES);

        Possibility p = new Possibility();

        p.cards = new ArrayList<>();
        p.cards.add(card1);
        p.cards.add(card2);
        p.cards.add(card3);
        p.cards.add(card4);
        p.cards.add(card5);
        p.cards.sort(Card::compareTo);
        p.weKnow = false;

        ksession.insert(p);

        ksession.fireAllRules();
    }

    private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }

            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }

        return kieHelper.build().newKieSession();
    }

    public void checkWhatWeHaveList(List<Possibility> possibilities) {
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");

        for (Possibility p : possibilities) {
            kieSession.insert(p);
        }

        int fired = kieSession.fireAllRules();
        LOGGER.info("Number of fired rules: " + fired);
    }

    public void checkWhatWeHave(FiveCardsDTO fiveCardsDTO) {
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");
        Card card1 = fiveCardsDTO.card1;
        Card card2 = fiveCardsDTO.card2;
        Card card3 = fiveCardsDTO.card3;
        Card card4 = fiveCardsDTO.card4;
        Card card5 = fiveCardsDTO.card5;
        Possibility p = new Possibility();
        p.cards = new ArrayList<>();
        p.cards.add(card1);
        p.cards.add(card2);
        p.cards.add(card3);
        p.cards.add(card4);
        p.cards.add(card5);
        //p.cards.sort(Card::compareTo);

        p.setupCards();

        kieSession.insert(p);
        //kieSession.setGlobal("LOGGER", LOGGER);

        int fired = kieSession.fireAllRules();
        LOGGER.info("Number of fired rules: " + fired);
    }
}
