package test;

/**
 * Created by IVAN on 2017/12/10.
 */
public class TestServiceImpl implements TestService {
    public String sayHello(String name) {
        return "hello " + name;
    }
}
