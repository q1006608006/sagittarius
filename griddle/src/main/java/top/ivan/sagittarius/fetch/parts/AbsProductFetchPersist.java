package top.ivan.sagittarius.fetch.parts;

import org.springframework.beans.factory.annotation.Autowired;
import top.ivan.sagittarius.griddle.persist.dao.ProductDao;
import top.ivan.sagittarius.griddle.persist.pojo.Product;
import top.ivan.sagittarius.screen.parse.focus.JsonFocus;

import java.util.List;

public abstract class AbsProductFetchPersist extends AbsProductPersist {
    @Autowired
    ProductDao productDao;

    @Override
    public void doPersist(List<Product> productList) {
//        productDao.insertBatch(productList);
        for (Product product : productList) {
            System.out.println(JsonFocus.toJson(product));
        }
    }

}
