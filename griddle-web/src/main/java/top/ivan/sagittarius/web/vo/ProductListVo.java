package top.ivan.sagittarius.web.vo;

import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;
import top.ivan.sagittarius.web.util.ELFunctions;

public class ProductListVo {
    private String subTitle;
    private String location;
    private String locationPicUrl;
    private ProductPreview preview;
    private String subNick;
    private String locationUrl;

    public ProductListVo(ProductPreview preview) {
        this.preview = preview;
        if(preview.getTitle().length() > 24) {
            subTitle = preview.getTitle().substring(0,15) + "...";
        } else {
            subTitle = preview.getTitle();
        }
        if(preview.getNick().length() > 8) {
            subNick = preview.getNick().substring(0,8) ;
        } else {
            subNick = preview.getNick();
        }
        subTitle = subTitle.replaceAll(" ","&nbsp;");
//        location = preview.getDetailUrl().replaceAll(regex,"$2").replaceAll("\\..*","");
        location = preview.getLocation();
        locationUrl = ELFunctions.getLocationUrl(location);
        locationPicUrl = locationUrl + "favicon.ico";
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationPicUrl() {
        return locationPicUrl;
    }

    public void setLocationPicUrl(String locationPicUrl) {
        this.locationPicUrl = locationPicUrl;
    }

    public ProductPreview getPreview() {
        return preview;
    }

    public void setPreview(ProductPreview preview) {
        this.preview = preview;
    }

    public String getSubNick() {
        return subNick;
    }

    public void setSubNick(String subNick) {
        this.subNick = subNick;
    }

    public String getLocationUrl() {
        return locationUrl;
    }

    public void setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl;
    }
}
