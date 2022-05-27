package com.sbz.proj.util;

import com.sbz.proj.service.TestService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

public class KnowledgeSessionHelper {

//	private final KieContainer kieContainer;
//
//	@Autowired
//	public KnowledgeSessionHelper(KieContainer kieContainer) {
//		this.kieContainer = kieContainer;
//	}

	private final static java.util.logging.Logger LOGGER = Logger.getLogger(KnowledgeSessionHelper.class.getName());
	
	public static KieContainer createRuleBase() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kieContainer = ks.getKieClasspathContainer();
		return kieContainer;
	}
	
	public static StatelessKieSession getStatlessKnowledgeSession(KieContainer kieContainer, String sessionName) {
		StatelessKieSession kSession = kieContainer.newStatelessKieSession(sessionName);
		return kSession;
	}
	
	public static KieSession getStatefulKnowledgeSession(KieContainer kieContainer, String sessionName) {
		KieSession kSession = kieContainer.newKieSession(sessionName);
		kSession.addEventListener(new DebugAgendaEventListener());
		kSession.setGlobal("LOGGER", LOGGER);
		return kSession;
	}

}
