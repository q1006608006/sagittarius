package top.ivan.sagittarius.web.service;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ivan.sagittarius.griddle.persist.dao.ProductPreviewDao;
import top.ivan.sagittarius.griddle.persist.dao.TraceDao;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;
import top.ivan.sagittarius.griddle.persist.pojo.Trace;
import top.ivan.sagittarius.segword.ProductWordSplit;
import top.ivan.sagittarius.web.choose.SearchModel;
import top.ivan.sagittarius.web.vo.ProductTraceVo;
import top.ivan.sagittarius.web.vo.SearchHandle;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemsService {

    @Autowired
    private ProductPreviewDao previewDao;
    @Autowired
    private TraceDao traceDao;

    public List<ProductPreview> getMostHotProduct(int page, int size) {
        PageQuery<Trace> pageQuery = new PageQuery<>(page,size);
        pageQuery.setOrderBy("dealImprove desc");
        traceDao.takeYesterdayTrace(pageQuery);
        List<Trace> traceList = pageQuery.getList();
        if (null != traceList) {
            return getPreviewFromTrace(traceList);
        }
        return null;
    }

    public List<ProductTraceVo> getMostReduceProduct(int page, int size) {
        PageQuery<Trace> pageQuery = new PageQuery<>(page,size);
        pageQuery.setOrderBy("priceImprove / (price - priceImprove)");
        traceDao.takeYesterdayTrace(pageQuery);

        List<Trace> traceList = pageQuery.getList();
        List<ProductTraceVo> vos = new ArrayList<>();
        if (null != traceList) {
            List<ProductPreview> previews = getPreviewFromTrace(traceList);
            previews.sort((p1, p2) -> Math.toIntExact(p1.getId() - p2.getId()));
            traceList.sort((t1, t2) -> Math.toIntExact(t1.getProductId() - t2.getProductId()));
            for (int i = 0; i < traceList.size(); i++) {
                ProductPreview preview = previews.get(i);
                Trace trace = traceList.get(i);
                ProductTraceVo vo = new ProductTraceVo(preview, trace);
                vos.add(vo);
            }
            vos.sort(Comparator.comparing(ProductTraceVo::getDisCount).reversed());
            return vos;
        }
        return null;
    }

    public PageQuery<ProductPreview> searchProduct(SearchHandle handle) {
        String searchLine = handle.getSearchLine();
        PageQuery<ProductPreview> pageQuery = getPageQuery(handle);
        if (searchLine.length() <= 1) {
            previewDao.containsTitle(pageQuery, searchLine);
            return pageQuery;
        }
        switch (handle.getModel()) {
            case AutoSegWord:
                Set<String> wordSet = ProductWordSplit.segLine(searchLine);
                handle.setSearchLine(String.join(",",wordSet));
                previewDao.searchTitleByKeyWords(pageQuery, wordSet);
                break;
            case ForceKeyWord:
                List<String> wordList = Arrays.asList(searchLine.split("[, ]"));
                handle.setSearchLine(String.join(",",wordList));
                previewDao.searchTitleByKeyWords(pageQuery, wordList);
                break;
            default:
                previewDao.searchTitle(pageQuery, searchLine);
        }
        return pageQuery;
    }


    private List<ProductPreview> getPreviewFromTrace(List<Trace> traceList) {
        List<Long> idList = traceList.stream().map(Trace::getProductId).collect(Collectors.toList());
        return previewDao.byIds(idList);
    }

    private static PageQuery<ProductPreview> getPageQuery(SearchHandle handle) {
        PageQuery<ProductPreview> pageQuery = new PageQuery<>(handle.getPageNum(), handle.getPageSize());
        String orderBy = handle.getOrderPoint() == null ? null : (handle.getOrderPoint() + (handle.isInDescOrder() ? " desc" : ""));
        pageQuery.setOrderBy(orderBy);
        if(handle.getMaxPrice() != null) {
            pageQuery.setPara("maxPrice",handle.getMaxPrice());
        }
        if(handle.getMinPrice() != null) {
            pageQuery.setPara("minPrice",handle.getMinPrice());
        }
        return pageQuery;
    }
}
