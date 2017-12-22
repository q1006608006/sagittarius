package test;

import top.ivan.sagittarius.uav.service.TestService;

/**
 * Created by IVAN on 2017/12/10.
 */
public class TestServiceImpl implements TestService {
    public String sayHello(String name) {
        return "hello " + name;
    }
}
