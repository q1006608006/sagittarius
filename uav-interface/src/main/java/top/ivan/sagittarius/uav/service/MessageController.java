package top.ivan.sagittarius.uav.service;

import top.ivan.sagittarius.uav.vo.Message;

public interface MessageController {
    Message takeMessage(String queueId);
    boolean putMessage(String queueId,Message message);
    boolean validate(String queueId,Message message);
}
