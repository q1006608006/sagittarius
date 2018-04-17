package top.ivan.sagittarius.web.vo;

import top.ivan.sagittarius.web.choose.OrderModel;
import top.ivan.sagittarius.web.choose.SearchModel;

import java.math.BigDecimal;

public class SearchHandle {
    private String searchLine;
    private int pageNum = 1;
    private int pageSize = 20;
    private SearchModel model = SearchModel.MaxMatch;
    private OrderModel orderPoint = OrderModel.def;
    private boolean inDescOrder;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;

    public SearchHandle() {
    }

    public SearchHandle(String searchLine, int pageNum, int pageSize, SearchModel model) {
        this.searchLine = searchLine;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.model = model;
    }

    public void setOrderBy(OrderModel orderPoint,boolean inDescOrder) {
        this.orderPoint = orderPoint;
        this.inDescOrder = inDescOrder;
    }

    public String getSearchLine() {
        return searchLine;
    }

    public void setSearchLine(String searchLine) {
        this.searchLine = searchLine;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public SearchModel getModel() {
        return model;
    }

    public void setModel(SearchModel model) {
        this.model = model;
    }

    public String getOrderPoint() {
        return orderPoint.getOrder();
    }

    public void setOrderPoint(OrderModel orderPoint) {
        this.orderPoint = orderPoint;
    }

    public boolean isInDescOrder() {
        return inDescOrder;
    }

    public void setInDescOrder(boolean inDescOrder) {
        this.inDescOrder = inDescOrder;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }
}
