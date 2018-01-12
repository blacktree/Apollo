package com.nepxion.apollo.state.machine.enums;

/**
 * <p>Title: Nepxion Apollo</p>
 * <p>Description: Nepxion Apollo For Spring Cloud</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

public enum States {
    // 状态：待审核
    STATE_WAIT_AUDIT,
    // 状态：审核驳回
    STATE_AUDIT_REJECT,
    // 状态：待发送
    STATE_WAIT_SEND,
    // 状态：已上报运营商
    STATE_SEND_COMPLETE,
    // 状态：已删除
    STATE_DELETE_COMPLETE
}