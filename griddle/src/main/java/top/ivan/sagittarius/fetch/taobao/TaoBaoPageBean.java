package top.ivan.sagittarius.fetch.taobao;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class TaoBaoPageBean implements Serializable {


    private String pageName;
    private ModsBean mods;
    private String baseUrl;
    private String url;

    public static TaoBaoPageBean objectFromData(String str) {

        return new Gson().fromJson(str, TaoBaoPageBean.class);
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public ModsBean getMods() {
        return mods;
    }

    public void setMods(ModsBean mods) {
        this.mods = mods;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class ModsBean {

        private ItemlistBean itemlist;
        private GridBean grid;
        private RelatedBean related;

        public static ModsBean objectFromData(String str) {

            return new Gson().fromJson(str, ModsBean.class);
        }

        public ItemlistBean getItemlist() {
            return itemlist;
        }

        public void setItemlist(ItemlistBean itemlist) {
            this.itemlist = itemlist;
        }

        public GridBean getGrid() {
            return grid;
        }

        public void setGrid(GridBean grid) {
            this.grid = grid;
        }

        public RelatedBean getRelated() {
            return related;
        }

        public void setRelated(RelatedBean related) {
            this.related = related;
        }

        public static class ItemlistBean {

            private String status;
            private DataBean data;

            public static ItemlistBean objectFromData(String str) {

                return new Gson().fromJson(str, ItemlistBean.class);
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public static class DataBean {

                private String postFeeText;
                private String trace;
                private boolean isSameStyleView;
                private String query;
                private String spmModId;
                private CustomizedconfigBean customizedconfig;
                private String clickstaturl;
                private List<AuctionsBean> auctions;
                private List<?> recommendAuctions;
                private List<?> sellers;

                public static DataBean objectFromData(String str) {

                    return new Gson().fromJson(str, DataBean.class);
                }

                public String getPostFeeText() {
                    return postFeeText;
                }

                public void setPostFeeText(String postFeeText) {
                    this.postFeeText = postFeeText;
                }

                public String getTrace() {
                    return trace;
                }

                public void setTrace(String trace) {
                    this.trace = trace;
                }

                public boolean isIsSameStyleView() {
                    return isSameStyleView;
                }

                public void setIsSameStyleView(boolean isSameStyleView) {
                    this.isSameStyleView = isSameStyleView;
                }

                public String getQuery() {
                    return query;
                }

                public void setQuery(String query) {
                    this.query = query;
                }

                public String getSpmModId() {
                    return spmModId;
                }

                public void setSpmModId(String spmModId) {
                    this.spmModId = spmModId;
                }

                public CustomizedconfigBean getCustomizedconfig() {
                    return customizedconfig;
                }

                public void setCustomizedconfig(CustomizedconfigBean customizedconfig) {
                    this.customizedconfig = customizedconfig;
                }

                public String getClickstaturl() {
                    return clickstaturl;
                }

                public void setClickstaturl(String clickstaturl) {
                    this.clickstaturl = clickstaturl;
                }

                public List<AuctionsBean> getAuctions() {
                    return auctions;
                }

                public void setAuctions(List<AuctionsBean> auctions) {
                    this.auctions = auctions;
                }

                public List<?> getRecommendAuctions() {
                    return recommendAuctions;
                }

                public void setRecommendAuctions(List<?> recommendAuctions) {
                    this.recommendAuctions = recommendAuctions;
                }

                public List<?> getSellers() {
                    return sellers;
                }

                public void setSellers(List<?> sellers) {
                    this.sellers = sellers;
                }

                public static class CustomizedconfigBean {
                    /**
                     * url : /api?ajax=true&m=customized&q=打印机&s=36&bcoffset=0&rn=a996fa95a1b5c27e7aebce4bab7a3740
                     * status : true
                     */

                    private String url;
                    private boolean status;

                    public static CustomizedconfigBean objectFromData(String str) {

                        return new Gson().fromJson(str, CustomizedconfigBean.class);
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public boolean isStatus() {
                        return status;
                    }

                    public void setStatus(boolean status) {
                        this.status = status;
                    }
                }

                public static class AuctionsBean {

                    private int p4p;
                    private boolean p4pSameHeight;
                    private String nid;
                    private String category;
                    private String pid;
                    private String title;
                    private String raw_title;
                    private String pic_url;
                    private String detail_url;
                    private String view_price;
                    private String view_fee;
                    private String item_loc;
                    private String view_sales;
                    private String comment_count;
                    private String user_id;
                    private String nick;
                    private ShopcardBean shopcard;
                    private boolean isHideIM;
                    private boolean isHideNick;
                    private String comment_url;
                    private String shopLink;
                    private I2iTagsBean i2iTags;
                    private String risk;
                    private List<IconBean> icon;
                    private List<?> p4pTags;

                    public static AuctionsBean objectFromData(String str) {

                        return new Gson().fromJson(str, AuctionsBean.class);
                    }

                    public int getP4p() {
                        return p4p;
                    }

                    public void setP4p(int p4p) {
                        this.p4p = p4p;
                    }

                    public boolean isP4pSameHeight() {
                        return p4pSameHeight;
                    }

                    public void setP4pSameHeight(boolean p4pSameHeight) {
                        this.p4pSameHeight = p4pSameHeight;
                    }

                    public String getNid() {
                        return nid;
                    }

                    public void setNid(String nid) {
                        this.nid = nid;
                    }

                    public String getCategory() {
                        return category;
                    }

                    public void setCategory(String category) {
                        this.category = category;
                    }

                    public String getPid() {
                        return pid;
                    }

                    public void setPid(String pid) {
                        this.pid = pid;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getRaw_title() {
                        return raw_title;
                    }

                    public void setRaw_title(String raw_title) {
                        this.raw_title = raw_title;
                    }

                    public String getPic_url() {
                        return pic_url;
                    }

                    public void setPic_url(String pic_url) {
                        this.pic_url = pic_url;
                    }

                    public String getDetail_url() {
                        return detail_url;
                    }

                    public void setDetail_url(String detail_url) {
                        this.detail_url = detail_url;
                    }

                    public String getView_price() {
                        return view_price;
                    }

                    public void setView_price(String view_price) {
                        this.view_price = view_price;
                    }

                    public String getView_fee() {
                        return view_fee;
                    }

                    public void setView_fee(String view_fee) {
                        this.view_fee = view_fee;
                    }

                    public String getItem_loc() {
                        return item_loc;
                    }

                    public void setItem_loc(String item_loc) {
                        this.item_loc = item_loc;
                    }

                    public String getView_sales() {
                        return view_sales;
                    }

                    public void setView_sales(String view_sales) {
                        this.view_sales = view_sales;
                    }

                    public String getComment_count() {
                        return comment_count;
                    }

                    public void setComment_count(String comment_count) {
                        this.comment_count = comment_count;
                    }

                    public String getUser_id() {
                        return user_id;
                    }

                    public void setUser_id(String user_id) {
                        this.user_id = user_id;
                    }

                    public String getNick() {
                        return nick;
                    }

                    public void setNick(String nick) {
                        this.nick = nick;
                    }

                    public ShopcardBean getShopcard() {
                        return shopcard;
                    }

                    public void setShopcard(ShopcardBean shopcard) {
                        this.shopcard = shopcard;
                    }

                    public boolean isIsHideIM() {
                        return isHideIM;
                    }

                    public void setIsHideIM(boolean isHideIM) {
                        this.isHideIM = isHideIM;
                    }

                    public boolean isIsHideNick() {
                        return isHideNick;
                    }

                    public void setIsHideNick(boolean isHideNick) {
                        this.isHideNick = isHideNick;
                    }

                    public String getComment_url() {
                        return comment_url;
                    }

                    public void setComment_url(String comment_url) {
                        this.comment_url = comment_url;
                    }

                    public String getShopLink() {
                        return shopLink;
                    }

                    public void setShopLink(String shopLink) {
                        this.shopLink = shopLink;
                    }

                    public I2iTagsBean getI2iTags() {
                        return i2iTags;
                    }

                    public void setI2iTags(I2iTagsBean i2iTags) {
                        this.i2iTags = i2iTags;
                    }

                    public String getRisk() {
                        return risk;
                    }

                    public void setRisk(String risk) {
                        this.risk = risk;
                    }

                    public List<IconBean> getIcon() {
                        return icon;
                    }

                    public void setIcon(List<IconBean> icon) {
                        this.icon = icon;
                    }

                    public List<?> getP4pTags() {
                        return p4pTags;
                    }

                    public void setP4pTags(List<?> p4pTags) {
                        this.p4pTags = p4pTags;
                    }

                    public static class ShopcardBean {
                        /**
                         * levelClasses : []
                         * isTmall : true
                         * delivery : [0,1,2758]
                         * description : [0,1,1198]
                         * service : [0,1,1800]
                         * encryptedUserId : UvFkLOmILvCxGMNTT
                         */

                        private boolean isTmall;
                        private String encryptedUserId;
                        private List<?> levelClasses;
                        private List<Integer> delivery;
                        private List<Integer> description;
                        private List<Integer> service;

                        public static ShopcardBean objectFromData(String str) {

                            return new Gson().fromJson(str, ShopcardBean.class);
                        }

                        public boolean isIsTmall() {
                            return isTmall;
                        }

                        public void setIsTmall(boolean isTmall) {
                            this.isTmall = isTmall;
                        }

                        public String getEncryptedUserId() {
                            return encryptedUserId;
                        }

                        public void setEncryptedUserId(String encryptedUserId) {
                            this.encryptedUserId = encryptedUserId;
                        }

                        public List<?> getLevelClasses() {
                            return levelClasses;
                        }

                        public void setLevelClasses(List<?> levelClasses) {
                            this.levelClasses = levelClasses;
                        }

                        public List<Integer> getDelivery() {
                            return delivery;
                        }

                        public void setDelivery(List<Integer> delivery) {
                            this.delivery = delivery;
                        }

                        public List<Integer> getDescription() {
                            return description;
                        }

                        public void setDescription(List<Integer> description) {
                            this.description = description;
                        }

                        public List<Integer> getService() {
                            return service;
                        }

                        public void setService(List<Integer> service) {
                            this.service = service;
                        }
                    }

                    public static class I2iTagsBean {
                        /**
                         * samestyle : {"url":""}
                         * similar : {"url":"/search?type=similar&app=i2i&rec_type=1&uniqpid=&nid=521505701681"}
                         */

                        private CustomizedconfigBean samestyle;
                        private CustomizedconfigBean similar;

                        public static I2iTagsBean objectFromData(String str) {

                            return new Gson().fromJson(str, I2iTagsBean.class);
                        }

                        public CustomizedconfigBean getSamestyle() {
                            return samestyle;
                        }

                        public void setSamestyle(CustomizedconfigBean samestyle) {
                            this.samestyle = samestyle;
                        }

                        public CustomizedconfigBean getSimilar() {
                            return similar;
                        }

                        public void setSimilar(CustomizedconfigBean similar) {
                            this.similar = similar;
                        }
                    }

                    public static class IconBean {
                        /**
                         * title : 38女王节
                         * dom_class : icon-fest-2018nvwangjie
                         * position : 0
                         * show_type : 0
                         * icon_category : baobei
                         * outer_text : 0
                         * html :
                         * icon_key : icon-fest-2018nvwangjie
                         * trace : srpservice
                         * traceIdx : 0
                         * innerText : 2018女王节
                         * url : //re.taobao.com/search?keyword=%B4%F2%D3%A1%BB%FA&refpid=420432_1006&frcatid=&
                         * iconPopupComplex : {"popup_title":"保险理赔","subIcons":[{"dom_class":"icon-service-yunfeixian","icon_content":"卖家赠送退货运费险"}]}
                         */

                        private String title;
                        private String dom_class;
                        private String position;
                        private String show_type;
                        private String icon_category;
                        private String outer_text;
                        private String html;
                        private String icon_key;
                        private String trace;
                        private int traceIdx;
                        private String innerText;
                        private String url;
                        private IconPopupComplexBean iconPopupComplex;

                        public static IconBean objectFromData(String str) {

                            return new Gson().fromJson(str, IconBean.class);
                        }

                        public String getTitle() {
                            return title;
                        }

                        public void setTitle(String title) {
                            this.title = title;
                        }

                        public String getDom_class() {
                            return dom_class;
                        }

                        public void setDom_class(String dom_class) {
                            this.dom_class = dom_class;
                        }

                        public String getPosition() {
                            return position;
                        }

                        public void setPosition(String position) {
                            this.position = position;
                        }

                        public String getShow_type() {
                            return show_type;
                        }

                        public void setShow_type(String show_type) {
                            this.show_type = show_type;
                        }

                        public String getIcon_category() {
                            return icon_category;
                        }

                        public void setIcon_category(String icon_category) {
                            this.icon_category = icon_category;
                        }

                        public String getOuter_text() {
                            return outer_text;
                        }

                        public void setOuter_text(String outer_text) {
                            this.outer_text = outer_text;
                        }

                        public String getHtml() {
                            return html;
                        }

                        public void setHtml(String html) {
                            this.html = html;
                        }

                        public String getIcon_key() {
                            return icon_key;
                        }

                        public void setIcon_key(String icon_key) {
                            this.icon_key = icon_key;
                        }

                        public String getTrace() {
                            return trace;
                        }

                        public void setTrace(String trace) {
                            this.trace = trace;
                        }

                        public int getTraceIdx() {
                            return traceIdx;
                        }

                        public void setTraceIdx(int traceIdx) {
                            this.traceIdx = traceIdx;
                        }

                        public String getInnerText() {
                            return innerText;
                        }

                        public void setInnerText(String innerText) {
                            this.innerText = innerText;
                        }

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }

                        public IconPopupComplexBean getIconPopupComplex() {
                            return iconPopupComplex;
                        }

                        public void setIconPopupComplex(IconPopupComplexBean iconPopupComplex) {
                            this.iconPopupComplex = iconPopupComplex;
                        }

                        public static class IconPopupComplexBean {
                            /**
                             * popup_title : 保险理赔
                             * subIcons : [{"dom_class":"icon-service-yunfeixian","icon_content":"卖家赠送退货运费险"}]
                             */

                            private String popup_title;
                            private List<SubIconsBean> subIcons;

                            public static IconPopupComplexBean objectFromData(String str) {

                                return new Gson().fromJson(str, IconPopupComplexBean.class);
                            }

                            public String getPopup_title() {
                                return popup_title;
                            }

                            public void setPopup_title(String popup_title) {
                                this.popup_title = popup_title;
                            }

                            public List<SubIconsBean> getSubIcons() {
                                return subIcons;
                            }

                            public void setSubIcons(List<SubIconsBean> subIcons) {
                                this.subIcons = subIcons;
                            }

                            public static class SubIconsBean {
                                /**
                                 * dom_class : icon-service-yunfeixian
                                 * icon_content : 卖家赠送退货运费险
                                 */

                                private String dom_class;
                                private String icon_content;

                                public static SubIconsBean objectFromData(String str) {

                                    return new Gson().fromJson(str, SubIconsBean.class);
                                }

                                public String getDom_class() {
                                    return dom_class;
                                }

                                public void setDom_class(String dom_class) {
                                    this.dom_class = dom_class;
                                }

                                public String getIcon_content() {
                                    return icon_content;
                                }

                                public void setIcon_content(String icon_content) {
                                    this.icon_content = icon_content;
                                }
                            }
                        }
                    }
                }
            }
        }

        public static class GridBean {
            private String status;
            private DataBeanX data;

            public static GridBean objectFromData(String str) {

                return new Gson().fromJson(str, GridBean.class);
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public DataBeanX getData() {
                return data;
            }

            public void setData(DataBeanX data) {
                this.data = data;
            }

            public static class DataBeanX {
                private List<SpusBean> spus;

                public static DataBeanX objectFromData(String str) {

                    return new Gson().fromJson(str, DataBeanX.class);
                }

                public List<SpusBean> getSpus() {
                    return spus;
                }

                public void setSpus(List<SpusBean> spus) {
                    this.spus = spus;
                }

                public static class SpusBean {

                    private String cat;
                    private String title;
                    private String pic_url;
                    private String price;
                    private String trace;
                    private String importantKey;
                    private String month_sales;
                    private SellerBean seller;
                    private String url;
                    private int cmt_count;
                    private String tag;
                    private ActivityBean activity;
                    private List<?> specialTag;
                    private List<TagInfoBean> tag_info;

                    public static SpusBean objectFromData(String str) {

                        return new Gson().fromJson(str, SpusBean.class);
                    }

                    public String getCat() {
                        return cat;
                    }

                    public void setCat(String cat) {
                        this.cat = cat;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getPic_url() {
                        return pic_url;
                    }

                    public void setPic_url(String pic_url) {
                        this.pic_url = pic_url;
                    }

                    public String getPrice() {
                        return price;
                    }

                    public void setPrice(String price) {
                        this.price = price;
                    }

                    public String getTrace() {
                        return trace;
                    }

                    public void setTrace(String trace) {
                        this.trace = trace;
                    }

                    public String getImportantKey() {
                        return importantKey;
                    }

                    public void setImportantKey(String importantKey) {
                        this.importantKey = importantKey;
                    }

                    public String getMonth_sales() {
                        return month_sales;
                    }

                    public void setMonth_sales(String month_sales) {
                        this.month_sales = month_sales;
                    }

                    public SellerBean getSeller() {
                        return seller;
                    }

                    public void setSeller(SellerBean seller) {
                        this.seller = seller;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public int getCmt_count() {
                        return cmt_count;
                    }

                    public void setCmt_count(int cmt_count) {
                        this.cmt_count = cmt_count;
                    }

                    public String getTag() {
                        return tag;
                    }

                    public void setTag(String tag) {
                        this.tag = tag;
                    }

                    public ActivityBean getActivity() {
                        return activity;
                    }

                    public void setActivity(ActivityBean activity) {
                        this.activity = activity;
                    }

                    public List<?> getSpecialTag() {
                        return specialTag;
                    }

                    public void setSpecialTag(List<?> specialTag) {
                        this.specialTag = specialTag;
                    }

                    public List<TagInfoBean> getTag_info() {
                        return tag_info;
                    }

                    public void setTag_info(List<TagInfoBean> tag_info) {
                        this.tag_info = tag_info;
                    }

                    public static class SellerBean {

                        private String trace;
                        private String url;
                        private String num;

                        public static SellerBean objectFromData(String str) {

                            return new Gson().fromJson(str, SellerBean.class);
                        }

                        public String getTrace() {
                            return trace;
                        }

                        public void setTrace(String trace) {
                            this.trace = trace;
                        }

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }

                    public static class ActivityBean {

                        private String trace;
                        private String url;
                        private String text;
                        private String iconClass;

                        public static ActivityBean objectFromData(String str) {

                            return new Gson().fromJson(str, ActivityBean.class);
                        }

                        public String getTrace() {
                            return trace;
                        }

                        public void setTrace(String trace) {
                            this.trace = trace;
                        }

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }

                        public String getText() {
                            return text;
                        }

                        public void setText(String text) {
                            this.text = text;
                        }

                        public String getIconClass() {
                            return iconClass;
                        }

                        public void setIconClass(String iconClass) {
                            this.iconClass = iconClass;
                        }
                    }

                    public static class TagInfoBean {
                        /**
                         * tag : Exynos疾速游戏芯片
                         */

                        private String tag;

                        public static TagInfoBean objectFromData(String str) {

                            return new Gson().fromJson(str, TagInfoBean.class);
                        }

                        public String getTag() {
                            return tag;
                        }

                        public void setTag(String tag) {
                            this.tag = tag;
                        }
                    }
                }
            }
        }

        public static class RelatedBean {

            private String status;
            private DataBeanXX data;

            public static RelatedBean objectFromData(String str) {

                return new Gson().fromJson(str, RelatedBean.class);
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public DataBeanXX getData() {
                return data;
            }

            public void setData(DataBeanXX data) {
                this.data = data;
            }

            public static class DataBeanXX {
                private List<WordsBean> words;

                public static DataBeanXX objectFromData(String str) {

                    return new Gson().fromJson(str, DataBeanXX.class);
                }

                public List<WordsBean> getWords() {
                    return words;
                }

                public void setWords(List<WordsBean> words) {
                    this.words = words;
                }

                public static class WordsBean {

                    private String text;
                    private boolean isHighlight;
                    private String href;

                    public static WordsBean objectFromData(String str) {

                        return new Gson().fromJson(str, WordsBean.class);
                    }

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }

                    public boolean isIsHighlight() {
                        return isHighlight;
                    }

                    public void setIsHighlight(boolean isHighlight) {
                        this.isHighlight = isHighlight;
                    }

                    public String getHref() {
                        return href;
                    }

                    public void setHref(String href) {
                        this.href = href;
                    }
                }
            }
        }
    }
}
