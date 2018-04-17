package top.ivan.sagittarius.segword;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.dictionary.DictionaryFactory;
import org.apdplat.word.segmentation.PartOfSpeech;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;
import org.apdplat.word.tagging.PartOfSpeechTagging;
import org.apdplat.word.util.WordConfTools;

import java.util.*;
import java.util.stream.Collectors;

class WordAnalysis {

    static void loadDic(String resourcePath) {
        WordConfTools.set("dic.path",resourcePath);
        DictionaryFactory.reload();
    }

    static List<String> segWords(String value) {
        if(!shouldSeg(value)) {
            return Collections.singletonList(value);
        }
        List<Word> wordList = WordSegmenter.segWithStopWords(value, SegmentationAlgorithm.MaximumMatching);
        PartOfSpeechTagging.process(wordList);
        return segWords(wordList);
    }

    private static List<String> segWords(List<Word> words) {
        Word word;
        Stack<Word> retStack = new Stack<>();
        Stack<Word> tempStack = new Stack<>();
        for (int i = words.size() - 1; i >= 0; i--) {
            tempStack.add(words.get(i));
        }
        while (!tempStack.isEmpty()) {
            word = tempStack.pop();
            if(word.getText().length() < 2) {
                if(word.getText().length() == 0 || moveWord(word,retStack,tempStack)) {
                    continue;
                }
            }
            retStack.add(word);
        }
        return retStack.stream().map(Word::getText).collect(Collectors.toList());
    }

    private static boolean moveWord(Word word,Stack<Word> output,Stack<Word> input) {
        boolean append = isAppend(word,output,input);
        boolean isMoved = appendWord(word, append ? output : input, !append);
        return isMoved || appendWord(word, append ? input : output, append);
    }

    private static boolean appendWord(Word word, Stack<Word> target, boolean reverse) {
        if(!target.isEmpty()) {
            Word peekWord;
            String text;
            peekWord = target.peek();
            text = reverse ? word.getText() + peekWord.getText() : peekWord.getText() + word.getText();
            peekWord.setText(text);
            return true;
        }
        return false;
    }

    private static boolean isAppend(Word word,Stack<Word> left,Stack<Word> right) {
        PartOfSpeech speech = word.getPartOfSpeech();
        if(!left.isEmpty() && left.peek().getText().matches("[^\\u4e00-\\u9fa5]+")) {
            return false;
        }
        return !speech.getPos().startsWith("a");  //分析词性，词性以a开始的为形容词，
    }

    private static boolean shouldSeg(String str) {
        if(str.length() < 3) {
            return false;
        } else if(!str.matches("[\\s\\S]*[\\u4e00-\\u9fa5][\\s\\S]*")) {
            return false;
        }
        return true;
    }

}
