package top.ivan.sagittarius.web.view;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;
import top.ivan.sagittarius.web.controller.ItemsController;
import top.ivan.sagittarius.web.vo.ProductListVo;
import top.ivan.sagittarius.web.vo.SearchHandle;

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
    public ModelAndView search(SearchHandle handle) {
        if(handle.getSearchLine() == null || handle.getSearchLine().length() == 0) {
            return new ModelAndView("redirect:/index");
        }
        ModelAndView view = new ModelAndView("search");
        PageQuery<ProductPreview> pageQuery = itemsController.searchTitle(handle);
        List<ProductPreview> previewList = pageQuery.getList();
        List<ProductListVo> voList = previewList.stream().map(ProductListVo::new).collect(Collectors.toList());
        view.addObject("productList",voList);
        view.addObject("totalCount",pageQuery.getTotalRow());
        view.addObject("fromSearch",true);
        view.addObject("search",handle.getSearchLine());
        return view;
    }
}
