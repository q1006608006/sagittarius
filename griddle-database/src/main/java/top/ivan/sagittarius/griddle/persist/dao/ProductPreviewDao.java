package top.ivan.sagittarius.griddle.persist.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import top.ivan.sagittarius.griddle.persist.pojo.Product;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;

import java.util.List;

@SqlResource("productPreview")
public interface ProductPreviewDao extends BaseMapper<ProductPreview> {
    @Sql("select * from ProductPreview where nid = ?")
    ProductPreview byNid(Long nid);

    List<ProductPreview> byIds(@Param("ids") List<Long> ids);

    List<ProductPreview> searchTitle(@Param("title") String title,@Param("start")int start,@Param("count")int count);

    @Sql("SELECT * FROM productpreview WHERE LOCATE(?,title) limit ?,?")
    List<ProductPreview> containsTitle(String title,int start,int count);

    List<ProductPreview> like(@Param("product") ProductPreview product,@Param("start") int start,@Param("count") int count);
}