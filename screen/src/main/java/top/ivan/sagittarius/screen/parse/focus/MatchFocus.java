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


    public static void test2() {
        String url = "3c.tmall.com?go=act&acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561662186585_708026";
        System.out.println(matchList(url,regex));
    }
    public static void test1() {

        String urls = "{\n" +
                "    \"categoryMainLines\": [\n" +
                "        {\n" +
                "            \"action1\": \"//nvzhuang.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561681423980_708026\",\n" +
                "            \"action2\": \"//neiyi.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561681423980_708026\",\n" +
                "            \"action3\": \"\",\n" +
                "            \"title1\": \"女装\",\n" +
                "            \"title2\": \"内衣\",\n" +
                "            \"title3\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"action1\": \"//nanzhuang.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561677576501_708026\",\n" +
                "            \"action2\": \"//sports.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561677576501_708026\",\n" +
                "            \"action3\": \"\",\n" +
                "            \"title1\": \"男装\",\n" +
                "            \"title2\": \"运动户外\",\n" +
                "            \"title3\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"action1\": \"//nvxie.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561689118972_708026\",\n" +
                "            \"action2\": \"//nanxie.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561689118972_708026\",\n" +
                "            \"action3\": \"//bag.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561689118972_708026\",\n" +
                "            \"title1\": \"女鞋\",\n" +
                "            \"title2\": \"男鞋\",\n" +
                "            \"title3\": \"箱包\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"action1\": \"//list.tmall.com/search_product.htm?q=%B9%D9%B7%BD%D6%B1%CA%DB&spm=875.7931836/B.subpannel2016034.1.GU94AS&vmarket=29890&style=w&theme=275&acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561685271493_708026&smAreaId=330100\",\n" +
                "            \"action2\": \"//list.tmall.com/search_product.htm?q=%B9%D9%B7%BD%D6%B1%CA%DB&spm=875.7931836/B.subpannel2016034.1.GU94AS&vmarket=29890&style=w&theme=275&acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561685271493_708026&smAreaId=330100\",\n" +
                "            \"action3\": \"\",\n" +
                "            \"title1\": \"美妆\",\n" +
                "            \"title2\": \"个人护理\",\n" +
                "            \"title3\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"action1\": \"//watch.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561666034064_708026\",\n" +
                "            \"action2\": \"//list.tmall.com/search_product.htm?abbucket=&active=1&acm=lb-zebra-148799-667863.1003.4.708026&sort=s&spm=3.7396704.20000007.22.7CvfAH&abtest=&pos=3&cat=50023064&theme=469&from=sn_1_rightnav&style=g&search_condition=7&scm=1003.4.lb-zebra-148799-667863.OTHER_14561666034064_708026&aldid=75994#J_crumbs\",\n" +
                "            \"action3\": \"//dai.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561666034064_708026\",\n" +
                "            \"title1\": \"腕表\",\n" +
                "            \"title2\": \"眼镜\",\n" +
                "            \"title3\": \"珠宝饰品\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"action1\": \"//shouji.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561662186585_708026\",\n" +
                "            \"action2\": \"//3c.tmall.com?go=act&acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561662186585_708026\",\n" +
                "            \"action3\": \"//3c.tmall.com/?go=digt&acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561662186585_708026\",\n" +
                "            \"title1\": \"手机\",\n" +
                "            \"title2\": \"数码\",\n" +
                "            \"title3\": \"电脑办公\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"action1\": \"//baby.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561673729066_708026\",\n" +
                "            \"action2\": \"\",\n" +
                "            \"action3\": \"\",\n" +
                "            \"title1\": \"母婴玩具\",\n" +
                "            \"title2\": \"\",\n" +
                "            \"title3\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"action1\": \"//food.tmall.com/?spm=3.7396704.20000007.14.7CvfAH&abtest=&abbucket=&pos=1&acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561669881597_708026&aldid=75999\",\n" +
                "            \"action2\": \"//food.tmall.com/?spm=3.7396704.20000007.16.7CvfAH&abtest=&abbucket=&pos=3&acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561669881597_708026&aldid=75999#J_MuiLiftPannel4https://food.tmall.com/?spm=3.7396704.20000007.15.7CvfAH&abbucket=&acm=tt-1141518-37004.1003.8.75999&aldid=75999&abtest=&scm=1003.8.tt-1141518-37004.OTHER_1431079495858_75999&pos=2#J_MuiLiftPannel1\",\n" +
                "            \"action3\": \"//food.tmall.com/?spm=3.7396704.20000007.15.7CvfAH&abtest=&abbucket=&pos=2&acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561669881597_708026&aldid=75999#J_MuiLiftPannel1\",\n" +
                "            \"title1\": \"零食\",\n" +
                "            \"title2\": \"茶酒\",\n" +
                "            \"title3\": \"进口食品\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"action1\": \"//miao.tmall.com?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14593834779268_708026\",\n" +
                "            \"action2\": \"\",\n" +
                "            \"action3\": \"\",\n" +
                "            \"title1\": \"生鲜水果\",\n" +
                "            \"title2\": \"\",\n" +
                "            \"title3\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"action1\": \"//3c.tmall.com?go=appl&acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561650644158_708026\",\n" +
                "            \"action2\": \"//3c.tmall.com?go=kich&acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561650644158_708026\",\n" +
                "            \"action3\": \"\",\n" +
                "            \"title1\": \"大家电\",\n" +
                "            \"title2\": \"生活电器\",\n" +
                "            \"title3\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"action1\": \"//jia.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_14561646796679_708026\",\n" +
                "            \"action2\": \"\",\n" +
                "            \"action3\": \"\",\n" +
                "            \"title1\": \"家具建材\",\n" +
                "            \"title2\": \"\",\n" +
                "            \"title3\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"action1\": \"//car.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_145616583391510_708026\",\n" +
                "            \"action2\": \"//list.tmall.com/search_product.htm?abbucket=&active=1&acm=lb-zebra-148799-667863.1003.4.708026&industryCatId=50660004&uuid=75987&spm=875.7789098.20150017.3.pPRs0I&abtest=&pos=11&cat=56772006&style=g&from=sn_1_rightnav&search_condition=55&scm=1003.4.lb-zebra-148799-667863.OTHER_145616583391510_708026&aldid=431510#J_crumbs\",\n" +
                "            \"action3\": \"//list.tmall.com/search_product.htm?abbucket=&active=1&acm=lb-zebra-148799-667863.1003.4.708026&industryCatId=50660004&uuid=75987&spm=875.7789098.20150017.3.P1jZNx&abtest=&pos=11&cat=56838011&style=g&from=sn_1_rightnav&search_condition=55&scm=1003.4.lb-zebra-148799-667863.OTHER_145616583391510_708026&aldid=431510#J_crumbs\",\n" +
                "            \"title1\": \"汽车\",\n" +
                "            \"title2\": \"配件\",\n" +
                "            \"title3\": \"用品\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"action1\": \"//myhome.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_145616544916711_708026\",\n" +
                "            \"action2\": \"//myhome.tmall.com/?spm=3.7396704.20000007.27.7CvfAH&abtest=&act=4,2&abbucket=&pos=2&acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_145616544916711_708026&aldid=74660\",\n" +
                "            \"action3\": \"//list.tmall.com/search_product.htm?spm=a220m.1000858.0.0.d811797DgxXDC&cat=50024897&style=g&from=sn_1_rightnav&acm=lb-zebra-148799-667863.1003.4.708026&sort=s&search_condition=7&scm=1003.4.lb-zebra-148799-667863.OTHER_145616544916711_708026&industryCatId=50024897&smAreaId=330100#J_crumbs\",\n" +
                "            \"title1\": \"家纺\",\n" +
                "            \"title2\": \"家饰\",\n" +
                "            \"title3\": \"鲜花\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"action1\": \"//yao.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_145616352542412_708026\",\n" +
                "            \"action2\": \"\",\n" +
                "            \"action3\": \"\",\n" +
                "            \"title1\": \"医药保健\",\n" +
                "            \"title2\": \"\",\n" +
                "            \"title3\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"action1\": \"//list.tmall.com/search_product.htm?spm=a220m.1000858.0.0.v7yFGa&cat=50036640&active=1&style=g&from=sn_1_rightnav&acm=lb-zebra-148799-667863.1003.4.708026&sort=s&search_condition=23&tmhkmain=0&scm=1003.4.lb-zebra-148799-667863.OTHER_145616314067613_708026&industryCatId=50036640&smAreaId=330100#J_crumbs\",\n" +
                "            \"action2\": \"//list.tmall.com/search_product.htm?abbucket=&active=1&acm=lb-zebra-148799-667863.1003.4.708026&sort=s&industryCatId=50071816&spm=3.7396704.20000007.31.7CvfAH&abtest=&pos=2&cat=50071786&from=sn_1_rightnav&style=g&search_condition=7&scm=1003.4.lb-zebra-148799-667863.OTHER_145616314067613_708026&aldid=75975#J_crumbs\",\n" +
                "            \"action3\": \"//list.tmall.com/search_product.htm?abbucket=&active=1&acm=lb-zebra-148799-667863.1003.4.708026&sort=s&industryCatId=50043495&uuid=92196&spm=3.7396704.20000007.32.7CvfAH&abtest=&pos=3&cat=50034368&from=sn_1_rightnav&style=g&search_condition=7&scm=1003.4.lb-zebra-148799-667863.OTHER_145616314067613_708026&aldid=75975#J_crumbs\",\n" +
                "            \"title1\": \"厨具\",\n" +
                "            \"title2\": \"收纳\",\n" +
                "            \"title3\": \"宠物\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"action1\": \"//book.tmall.com/?acm=lb-zebra-148799-667863.1003.4.708026&scm=1003.4.lb-zebra-148799-667863.OTHER_145616429492414_708026\",\n" +
                "            \"action2\": \"\",\n" +
                "            \"action3\": \"\",\n" +
                "            \"title1\": \"图书音像\",\n" +
                "            \"title2\": \"\",\n" +
                "            \"title3\": \"\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        System.out.println(urls.matches(regex));
        matchList(urls,regex).forEach(System.out::println);
    }

    static String regex = "((http|ftp|https)://)?(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
    public static void main(String[] args) {
        test2();
    }
}
