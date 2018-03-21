package top.ivan.sagittarius.griddle.persist.util;

import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.core.kit.GenKit;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;
import top.ivan.sagittarius.griddle.persist.dao.ProductDao;
import top.ivan.sagittarius.griddle.persist.dao.SearchDao;
import top.ivan.sagittarius.griddle.persist.pojo.Product;
import top.ivan.sagittarius.griddle.persist.pojo.Search;

public class PojoLoaderUtil {
    private static String driver = "com.mysql.jdbc.Driver";
//    private static String url = "jdbc:mysql://gd.badtheway.xin:3306/graduation_project?useUnicode=true&characterEncoding=UTF-8";
//    private static String user = "ivan";
//    private static String password = "qaz103103";

    private static String url = "jdbc:mysql://localhost:3306/graduation_project?useUnicode=true&characterEncoding=UTF-8";
    private static String user = "root";
    private static String password = "qaz103103";

    public static final String POJO_PATH = "top.ivan.sagittarius.griddle.persist.pojo";

    static {
        GenKit.setResourcePathRelativeToSrc("../griddle-database/src/main/resources");
        GenKit.setSrcPathRelativeToSrc("../griddle-database/src/main/java");
    }

    public static SQLManager getSqlManager() {
        ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, user, password);
        DBStyle mysql = new MySqlStyle();
        // sql语句放在classpagth的/beetlsql 目录下
        SQLLoader loader = new ClasspathLoader("/beetlsql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的
//        UnderlinedNameConversion nc = new UnderlinedNameConversion();
        DefaultNameConversion nc = new DefaultNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[]{new DebugInterceptor()});
        return sqlManager;
    }

    public static void createPojo(String table) throws Exception {
        SQLManager manager = getSqlManager();
        GenConfig config = new GenConfig();
        manager.genPojoCode(table, "top.ivan.sagittarius.griddle.persist.pojo", config);
    }

    public static <T extends BaseMapper> T getDao(Class<T> clazz) {
        return getSqlManager().getMapper(clazz);
    }

    public static void main(String[] args) throws Exception {
        createPojo("event");
//        getSqlManager().getMapper(ProductDao.class).searchProbable("123");
//        System.out.println(getSqlManager().getMapper(SearchDao.class).getAllKey());
/*        SQLManager manager = getSqlManager();
        ProductDao mapper = manager.getMapper(ProductDao.class);
        for (Product sdfw : mapper.searchProbable("123")) {
            System.out.println(sdfw.getProductName());
        }
        System.out.println(mapper.allCount())*/;
    }
}
