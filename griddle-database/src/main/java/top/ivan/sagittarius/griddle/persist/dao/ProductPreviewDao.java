package top.ivan.sagittarius.griddle.persist.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import top.ivan.sagittarius.griddle.persist.pojo.Product;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;

import java.util.Collection;
import java.util.List;

@SqlResource("productPreview")
public interface ProductPreviewDao extends BaseMapper<ProductPreview> {
    @Sql("select * from ProductPreview where nid = ?")
    ProductPreview byNid(Long nid);

    List<ProductPreview> byIds(@Param("ids") List<Long> ids);

    void searchTitle(PageQuery<ProductPreview> pageQuery,@Param("title") String title);

    void containsTitle(PageQuery<ProductPreview> pageQuery,@Param("title")String title);

    List<ProductPreview> like(@Param("product") ProductPreview product,@Param("start") int start,@Param("count") int count);

    void searchTitleByKeyWords(PageQuery<ProductPreview> pageQuery,@Param("keyWords")Collection<String> keyWords);
}