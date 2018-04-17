package top.ivan.sagittarius.griddle.persist.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import top.ivan.sagittarius.griddle.persist.pojo.Trace;

import java.util.List;

@SqlResource("trace")
public interface TraceDao extends BaseMapper<Trace> {

    List<Trace> orderByDealDesc(@Param("interval") int dayInterval,@Param("start") int start,@Param("count") int size);

    List<Trace> orderByPriceScale(@Param("interval") int dayInterval,@Param("start") int start,@Param("count") int size);

    List<Trace> getProductTrace(@Param("productId") long id,@Param("interval") int dayInterval);

    void takeYesterdayTrace(PageQuery<Trace> pageQuery);
}
