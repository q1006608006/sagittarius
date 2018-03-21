package top.ivan.sagittarius.griddle.persist.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import top.ivan.sagittarius.griddle.persist.pojo.Product;

import java.util.List;

@SqlResource("product")
public interface ProductDao extends BaseMapper<Product> {
    List<Product> searchProbable(@Param("keyWords") String keyWords);
}
