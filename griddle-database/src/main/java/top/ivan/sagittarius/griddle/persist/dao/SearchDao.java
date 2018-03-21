package top.ivan.sagittarius.griddle.persist.dao;

import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import top.ivan.sagittarius.griddle.persist.pojo.Search;

import java.util.List;

public interface SearchDao extends BaseMapper<Search> {
    @Sql("select searchKey from Search where isDelete = 0")
    List<String> getAllKey();
}
