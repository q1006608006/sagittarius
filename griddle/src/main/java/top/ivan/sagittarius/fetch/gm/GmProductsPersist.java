package top.ivan.sagittarius.fetch.gm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.fetch.service.ProductService;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.operator.persist.PersistOperator;
import top.ivan.sagittarius.screen.task.UnExceptMessageException;

import java.util.ArrayList;
import java.util.List;

@Component("gmPersist")
public class GmProductsPersist implements PersistOperator {

    @Autowired
    public ProductService productService;

    @Override
    public void process(Seed item) throws UnExceptMessageException {
        String json = item.getStorage().get("json");
        if(GmUtils.isFinished(item)) {
            return;
        }
        List<GmPreviewBean> beans = GmPreviewBean.objectsFromData(json);
        for (GmPreviewBean bean : beans) {
            String pid = bean.getPId();
            String skuId = bean.getSkuId();
            bean.setPrice(GmUtils.getPrice(pid, skuId));
        }
        List<ProductPreview> previews = new ArrayList<>();
        ProductPreview preview;
        for (GmPreviewBean bean : beans) {
            preview = GmUtils.transForPreview(bean);
            previews.add(preview);
        }
        productService.updateOrInsertProductPreview(previews);

    }
}
