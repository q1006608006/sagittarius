package top.ivan.sagittarius.fetch.taobao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.fetch.redis.RedisSetOperator;
import top.ivan.sagittarius.fetch.service.ProductService;
import top.ivan.sagittarius.griddle.persist.dao.ProductDao;
import top.ivan.sagittarius.griddle.persist.dao.ProductPreviewDao;
import top.ivan.sagittarius.griddle.persist.dao.ProductViewDao;
import top.ivan.sagittarius.griddle.persist.pojo.Product;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;
import top.ivan.sagittarius.griddle.persist.pojo.ProductView;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.operator.persist.PersistOperator;
import top.ivan.sagittarius.screen.parse.focus.JsonFocus;
import top.ivan.sagittarius.fetch.taobao.TaoBaoPageBean.ModsBean.ItemlistBean.DataBean.AuctionsBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component("tbInsertPersist")
public class TaoBaoInsertPersist implements PersistOperator {
    @Autowired
    private ProductService productService;
    private static final String protocol = "https:";

    @Override
    public void process(Seed item) {
        TaoBaoPageBean pageBean = TaoBaoBeanCheckUtils.fromSeed(item,"itemStr");
        if (pageBean == null) {
            throw new IllegalArgumentException("null pageBean is un except here");
        }
        item.setBaggage(pageBean);
        if(TaoBaoBeanCheckUtils.hasProductItems(pageBean)) {
            System.out.println(item + " persist start==>");
            int size = processAuctions(pageBean.getMods().getItemlist().getData().getAuctions());
            System.out.println(String.format("%s persisted %d items,end persist<==",item,size));
        }
    }

    public int processAuctions(List<AuctionsBean> auctions) {
        List<ProductPreview> previews = new ArrayList<>();
        for (AuctionsBean auction : auctions) {
            ProductPreview productPreview = getProductPreview(auction);
            previews.add(productPreview);
        }
        productService.updateOrInsertProductPreview(previews);
        return previews.size();
    }

    private ProductPreview getProductPreview(AuctionsBean auction) {
        ProductPreview view = new ProductPreview();
        String commentCount = auction.getComment_count();
        if(null != commentCount && commentCount.length() > 0) {
            view.setCommentCount(Integer.valueOf(commentCount));
        }
        view.setCategory(auction.getCategory());
        view.setItemLoc(auction.getItem_loc());
        view.setNick(auction.getNick());
        view.setNid(Long.valueOf(auction.getNid()));
        view.setPicUrl(protocol + auction.getPic_url());
        view.setTitle(auction.getRaw_title());
        view.setShopLink(protocol + auction.getShopLink());
        if(auction.getView_sales() != null) {
            view.setViewSales(auction.getView_sales().replace("人付款", ""));
        }
        view.setViewPrice(BigDecimal.valueOf(Double.valueOf(auction.getView_price())));
        view.setViewFee(auction.getView_fee());
        view.setUserId(Long.valueOf(auction.getUser_id()));
        String detailUrl;
        try {
            if (auction.getShopcard().isIsTmall()) {
                detailUrl = String.format("https://detail.%s.com/item.htm?id=%s", "tmall", auction.getNid());
                view.setLocation("tmall");
            } else {
                detailUrl = String.format("https://detail.%s.com/item.htm?id=%s", "taobao", auction.getNid());
                view.setLocation("taobao");
            }
        } catch (Exception e) {
            detailUrl = String.format("https://detail.%s.com/item.htm?id=%s", "taobao", auction.getNid());
        }
        view.setDetailUrl(detailUrl);

        return view;
    }
}
