package top.ivan.sagittarius.fetch.parts;

import top.ivan.sagittarius.griddle.persist.pojo.Product;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.operator.persist.PersistOperator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbsProductPersist implements PersistOperator {

    @Override
    public void process(Seed item) {
        List<Map<String, String>> items = getKeepItems(item);
        doPersist(getProducts(items));
    }

    public List<Product> getProducts(List<Map<String, String>> itemMaps) {
        return itemMaps.stream().map(this::getProduct).collect(Collectors.toList());
    }

    public Product getProduct(Map<String, String> item) {
        Product product = new Product();
        product.setLocation(item.get("$location"));
        product.setSourceUrl(item.get("$url"));
        product.setArtNo(item.get("artNo"));
        product.setBrand(item.get("brand"));
        product.setDescription(item.get("description"));
        product.setModelNo(item.get("modelNo"));
        if(item.get("price") != null) {
            product.setPrice(BigDecimal.valueOf(Double.valueOf(item.get("price"))));
        }
        product.setProductName(item.get("productName"));
        product.setProductTitle(item.get("productTitle"));
        product.setType(item.get("type"));
        return product;
    }


    public abstract List<Map<String, String>> getKeepItems(Seed item);

    public abstract void doPersist(List<Product> products);
}
