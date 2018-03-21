package top.ivan.sagittarius.web.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public TestService() {
        System.out.println("test service init....");
    }
    public void test() {
        System.out.println("tetdsd");
    }
}
