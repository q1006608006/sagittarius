package top.ivan.sagittarius.screen.defalutparts;

import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.operator.persist.PersistOperator;
import top.ivan.sagittarius.screen.parse.focus.JsonFocus;

import java.util.Map;

public class TestPersistOperator implements PersistOperator {
    @Override
    public void process(Seed item) {
        Map<String,String> map  = item.getStorage();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(String.format("%s: %s",entry.getKey(),entry.getValue()));
        }
    }

}
