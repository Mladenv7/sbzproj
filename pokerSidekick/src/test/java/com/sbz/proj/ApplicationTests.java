package com.sbz.proj;

import com.sbz.proj.model.Action;
import com.sbz.proj.model.StageName;
import com.sbz.proj.model.events.ActionInsertEvent;
import com.sbz.proj.model.events.StageEvent;
import com.sbz.proj.util.DebugAgendaEventListener;
import com.sbz.proj.util.KnowledgeSessionHelper;
import org.drools.core.time.SessionPseudoClock;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class ApplicationTests {

	private final static java.util.logging.Logger LOGGER = Logger.getLogger(KnowledgeSessionHelper.class.getName());

	@Test
	public void testCEPConfigThroughKModuleXML() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("pseudoClockTest");
		KieSession ksession2 = kContainer.newKieSession("realtimeClockTest");
		ksession1.addEventListener(new DebugAgendaEventListener());
		ksession1.setGlobal("LOGGER", LOGGER);
		ksession2.addEventListener(new DebugAgendaEventListener());
		ksession2.setGlobal("LOGGER", LOGGER);

		runPseudoClockExample(ksession1);
		runRealtimeClockExample(ksession2);

	}

	private void runPseudoClockExample(KieSession ksession) {
		SessionPseudoClock clock = ksession.getSessionClock();
		ksession.getAgenda().getAgendaGroup("pre-flop").setFocus();
		for (int index = 0; index < 16; index++) {
			ksession.insert(new StageEvent(StageName.PRE_FLOP));
			ActionInsertEvent foldInsert = new ActionInsertEvent(new Date(), Action.FOLD);
			ksession.insert(foldInsert);
			clock.advanceTime(30, TimeUnit.SECONDS);

		}
		int ruleCount = ksession.fireAllRules();
		assertThat(ruleCount, equalTo(6));
	}

	private void runRealtimeClockExample(KieSession ksession) {
		ksession.getAgenda().getAgendaGroup("pre-flop").setFocus();
		Thread t = new Thread() {
			@Override
			public void run() {
				for (int index = 0; index < 16; index++) {
					ksession.insert(new StageEvent(StageName.PRE_FLOP));
					ActionInsertEvent foldInsert = new ActionInsertEvent(new Date(), Action.FOLD);
					ksession.insert(foldInsert);
				}
			}
		};
		t.setDaemon(true);
		t.start();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			//do nothing
		}
		int fired = ksession.fireAllRules();
		assertThat(fired, equalTo(6));
	}

}
