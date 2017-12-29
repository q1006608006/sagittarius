package top.ivan.sagittarius.uav.dao;

import top.ivan.sagittarius.uav.vo.Message;

public interface MessageDao {

    boolean putMessage(String queueId,Message message);

    Message getMessage(String queueId);
}
