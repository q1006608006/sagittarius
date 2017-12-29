package top.ivan.sagittarius.uav.impl.service;

import top.ivan.sagittarius.uav.service.MessageController;
import top.ivan.sagittarius.uav.vo.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageControllerImpl implements MessageController {
    private Map<String,Queue<Message>> messageCache = new HashMap<>();
    @Override
    public Message takeMessage(String queueId) {
        Queue<Message> queue = messageCache.get(queueId);
        if(null != queue) {
            return queue.poll();
        }
        return null;
    }

    @Override
    public boolean putMessage(String queueId, Message message) {
        Queue<Message> queue = messageCache.get(queueId);
        if(null == queue) {
            queue = new LinkedBlockingQueue<>();
            messageCache.put(queueId,queue);
        }
        queue.add(message);
        return true;
    }

}
