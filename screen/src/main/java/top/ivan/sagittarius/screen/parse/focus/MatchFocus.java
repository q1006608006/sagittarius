package top.ivan.sagittarius.screen.parse.focus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchFocus implements Focus {
    private static final long serialVersionUID = -626248192265717104L;

    @Override
    public String peek(String src, String target, String key) throws Exception {
        if(null == key) {
            key = target;
        }
        List<String> result = matchList(src,key);
        return JsonFocus.toJson(result);
    }

    public static List<String> matchList(String src,String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(src);
        List<String> resultList = new ArrayList<>();
        while (matcher.find()) {
            String matchStr = matcher.group();
            resultList.add(matchStr);
        }
        return resultList;
    }

}
