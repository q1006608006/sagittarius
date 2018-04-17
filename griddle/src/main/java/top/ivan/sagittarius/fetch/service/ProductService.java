package top.ivan.sagittarius.fetch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ivan.sagittarius.griddle.persist.dao.ProductPreviewDao;
import top.ivan.sagittarius.griddle.persist.dao.TraceDao;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;
import top.ivan.sagittarius.griddle.persist.pojo.Trace;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    private ProductPreviewDao previewDao;
    @Autowired
    private TraceDao traceDao;

    private static long dayEnd = 0;
    private static long dayStart = 0;

    public void updateOrInsertProductPreview(List<ProductPreview> previews) {
        int insertCount = 0,updateCount = 0;
        for (ProductPreview preview : previews) {
            try {
                ProductPreview old = new ProductPreview();
                old.setLocation(preview.getLocation());
                old.setNid(preview.getNid());
                old = previewDao.templateOne(old);
                validate(old,preview);
                if (null == old) {
                    if(preview.getViewPrice().compareTo(BigDecimal.ZERO) <= 0) {
                        preview.setViewPrice(BigDecimal.valueOf(-1));
                    }
                    previewDao.insert(preview);
                    insertCount++;
                } else if (updateAble(preview, old)) {
                    preview.setId(old.getId());
                    previewDao.updateTemplateById(preview);
                    Trace trace = takeTrace(old, preview);
                    if (insertAble(trace)) {
                        traceDao.insert(trace);
                        updateCount++;
                    }
                }
            } catch (Exception e) {
                System.out.println("error: " + e.getClass() + " message:" + e.getMessage());
            }
        }
        System.out.println(String.format("insert %d items,update %d items",insertCount,updateCount));
    }

    private static boolean insertAble(Trace t) {
        return t.getCommentImprove() != 0 || t.getDealImprove() != 0 || t.getPriceImprove().doubleValue() != 0;
    }

    private static void validate(ProductPreview ... previews) {
        for (ProductPreview preview : previews) {
            if (null == preview) {
                continue;
            }
            if(preview.getCommentCount() == null) {
                preview.setCommentCount(0);
            }
        }
    }

    private static boolean updateAble(ProductPreview cur,ProductPreview old) {
        if(System.currentTimeMillis() >= dayEnd) {
            updateTime();
        }
        if(old.getUpdateTime().getTime() >= dayStart) {
            return false;
        }
        return old.getViewPrice().compareTo(cur.getViewPrice()) != 0
                || !old.getViewSales().equals(cur.getViewSales())
                || !Objects.equals(old.getCommentCount(), cur.getCommentCount());
    }

    private static void updateTime() {
        dayEnd = LocalDate.now().atTime(23,59,59).toEpochSecond(ZoneOffset.of("+8")) * 1000;
        dayStart = LocalDate.now().atStartOfDay().toEpochSecond(ZoneOffset.of("+8")) * 1000;
    }
    private Trace takeTrace(ProductPreview old,ProductPreview cur) {
        Trace trace = new Trace();
        trace.setProductId(old.getId());
        trace.setPrice(cur.getViewPrice());
        trace.setPriceImprove(cur.getViewPrice().subtract(old.getViewPrice()));
        String oldSales = old.getViewSales();
        String curSales = cur.getViewSales();
        int improve = 0;
        if(null != oldSales && null != curSales) {
            improve = Integer.valueOf(curSales) - Integer.valueOf(oldSales);
        }
        trace.setDealImprove(improve);
        improve = cur.getCommentCount() - old.getCommentCount();
        trace.setCommentImprove(improve);
        return trace;
    }

}
