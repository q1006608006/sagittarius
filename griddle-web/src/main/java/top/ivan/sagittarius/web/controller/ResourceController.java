package top.ivan.sagittarius.web.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.ivan.sagittarius.griddle.persist.dao.EventDao;
import top.ivan.sagittarius.griddle.persist.pojo.Event;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/resource", method = RequestMethod.POST)
public class ResourceController implements InitializingBean {
    public static final String FILE_PATH = ResourceController.class.getResource("/").getPath() + "../..";
    public static List<Event> events;
    @Autowired
    private EventDao eventDao;

    @RequestMapping("")
    @ResponseBody
    public Map<String, Object> indexAddition(MultipartFile eventImgFile, String eventImgUrl, String eventTitle, String eventLink, Integer eventIndex) {
        Map<String, Object> ret = new HashMap<>();
        if (eventIndex == null || eventIndex < 1 || eventIndex > 4) {
            ret.put("msg", "超能陆战队了解一下");
            return ret;
        }
        Event event = new Event();
        event.setIndex(eventIndex);
        event = eventDao.templateOne(event);
        event.setTitle(eventTitle);
        event.setLink(eventLink);

        try {
            if (StringUtils.isNotEmpty(eventImgUrl)) {
                if ("default".equalsIgnoreCase(eventImgUrl)) {
                    event.setPicUrl(event.getDefaultPic());
                } else {
                    event.setPicUrl(eventImgUrl);
                }
            } else if (eventImgFile.getSize() > 0) {
                event.setPicUrl("resource/" + event.getDefaultPic());
                saveImgFile(eventImgFile, event.getPicUrl());
            }
        } catch (Exception e) {
            ret.put("msg", "我不是，我没有。。。");
            return ret;
        }
        eventDao.updateById(event);
        afterPropertiesSet();
        ret.put("msg", "更新成功");
        ret.put("event", event);
        return ret;
    }


    @RequestMapping("events")
    @ResponseBody
    public List<Event> getAdditions() {
        return events;
    }


    private void saveImgFile(MultipartFile file, String relativePath) throws IOException {
        File localFile = new File(FILE_PATH, relativePath);
        if (!localFile.getParentFile().exists()) {
            localFile.getParentFile().mkdirs();
        }
        file.transferTo(localFile);
    }

    @Override
    public void afterPropertiesSet() {
        List<Event> eventList = eventDao.all();
        eventList.sort(Comparator.comparing(Event::getIndex));
        events = eventList;
    }
}
