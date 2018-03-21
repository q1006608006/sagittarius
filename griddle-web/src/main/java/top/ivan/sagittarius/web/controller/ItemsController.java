package top.ivan.sagittarius.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ivan.sagittarius.griddle.persist.dao.ProductPreviewDao;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;
import top.ivan.sagittarius.web.service.ItemsService;
import top.ivan.sagittarius.web.service.ProductService;
import top.ivan.sagittarius.web.vo.ProductTraceVo;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/api/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;
    @Autowired
    private ProductPreviewDao productPreviewDao;
    @Autowired
    private ProductService productService;

    @RequestMapping("/list")
    @ResponseBody
    public List<ProductPreview> listProduct() {
        List<ProductPreview> previews = productPreviewDao.all(1, 10);
        return previews;
    }

    @RequestMapping("/hots")
    @ResponseBody
    public List<ProductPreview> mostHodProducts(Integer page, Integer size) {
        page = validatePage(page);
        size = validateSize(size);
        return itemsService.getMostHotProduct(page, size);
    }

    @RequestMapping("/reduces")
    @ResponseBody
    public List<ProductTraceVo> mostReduceProduct(Integer page, Integer size) {
        page = validatePage(page);
        size = validateSize(size);
        return itemsService.getMostReduceProduct(page, size);
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<ProductPreview> searchTitle(String search,Integer page,Integer size) {
        page = validatePage(page);
        size = validateSize(size);
        if(search == null) {
            return Collections.emptyList();
        } else if(search.length() <= 1) {
            return productPreviewDao.containsTitle(search,page,size);
        }
        return productPreviewDao.searchTitle(search,page,size);
    }

    private Integer validatePage(Integer page) {
        if(page == null || page < 0) {
            return 0;
        }
        return page;
    }

    private Integer validateSize(Integer size) {
        if(size == null || size < 1) {
            return 10;
        }
        return size;
    }
}
