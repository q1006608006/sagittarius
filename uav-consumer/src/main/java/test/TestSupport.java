package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.uav.consumer.DefaultTaskSupporter;
import top.ivan.sagittarius.uav.consumer.TaskSupporter;

@Component
public class TestSupport {
    @Autowired
    private TaskSupporter taskSupporter;

    public void test() {
        System.out.println("test");
        System.out.println(taskSupporter);
    }
}
