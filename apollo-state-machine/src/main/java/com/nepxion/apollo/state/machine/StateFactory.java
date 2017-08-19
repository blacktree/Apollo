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

import java.util.Arrays;
import java.util.List;

import com.nepxion.apollo.state.machine.enums.Actions;
import com.nepxion.apollo.state.machine.enums.Events;
import com.nepxion.apollo.state.machine.enums.States;

public class StateFactory {
    // 根据当前状态和事件，驱动出下一个状态
    public static States getNextState(States state, Events event) {
        if (state == States.STATE_WAIT_AUDIT && event == Events.EVENT_AUDIT_PASS) {
            return States.STATE_WAIT_SEND;
        }

        if (state == States.STATE_WAIT_AUDIT && event == Events.EVENT_AUDIT_REJECT) {
            return States.STATE_AUDIT_REJECT;
        }

        if (state == States.STATE_WAIT_SEND && event == Events.EVENT_SEND) {
            return States.STATE_SEND_COMPLETE;
        }

        if (state == States.STATE_WAIT_AUDIT && event == Events.EVENT_DELETE) {
            return States.STATE_DELETE_COMPLETE;
        }

        if (state == States.STATE_WAIT_SEND && event == Events.EVENT_DELETE) {
            return States.STATE_DELETE_COMPLETE;
        }

        if (state == States.STATE_AUDIT_REJECT && event == Events.EVENT_DELETE) {
            return States.STATE_DELETE_COMPLETE;
        }

        throw new StateException("Invalid state=[" + state + "] with event=[" + event + "]");
    }

    // 根据当前状态获取下一步动作列表
    public static List<Actions> getNextActions(States state) {
        switch (state) {
            case STATE_WAIT_AUDIT:
                return Arrays.asList(Actions.ACTION_TO_AUDIT, Actions.ACTION_TO_DELETE);
            case STATE_AUDIT_REJECT:
                return Arrays.asList(Actions.ACTION_TO_DELETE);
            case STATE_WAIT_SEND:
                return Arrays.asList(Actions.ACTION_TO_SEND, Actions.ACTION_TO_DELETE);
            case STATE_SEND_COMPLETE:
                return Arrays.asList(Actions.ACTION_TO_DOWNLOAD);
            case STATE_DELETE_COMPLETE:
                return Arrays.asList();
        }

        throw new StateException("Invalid state=[" + state + "]");
    }
}