package top.ivan.sagittarius.fetch.gm;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.util.List;

public class GmPreviewBean {

    /**
     * secondCat : cat10000012
     * salesVolume : 167
     * color : 曜金黑
     * promoDesc : 我的国美U7 知我更懂我！ 高性价比之选
     * sUrl : //item.gome.com.cn/9140059715-1130549287.html
     * brandIds : ["1dbo"]
     * gomeCardType :
     * score : 2516.8347
     * promoFlag : 0
     * cityName : 北京市
     * evaluateCount : 7489
     * mallTag : 0
     * productTag : 1
     * onSale : false
     * shopId : 100001121
     * stock : 1
     * skuId : 1130549287
     * brandCode : 00879
     * taoType : 1
     * images : []
     * XSearch : true
     * sImg : //gfs17.gomein.net.cn/T11gxgB7hT1RCvBVdK
     * shopFlag : 0
     * rebate : 1
     * sName : 国美手机官方旗舰店
     * alt : 国美 (GOME) U7 4GB+64GB 手机 虹膜/人脸/指纹生物识别 曜金黑 移动联通电信4G手机 双卡双待
     * marketTag : 0
     * weight : 191.1237030029297
     * pId : 9140059715
     * skuType : gome-sku
     * promoScore : 0
     * isVip : 0
     * goodsType : ZSP
     * mUrl : //pinpai.gome.com.cn/100001121/index.html
     * isMulti : true
     * energyTag : 0
     * isBigImg : false
     * thirdProduct : false
     * taoGou : [2]
     * 3ppFlag : 0
     * skuNo : 100459679
     * name : 国美 (GOME) U7 4GB+64GB <label style='color:red;'>手机</label> 虹膜/人脸/指纹生物识别 曜金黑 移动联通电信4G<label style='color:red;'>手机</label> 双卡双待
     * defCatId : cat10000070
     * shopType : 1
     * interFlowType : 1
     * firstCat : cat31665542
     */

    private String secondCat;
    private int salesVolume;
    private String color;
    private String promoDesc;
    private String sUrl;
    private String gomeCardType;
    private double score;
    private int promoFlag;
    private String cityName;
    private int evaluateCount;
    private int mallTag;
    private int productTag;
    private boolean onSale;
    private String shopId;
    private int stock;
    private String skuId;
    private String brandCode;
    private int taoType;
    private boolean XSearch;
    private String sImg;
    private int shopFlag;
    private int rebate;
    private String sName;
    private String alt;
    private int marketTag;
    private double weight;
    private String pId;
    private String skuType;
    private int promoScore;
    private int isVip;
    private String goodsType;
    private String mUrl;
    private boolean isMulti;
    private int energyTag;
    private boolean isBigImg;
    private boolean thirdProduct;
    @SerializedName("3ppFlag")
    private int _$3ppFlag;
    private String skuNo;
    private String name;
    private String defCatId;
    private String shopType;
    private String interFlowType;
    private String firstCat;
    private List<String> brandIds;
    private List<?> images;
    private List<Integer> taoGou;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static GmPreviewBean objectFromData(String str) {
        return new Gson().fromJson(str, GmPreviewBean.class);
    }

    public static List<GmPreviewBean> objectsFromData(String str) {
        return new Gson().fromJson(str,new TypeToken<List<GmPreviewBean>>(){}.getType());
    }

    public String getSecondCat() {
        return secondCat;
    }

    public void setSecondCat(String secondCat) {
        this.secondCat = secondCat;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPromoDesc() {
        return promoDesc;
    }

    public void setPromoDesc(String promoDesc) {
        this.promoDesc = promoDesc;
    }

    public String getSUrl() {
        return sUrl;
    }

    public void setSUrl(String sUrl) {
        this.sUrl = sUrl;
    }

    public String getGomeCardType() {
        return gomeCardType;
    }

    public void setGomeCardType(String gomeCardType) {
        this.gomeCardType = gomeCardType;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getPromoFlag() {
        return promoFlag;
    }

    public void setPromoFlag(int promoFlag) {
        this.promoFlag = promoFlag;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getEvaluateCount() {
        return evaluateCount;
    }

    public void setEvaluateCount(int evaluateCount) {
        this.evaluateCount = evaluateCount;
    }

    public int getMallTag() {
        return mallTag;
    }

    public void setMallTag(int mallTag) {
        this.mallTag = mallTag;
    }

    public int getProductTag() {
        return productTag;
    }

    public void setProductTag(int productTag) {
        this.productTag = productTag;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public int getTaoType() {
        return taoType;
    }

    public void setTaoType(int taoType) {
        this.taoType = taoType;
    }

    public boolean isXSearch() {
        return XSearch;
    }

    public void setXSearch(boolean XSearch) {
        this.XSearch = XSearch;
    }

    public String getSImg() {
        return sImg;
    }

    public void setSImg(String sImg) {
        this.sImg = sImg;
    }

    public int getShopFlag() {
        return shopFlag;
    }

    public void setShopFlag(int shopFlag) {
        this.shopFlag = shopFlag;
    }

    public int getRebate() {
        return rebate;
    }

    public void setRebate(int rebate) {
        this.rebate = rebate;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public int getMarketTag() {
        return marketTag;
    }

    public void setMarketTag(int marketTag) {
        this.marketTag = marketTag;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getSkuType() {
        return skuType;
    }

    public void setSkuType(String skuType) {
        this.skuType = skuType;
    }

    public int getPromoScore() {
        return promoScore;
    }

    public void setPromoScore(int promoScore) {
        this.promoScore = promoScore;
    }

    public int getIsVip() {
        return isVip;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getMUrl() {
        return mUrl;
    }

    public void setMUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public boolean isIsMulti() {
        return isMulti;
    }

    public void setIsMulti(boolean isMulti) {
        this.isMulti = isMulti;
    }

    public int getEnergyTag() {
        return energyTag;
    }

    public void setEnergyTag(int energyTag) {
        this.energyTag = energyTag;
    }

    public boolean isIsBigImg() {
        return isBigImg;
    }

    public void setIsBigImg(boolean isBigImg) {
        this.isBigImg = isBigImg;
    }

    public boolean isThirdProduct() {
        return thirdProduct;
    }

    public void setThirdProduct(boolean thirdProduct) {
        this.thirdProduct = thirdProduct;
    }

    public int get_$3ppFlag() {
        return _$3ppFlag;
    }

    public void set_$3ppFlag(int _$3ppFlag) {
        this._$3ppFlag = _$3ppFlag;
    }

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefCatId() {
        return defCatId;
    }

    public void setDefCatId(String defCatId) {
        this.defCatId = defCatId;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getInterFlowType() {
        return interFlowType;
    }

    public void setInterFlowType(String interFlowType) {
        this.interFlowType = interFlowType;
    }

    public String getFirstCat() {
        return firstCat;
    }

    public void setFirstCat(String firstCat) {
        this.firstCat = firstCat;
    }

    public List<String> getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(List<String> brandIds) {
        this.brandIds = brandIds;
    }

    public List<?> getImages() {
        return images;
    }

    public void setImages(List<?> images) {
        this.images = images;
    }

    public List<Integer> getTaoGou() {
        return taoGou;
    }

    public void setTaoGou(List<Integer> taoGou) {
        this.taoGou = taoGou;
    }
}
