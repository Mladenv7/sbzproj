package com.sbz.proj.service;


import com.sbz.proj.model.Card;
import com.sbz.proj.model.Player;
import com.sbz.proj.model.Suit;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private static Logger log = LoggerFactory.getLogger(TestService.class);

    private final KieContainer kieContainer;

    @Autowired
    public TestService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void nistaSeTiNeSekeraj(){
        KieSession kieSession = kieContainer.newKieSession("ExampleSession");
        Player p1 = new Player();

        System.out.println(this.kieContainer);

        p1.setCard1(new Card(1, Suit.SPADES));
        p1.setCard2(new Card(1, Suit.HEARTS));

        kieSession.insert(p1);
        kieSession.fireAllRules();
        kieSession.dispose();

    }
}
