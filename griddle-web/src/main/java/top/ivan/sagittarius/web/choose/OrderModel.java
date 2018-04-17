package top.ivan.sagittarius.web.choose;

public enum OrderModel {

    price("viewPrice"),comment("commentCount"),def;

    private String order;

    OrderModel(String value) {
        this.order = value;
    }

    OrderModel(){
    }

    public String getOrder() {
        return order;
    }
}
