package top.ivan.griddle.griddle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.ivan.griddle.Griddle;
import top.ivan.griddle.focus.TestFocus;

import java.util.*;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/1
 */
public class FIFOGriddle implements Griddle {
    List<GriddleAisle> aisleList;
    private static Logger logger = LogManager.getLogger(FIFOGriddle.class);

    public FIFOGriddle(List<GriddleAisle> aisleList) {
        this.aisleList = aisleList;
    }

    public FIFOGriddle(GriddleAisle aisle) {
        aisleList = new LinkedList<>();
        aisleList.add(aisle);
    }

    @Override
    public Map<String, String> doFilter(String src, Map<String, String> keyMap) {
        Map<String, String> retMap = new LinkedHashMap<>();
        if(keyMap != null) {
            retMap.putAll(keyMap);
        }
        if(null != TestFocus.nullValue(src)) {
            GriddleAisle aisle;
            for (int i = 0; i < aisleList.size(); i++) {
                aisle = aisleList.get(i);
                try {
                    retMap.put(aisle.getPeek(), aisle.pass(src, retMap));
                } catch (Exception e) {
                    logger.error("filter error:",e);
                    retMap.put(aisle.getPeek(), aisle.getDefaultValue());
                }
            }
        }
//        removeSimileKey(keyMap,retMap);
        return retMap;
    }

    public void removeSimileKey(Map<String,String> src,Map<String,String> target) {
        Iterator iterator = src.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,String> entry = (Map.Entry) iterator.next();
            target.remove(entry.getKey(),entry.getValue());
/*            String targetValue = target.get(entry.getKey());
            if(null != entry.getValue() && entry.getValue().equals(targetValue)) {

            }*/
        }
    }

    public void setAisles(List<GriddleAisle> aisleList) {
        this.aisleList = aisleList;
    }

    public void addAisle(GriddleAisle aisle) {
        this.aisleList.add(aisle);
    }

}
