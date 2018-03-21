package top.ivan.sagittarius.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ivan.sagittarius.griddle.persist.dao.ProductPreviewDao;
import top.ivan.sagittarius.griddle.persist.dao.TraceDao;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;
import top.ivan.sagittarius.griddle.persist.pojo.Trace;
import top.ivan.sagittarius.web.vo.ProductTraceVo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemsService {

    @Autowired
    private ProductPreviewDao previewDao;
    @Autowired
    private TraceDao traceDao;

    public List<ProductPreview> getMostHotProduct(int page, int size) {
        List<Trace> traceList = traceDao.orderByDealDesc(1,page * size,size);
        if(null != traceList) {
            return getPreviewFromTrace(traceList);
        }
        return null;
    }

    public List<ProductTraceVo> getMostReduceProduct(int page, int size) {
        List<Trace> traceList = traceDao.orderByPriceScale(1,page * size,size);
        List<ProductTraceVo> vos = new ArrayList<>();
        if(null != traceList) {
            List<ProductPreview> previews = getPreviewFromTrace(traceList);
            previews.sort((p1,p2)-> Math.toIntExact(p1.getId() - p2.getId()));
            traceList.sort((t1,t2)-> Math.toIntExact(t1.getProductId() - t2.getProductId()));
            for (int i = 0; i < traceList.size(); i++) {
                ProductPreview preview = previews.get(i);
                Trace trace = traceList.get(i);
                ProductTraceVo vo = new ProductTraceVo(preview,trace);
                vos.add(vo);
            }
            return vos;
        }
        return null;
    }

    private List<ProductPreview> getPreviewFromTrace(List<Trace> traceList) {
        List<Long> idList = traceList.stream().map(Trace::getProductId).collect(Collectors.toList());
        return previewDao.byIds(idList);
    }
}
