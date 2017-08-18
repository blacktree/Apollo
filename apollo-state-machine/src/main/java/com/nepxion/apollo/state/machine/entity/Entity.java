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
import com.nepxion.apollo.state.machine.enums.States;

public class Entity implements Serializable {
    private static final long serialVersionUID = -6738573261177034688L;

    private States currentState;
    private States nextState;

    private List<Actions> nextActionList;

    public States getCurrentState() {
        return currentState;
    }

    public void setCurrentState(States currentState) {
        this.currentState = currentState;
    }

    public States getNextState() {
        return nextState;
    }

    public void setNextState(States nextState) {
        this.nextState = nextState;
    }

    public List<Actions> getNextActionList() {
        return nextActionList;
    }

    public void setNextActionList(List<Actions> nextActionList) {
        this.nextActionList = nextActionList;
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