package top.ivan.sagittarius.screen.parse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.parse.focus.TestFocus;

import java.util.*;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/1
 */
public class FIFOGriddle implements Griddle {
    private static final long serialVersionUID = 5327934665931938216L;
    private List<GriddleAisle> aisleList;
    private static Logger logger = LogManager.getLogger(FIFOGriddle.class);

    public FIFOGriddle(List<GriddleAisle> aisleList) {
        this.aisleList = aisleList;
    }

    public FIFOGriddle(GriddleAisle aisle) {
        aisleList = new LinkedList<>();
        aisleList.add(aisle);
    }

    @Override
    public Map<String, String> doFilter(Seed seed) {
        long timestamp = System.currentTimeMillis();
        Map<String, String> retMap = new LinkedHashMap<>();
        loadMap(retMap, seed);
        if (null != TestFocus.nullValue(seed.getBody())) {
            GriddleAisle aisle;
            for (int i = 0; i < aisleList.size(); i++) {
                aisle = aisleList.get(i);
                try {
                    retMap.put(aisle.getPeek(), aisle.pass(seed.getBody(), retMap));
                } catch (Exception e) {
                    if (e instanceof UnParsedException) {
                        logger.error("filter '" + aisle.getPeek() + "' error" +
                                ",position: " + ((UnParsedException) e).getErrorChainInfo() +
                                String.format(",params:{key='%s',target='%s'}", ((UnParsedException) e).getErrorKey(), ((UnParsedException) e).getErrorTarget()) +
                                ",type: [" + e.getCause().getClass() + "]" +
                                ", info: [" + e.getLocalizedMessage() + "]");
                    } else {
                        logger.error("filter '" + aisle.getPeek() + "' error,type: [" + e.getClass() + "], info: [" + e.getLocalizedMessage() + "]");
                    }
                    retMap.put(aisle.getPeek(), aisle.getDefaultValue() == null ? e.getClass().toString().substring(6) : aisle.getDefaultValue());
                }
            }
        }
        long timestampE = System.currentTimeMillis();
        logger.info(String.format("filter %s end,rule: '%s',start: %d,cost %dms", seed.toString(), seed.getLocation() + "." + seed.getSpread().getRuleId(), timestamp, timestampE - timestamp));
//        removeSimileKey(keyMap,retMap);
        return retMap;
    }

    private void loadMap(Map<String, String> src, Seed seed) {
        src.put("$location", seed.getLocation());
        src.put("$url", seed.getUrl());
        src.put("$baseUrl", seed.getBaseUrl());
        Map<String, String> exMap;
        if ((exMap = seed.getSite().getStorage()) != null) {
            src.putAll(exMap);
        }
        if ((exMap = seed.getSpread().getRule().getField()) != null) {
            src.putAll(exMap);
        }
    }


    public void removeSimileKey(Map<String, String> src, Map<String, String> target) {
        Iterator iterator = src.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) iterator.next();
            target.remove(entry.getKey(), entry.getValue());
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
