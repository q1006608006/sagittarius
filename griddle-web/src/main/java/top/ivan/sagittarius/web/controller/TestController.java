package top.ivan.sagittarius.web.controller;

import org.beetl.sql.core.Interceptor;
import org.beetl.sql.ext.DebugInterceptor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ivan.sagittarius.griddle.persist.dao.ProductPreviewDao;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController implements InitializingBean {

    @RequestMapping("/test")
    @ResponseBody
    public Map test() {
        Map<String,String> ret = new HashMap<>();
        ret.put("ad","bc");
        ret.put("ac","bd");
        return ret;
    }

    @Autowired
    private ProductPreviewDao productPreviewDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        productPreviewDao.getSQLManager().setInters(new Interceptor[]{new DebugInterceptor()});
    }
}
