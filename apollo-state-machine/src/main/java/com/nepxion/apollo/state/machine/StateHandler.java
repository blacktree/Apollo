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

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import com.nepxion.apollo.state.machine.entity.StateEntity;
import com.nepxion.apollo.state.machine.entity.StateMessage;
import com.nepxion.apollo.state.machine.enums.Events;
import com.nepxion.apollo.state.machine.enums.States;

@Component("stateHandler")
public class StateHandler {
    @Autowired
    private ObjectFactory<StateMachine<States, Events>> stateMachineObjectFactory;

    public StateMachine<States, Events> getStateMachine() {
        return stateMachineObjectFactory.getObject();
    }

    public void execute(Events event, StateEntity entity) {
        StateMachine<States, Events> stateMachine = getStateMachine();

        execute(stateMachine, event, entity);
    }

    public void execute(StateMachine<States, Events> stateMachine, Events event, StateEntity entity) {
        boolean result = stateMachine.sendEvent(new StateMessage<Events>(event, entity));

        if (!result) {
            throw new StateException("Invalid event=[" + event + "] to execute with entity=[" + entity + "]");
        }
    }
}