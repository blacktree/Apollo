package com.nepxion.apollo.state.machine;

/**
 * <p>Title: Nepxion Apollo</p>
 * <p>Description: Nepxion Apollo For Spring Cloud</p>
 * <p>Copyright: Copyright (c) 2017-2020</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.nepxion.apollo.state.machine.entity.StateEntity;
import com.nepxion.apollo.state.machine.entity.StateMessage;
import com.nepxion.apollo.state.machine.enums.Events;
import com.nepxion.apollo.state.machine.enums.States;

@Configuration
@EnableStateMachine
@Scope("prototype")
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    private static final Logger LOG = LoggerFactory.getLogger(StateMachineConfig.class);

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states
                .withStates()
                .initial(States.STATE_WAIT_AUDIT)
                .state(States.STATE_AUDIT_REJECT, action())
                .state(States.STATE_WAIT_SEND, action())
                .state(States.STATE_SEND_COMPLETE, action())
                .state(States.STATE_DELETE_COMPLETE, action())
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
                .autoStartup(true);
    }

    @Bean
    public Action<States, Events> action() {
        return new Action<States, Events>() {
            @Override
            public void execute(StateContext<States, Events> context) {
                StateMessage<Events> message = (StateMessage<Events>) context.getMessage();
                Events event = message.getPayload();
                StateEntity entity = message.getEntity();

                States sourceState = context.getSource().getId();
                States targetState = context.getTarget().getId();

                entity.setSourceState(sourceState);
                entity.setTargetState(targetState);
                entity.setEvent(event);
                entity.setTargetActions(StateFactory.getNextActions(targetState));

                triggerAction(entity);
            }
        };
    }

    private void triggerAction(StateEntity entity) {
        LOG.info("Entity : {}", entity);
    }
}