package top.ivan.sagittarius.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ivan.sagittarius.griddle.persist.dao.ProductDao;
import top.ivan.sagittarius.griddle.persist.dao.ProductPreviewDao;
import top.ivan.sagittarius.griddle.persist.dao.TraceDao;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;
import top.ivan.sagittarius.griddle.persist.pojo.Trace;
import top.ivan.sagittarius.web.vo.ProductTraceVo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductPreviewDao previewDao;

    @Autowired
    private TraceDao traceDao;


    List<Trace> getProductTrace(long id,int interval) {
        return traceDao.getProductTrace(id,interval);
    }

}
