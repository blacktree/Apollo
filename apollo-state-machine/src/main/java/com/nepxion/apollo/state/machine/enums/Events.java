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

public enum Events {
    // 事件：审核通过
    EVENT_AUDIT_PASS,
    // 事件：审核通不过
    EVENT_AUDIT_REJECT,
    // 事件：发送
    EVENT_SEND,
    // 事件：删除
    EVENT_DELETE
}