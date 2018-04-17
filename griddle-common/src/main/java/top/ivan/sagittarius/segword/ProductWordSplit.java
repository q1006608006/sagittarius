package top.ivan.sagittarius.segword;

import org.apdplat.word.dictionary.Dictionary;
import org.apdplat.word.dictionary.DictionaryFactory;
import org.apdplat.word.util.WordConfTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductWordSplit implements InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(ProductWordSplit.class);

    private static BoundSetOperations<String, String> setOperations;

    private static final String WORD_SET = "ivan.segword.wordset";
    private static final Pattern EN_PAT = Pattern.compile("[0-9-a-zA-Z_#]{2,}");
    ;

    public void setSetOperations(RedisTemplate<String, String> redisTemplate) {
        ProductWordSplit.setOperations = redisTemplate.boundSetOps(WORD_SET);
    }

    public static Set<String> segLine(String line) {
        Set<String> stringSet = new LinkedHashSet<>();
        segLine(line, stringSet);
        return stringSet;
    }

    public static void segLine(String line, Set<String> stringSet) {
        List<String> shortLine = prepareShortLine(line);
        for (String s : shortLine) {
            segShortLine(s, stringSet);
        }
    }

    public static Set<String> segShortLine(String shortLine) {
        Set<String> stringSet = new LinkedHashSet<>();
        segShortLine(shortLine, stringSet);
        return stringSet;
    }

    public static void segShortLine(String shortLine, Set<String> stringSet) {
        List<String> stringList = WordAnalysis.segWords(shortLine);
        for (String word : stringList) {
            if (word.length() > 1) {
                stringSet.add(word);
            }
        }
    }

    public static List<String> prepareShortLine(String value) {
//        value = value.replaceAll("([a-zA-Z0-9\\-/_+]{2,})", " $1 ");
        value = value.replaceAll("[/\\\\()|]", " ");
        value = value.replaceAll("[^\\u4e00-\\u9fa5a-zA-Z0-9-/_|\\\\]", " ");
        String[] strs = value.split(" ");
        return Arrays.asList(strs);
    }

    public static Set<String> segAndCachedForMultiMatch(String line) {
        Set<String> stringSet = new TreeSet<>();
        segAndCachedForMultiMatch(line, stringSet);
        return stringSet;
    }

    public static void segAndCachedForMultiMatch(String line, Set<String> stringSet) {
        List<String> shortLine = prepareShortLine(line);
        for (String s : shortLine) {
            segShortLine(s, stringSet);
        }
        stringSet.addAll(shortLine);
        segWithoutCNWords(line, stringSet);
        cacheWord(stringSet);
    }

    public static Set<String> segAndCachedForShortestWord(String line) {
        Set<String> stringSet = new TreeSet<>();
        segAndCachedForShortestWord(line, stringSet);
        return stringSet;
    }

    public static void segAndCachedForShortestWord(String line, Set<String> stringSet) {
        List<String> shortLine = prepareShortLine(line);
        for (String s : shortLine) {
            segShortLine(s, stringSet);
        }
        cacheWord(stringSet);
    }

    public static Set<String> segWithoutCNWords(String line) {
        Set<String> stringSet = new TreeSet<>();
        segWithoutCNWords(line, stringSet);
        return stringSet;
    }

    public static void segWithoutCNWords(String line, Set<String> stringSet) {
        Matcher matcher = EN_PAT.matcher(line);
        while (matcher.find()) {
            stringSet.add(matcher.group());
        }
    }

    public static long cacheWord(Collection<String> collection) {
        long size = 0;
        for (String s : collection) {
            if (setOperations.add(s) > 0) {
                DictionaryFactory.getDictionary().add(s);
                size++;
            }
        }
        return size;
    }

    public static void main(String[] args) {
        System.out.println(segWithoutCNWords("艹iphone-8x这是"));
/*        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/application-*.xml");
        ProductPreviewDao dao = context.getBean(ProductPreviewDao.class);
//        Set<String> keyWords = getSegLineWords("一加5T全面屏手机");
        Set<String> keyWords = new TreeSet<>(Arrays.asList("一加 5t 全面屏 手机".split(" ")));
        System.out.println(keyWords);
        PageQuery<ProductPreview> page = new PageQuery<>();
        page.setPageNumber(9);
        page.setPageSize(10);
        dao.searchTitleByKeyWords(page, keyWords);
        System.out.println(page.getList());*/
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        long size = setOperations.size(), curMillion = System.currentTimeMillis();
        String value;
        logger.info("{} words in redis cache,start load into dictionary in {}", size, curMillion);
        Set<String> words = setOperations.members();
        WordConfTools.set("dic.class", "org.apdplat.word.dictionary.impl.DictionaryTrie");
        Dictionary dictionary = DictionaryFactory.getDictionary();
        dictionary.clear();
        dictionary.addAll(new ArrayList<>(words));
        logger.debug("load words end,cost time {}ms", System.currentTimeMillis() - curMillion);
/*        for (long i = 0; i < size; i++) {
            value = setOperations.pop();
            DictionaryFactory.getDictionary().add(value);
            tempSetOperations.add(value);
        }
        logger.debug("load words end,cost time {}ms",System.currentTimeMillis() - curMillion);
        for (long i = 0; i < size; i++) {
            value = tempSetOperations.pop();
            setOperations.add(value);
        }*/
    }
}
