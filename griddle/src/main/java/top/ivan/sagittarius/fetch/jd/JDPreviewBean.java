package top.ivan.sagittarius.fetch.jd;

import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;

import java.math.BigDecimal;

public class JDPreviewBean {
    private BigDecimal price;
    private Long nid;
    private String imgLink;
    private int comment;
    private String title;
    private String shopLink;
    private String shopTitle;
    private String detailUrl;

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShopLink() {
        return shopLink;
    }

    public void setShopLink(String shopLink) {
        this.shopLink = shopLink;
    }

    public ProductPreview getProductPreview() {
        ProductPreview preview = new ProductPreview();
        preview.setLocation("jd");
        preview.setShopLink(shopLink);
        preview.setNick(shopTitle);
        preview.setCommentCount(comment);
        preview.setDetailUrl(detailUrl);
        preview.setPicUrl(imgLink);
        preview.setViewPrice(price);
        preview.setNid(nid);
        preview.setTitle(title);
        preview.setViewSales("-1");
        return preview;
    }

    @Override
    public String toString() {
        return "JDPreviewBean{" +
                "price=" + price +
                ", nid=" + nid +
                ", imgLink='" + imgLink + '\'' +
                ", comment=" + comment +
                ", title='" + title + '\'' +
                ", shopLink='" + shopLink + '\'' +
                ", shopTitle='" + shopTitle + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                '}';
    }
}
