package top.ivan.sagittarius.web.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;
import top.ivan.sagittarius.web.controller.ItemsController;
import top.ivan.sagittarius.web.vo.ProductListVo;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/search")
public class SearchViewController {

    @Autowired
    private ItemsController itemsController;
    @Autowired
    private IndexViewController indexViewController;

    @RequestMapping("")
    public ModelAndView search(String search,Integer page,Integer size) {
        if(search == null || search.length() == 0) {
            return new ModelAndView("redirect:/index");
        }
        ModelAndView view = new ModelAndView("search");
        view.addObject("fromSearch",true);
        view.addObject("search",search);
        List<ProductPreview> previewList = itemsController.searchTitle(search,page,size);
        List<ProductListVo> voList = previewList.stream().map(ProductListVo::new).collect(Collectors.toList());
        view.addObject("productList",voList);
        return view;
    }
}
