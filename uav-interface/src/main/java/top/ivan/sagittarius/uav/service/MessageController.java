package top.ivan.sagittarius.uav.service;

import top.ivan.sagittarius.uav.vo.Message;

public interface MessageController {

    /**
     * 获取消息
     * @param queueId 消息队列ID
     * @return
     */
    Message takeMessage(String queueId);

    /**
     * 存放消息
     * @param queueId 消息队列ID
     * @param message
     * @return
     */
    boolean putMessage(String queueId,Message message);

}
