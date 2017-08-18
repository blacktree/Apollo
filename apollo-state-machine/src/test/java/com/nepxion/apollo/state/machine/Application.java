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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

import com.nepxion.apollo.state.machine.entity.Entity;
import com.nepxion.apollo.state.machine.enums.Events;
import com.nepxion.apollo.state.machine.enums.States;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private StateHandler stateHandler;

    @Override
    public void run(String... args) throws Exception {
        test1();
        test2();
    }

    private void test1() throws Exception {
        Entity entity = new Entity();
        entity.setSourceState(States.STATE_WAIT_AUDIT);

        // 处理同一个对象的状态，必须是同一个状态机
        StateMachine<States, Events> stateMachine = stateHandler.getStateMachine();
        stateHandler.execute(stateMachine, Events.EVENT_AUDIT_PASS, entity);
        stateHandler.execute(stateMachine, Events.EVENT_SEND, entity);
    }

    private void test2() throws Exception {
        Entity entity = new Entity();
        entity.setSourceState(States.STATE_WAIT_AUDIT);

        // 处理同一个对象的状态，必须是同一个状态机
        StateMachine<States, Events> stateMachine = stateHandler.getStateMachine();
        stateHandler.execute(stateMachine, Events.EVENT_AUDIT_REJECT, entity);
        stateHandler.execute(stateMachine, Events.EVENT_DELETE, entity);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}