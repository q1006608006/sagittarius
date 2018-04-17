package top.ivan.sagittarius.web.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.ivan.sagittarius.griddle.persist.dao.ProductPreviewDao;
import top.ivan.sagittarius.griddle.persist.dao.TraceDao;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;
import top.ivan.sagittarius.griddle.persist.pojo.Trace;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemViewController {

    @Autowired
    private ProductPreviewDao previewDao;
    @Autowired
    private TraceDao traceDao;

    @RequestMapping("")
    public ModelAndView showItem(Long id) {
        if(id == null) {
            return new ModelAndView("redirect:/index");
        }
        ModelAndView itemModel = new ModelAndView("item");
        ProductPreview preview = previewDao.single(id);
        Trace trace = new Trace();
        trace.setProductId(id);
        List<Trace> traceList = traceDao.template(trace);
        traceList.sort(Comparator.comparing(Trace::getCreateTime));
        itemModel.addObject("preview",preview);
        itemModel.addObject("traceList",TraceInfo.takeTrace(preview,traceList));
        return itemModel;
    }


    public static class TraceInfo {
        private BigDecimal price;
        private int commentCount;
        private int saleCount;
        private Date date;

        public static List<TraceInfo> takeTrace(ProductPreview curPreview,List<Trace> traces) {
            if(null == traces || traces.size() == 0) {
                return null;
            }
            List<TraceInfo> infoList = new ArrayList<>();
            int curComment = curPreview.getCommentCount();
            int curSale = getSales(curPreview.getViewSales());
            TraceInfo info;
            Trace trace;
            for (int i = traces.size() - 1; i >= 0; i--) {
                trace = traces.get(i);
                curComment -= trace.getCommentImprove();
                curSale -= trace.getDealImprove();
            }
            info = new TraceInfo();
            info.price = traces.get(0).getPrice().subtract(traces.get(0).getPriceImprove());
            info.commentCount = curComment;
            info.saleCount = curSale;
            info.date = curPreview.getCreateTime();
            infoList.add(info);
            for (int i = 0; i < traces.size(); i++) {
                info = new TraceInfo();
                trace = traces.get(i);
                info.date = trace.getCreateTime();
                curComment += trace.getCommentImprove();
                curSale += trace.getDealImprove();
                info.price = trace.getPrice();
                info.commentCount = curComment;
                info.saleCount = curSale;
                infoList.add(info);
            }
            return infoList;
        }

        private static int getSales(String saleStr) {
            if(saleStr != null) {
                return Integer.valueOf(saleStr);
            }
            return 0;
        }

        private TraceInfo(){
        }

        public BigDecimal getPrice() {
            return price;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public int getSaleCount() {
            return saleCount;
        }

        public Date getDate() {
            return date;
        }
    }
}
