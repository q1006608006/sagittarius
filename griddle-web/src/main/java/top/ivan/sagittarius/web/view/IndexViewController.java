package top.ivan.sagittarius.web.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;
import top.ivan.sagittarius.web.controller.ItemsController;
import top.ivan.sagittarius.web.controller.ResourceController;

@Controller
@RequestMapping("/")
public class IndexViewController {
    @Autowired
    private ItemsController itemsController;
    @Autowired
    private ResourceController resourceController;

    @RequestMapping({"","index","index.html"})
    public ModelAndView mainView() {
        ModelAndView view = new ModelAndView("index");
        view.addObject("hotItems",itemsController.mostHodProducts(0,10));
        view.addObject("events",resourceController.getAdditions());
        view.addObject("reduceItems",itemsController.mostReduceProduct(0,36));
        return view;
    }

    @RequestMapping("otherEdit")
    public ModelAndView otherEditView() {
        ModelAndView view = new ModelAndView("otherEdit");
        view.addObject("events",resourceController.getAdditions());
        return view;
    }
}
