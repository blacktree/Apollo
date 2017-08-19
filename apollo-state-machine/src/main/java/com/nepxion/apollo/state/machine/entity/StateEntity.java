package com.nepxion.apollo.state.machine.entity;

/**
 * <p>Title: Nepxion Apollo</p>
 * <p>Description: Nepxion Apollo For Spring Cloud</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.nepxion.apollo.state.machine.enums.Actions;
import com.nepxion.apollo.state.machine.enums.Events;
import com.nepxion.apollo.state.machine.enums.States;

public class StateEntity implements Serializable {
    private static final long serialVersionUID = -6738573261177034688L;

    // 上一个状态
    private States sourceState;

    // 下一个状态
    private States targetState;

    // 状态变迁所受哪个事件驱动的
    private Events event;

    // 下一个状态对应的操作列表
    private List<Actions> targetActions;

    public States getSourceState() {
        return sourceState;
    }

    public void setSourceState(States sourceState) {
        this.sourceState = sourceState;
    }

    public States getTargetState() {
        return targetState;
    }

    public void setTargetState(States targetState) {
        this.targetState = targetState;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public List<Actions> getTargetActions() {
        return targetActions;
    }

    public void setTargetActions(List<Actions> targetActions) {
        this.targetActions = targetActions;
    }

    @Override
    public boolean equals(Object object) {
        return EqualsBuilder.reflectionEquals(this, object);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}