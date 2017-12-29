package top.ivan.sagittarius.uav.impl.dao;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.uav.dao.MessageDao;
import top.ivan.sagittarius.uav.vo.Message;


@Component
public class MessageDaoImpl implements MessageDao {

    private ListOperations<String,Message> listOperations;

    @Override
    public boolean putMessage(String queueId, Message message) {
        return listOperations.rightPush(queueId,message) > 0;
    }

    @Override
    public Message getMessage(String queueId) {
        return listOperations.leftPop(queueId);
    }
}
