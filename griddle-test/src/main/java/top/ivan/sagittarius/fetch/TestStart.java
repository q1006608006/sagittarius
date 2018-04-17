package top.ivan.sagittarius.fetch;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.dictionary.DictionaryFactory;
import org.apdplat.word.segmentation.PartOfSpeech;
import org.apdplat.word.segmentation.Word;
import org.apdplat.word.tagging.PartOfSpeechTagging;
import org.apdplat.word.util.WordConfTools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestStart {
    public static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(8); //线程池
            private static ThreadPoolExecutor e2 = new ThreadPoolExecutor(8, 8,
                                      0L,TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>(10));
    public static void main(String[] args) throws IOException, InterruptedException {
/*        for (int i = 0; i < 90; i++) {
            final int myI = i;
*//*            while (executor.getActiveCount() >= 8) {
                synchronized (executor) {
                    executor.wait();
                }
            }*//*
            e2.execute(() -> {
                System.out.println("my I is " + myI + " and active count is " + executor.getActiveCount());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (executor) {
                    executor.notify();
                }
            });
        };
        System.out.println("active count is " + executor.getActiveCount());
        System.out.println(executor.awaitTermination(10, TimeUnit.SECONDS));
        System.out.println("now active count is " + executor.getActiveCount());
        executor.execute(()-> {
            System.out.println("end Run");
        });*/

    }

}
