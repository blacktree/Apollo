package com.nepxion.apollo.state.machine;

/**
 * <p>Title: Nepxion Apollo</p>
 * <p>Description: Nepxion Apollo For Spring Cloud</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.util.EnumSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import com.nepxion.apollo.state.machine.enums.Events;
import com.nepxion.apollo.state.machine.enums.States;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states
                .withStates()
                .initial(States.STATE_WAIT_AUDIT)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal()
                .source(States.STATE_WAIT_AUDIT).target(States.STATE_WAIT_SEND)
                .event(Events.EVENT_AUDIT_PASS)
                .and()
                .withExternal()
                .source(States.STATE_WAIT_AUDIT).target(States.STATE_AUDIT_REJECT)
                .event(Events.EVENT_AUDIT_REJECT)
                .and()
                .withExternal()
                .source(States.STATE_WAIT_SEND).target(States.STATE_SEND_COMPLETE)
                .event(Events.EVENT_SEND)
                .and()
                .withExternal()
                .source(States.STATE_WAIT_AUDIT).target(States.STATE_DELETE_COMPLETE)
                .event(Events.EVENT_DELETE)
                .and()
                .withExternal()
                .source(States.STATE_WAIT_SEND).target(States.STATE_DELETE_COMPLETE)
                .event(Events.EVENT_DELETE)
                .and()
                .withExternal()
                .source(States.STATE_AUDIT_REJECT).target(States.STATE_DELETE_COMPLETE)
                .event(Events.EVENT_DELETE);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
            throws Exception {
        config
                .withConfiguration()
                .listener(listener());
    }

    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void transition(Transition<States, Events> transition) {
                if (transition.getTarget() != null) {
                    System.out.println("Target state : " + transition.getTarget().getId());
                    System.out.println("Next Actions : " + ActionFactory.getActions(transition.getTarget().getId()));
                }

                if (transition.getSource() != null) {
                    // System.out.println("Source state : " + transition.getSource().getId());
                }
            }
        };
    }
}