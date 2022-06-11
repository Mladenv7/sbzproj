package com.sbz.proj.util;

import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;

import java.util.logging.Logger;

public class DebugAgendaEventListener extends DefaultAgendaEventListener {

    private final static Logger LOGGER = Logger.getLogger(DebugAgendaEventListener.class.getName());
    public String rulesFired = "";

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        Rule rule = event.getMatch().getRule();
        rulesFired += rule.getName() + "\n";
        LOGGER.info("Rule fired: " + "\u001B[31m" + rule.getName() + "\u001B[0m");
        LOGGER.info("----------------------------------------------------------------------------");
    }
}