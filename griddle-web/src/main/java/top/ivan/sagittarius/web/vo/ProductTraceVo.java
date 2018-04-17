package top.ivan.sagittarius.web.vo;

import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;
import top.ivan.sagittarius.griddle.persist.pojo.Trace;

import java.math.BigDecimal;

public class ProductTraceVo extends ProductPreview {
    private BigDecimal reduce;
    private BigDecimal oldPrice;
    private BigDecimal curPrice;
    private BigDecimal disCount;

    public ProductTraceVo(ProductPreview preview,Trace trace) {
        setId(preview.getId());
        setCategory(preview.getCategory());
        setViewSales(preview.getViewSales());
        setViewPrice(preview.getViewPrice());
        setViewFee(preview.getViewFee());
        setUserId(preview.getUserId());
        setTitle(preview.getTitle());
        setShopLink(preview.getShopLink());
        setPicUrl(preview.getPicUrl());
        setDetailUrl(preview.getDetailUrl());
        setNid(preview.getNid());
        setNick(preview.getNick());
        setItemLoc(preview.getItemLoc());
        setCommentCount(preview.getCommentCount() == null ? 0 : preview.getCommentCount());
        setReduce(preview.getViewPrice());
        setOldPrice(trace.getPrice().subtract(trace.getPriceImprove()));
        if(dividable(getOldPrice())) {
            setDisCount(trace.getPriceImprove().negate().divide(getOldPrice(),2,BigDecimal.ROUND_UP));
        } else {
            setDisCount(BigDecimal.ONE);
        }
        setCurPrice(preview.getViewPrice());
    }

    private static boolean dividable(BigDecimal toDiv) {
        if(toDiv.doubleValue() == 0) {
            return false;
        }
        return true;
    }

    public BigDecimal getReduce() {
        return reduce;
    }

    public void setReduce(BigDecimal reduce) {
        this.reduce = reduce;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public BigDecimal getCurPrice() {
        return curPrice;
    }

    public void setCurPrice(BigDecimal curPrice) {
        this.curPrice = curPrice;
    }

    public BigDecimal getDisCount() {
        return disCount;
    }

    public void setDisCount(BigDecimal disCount) {
        this.disCount = disCount;
    }

    public static void main(String[] args) {
        System.out.println(BigDecimal.ZERO.equals(new BigDecimal("0.00")));
        System.out.println(dividable(new BigDecimal("0.00")));
        System.out.println(new BigDecimal("0.00").doubleValue() == 0);
        System.out.println(new BigDecimal(-10000).negate().divide(new BigDecimal(3),2,BigDecimal.ROUND_UP));
    }
}
