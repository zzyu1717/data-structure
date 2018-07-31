package zzy.map.test;

import org.junit.Test;
import zzy.map.impl.BSTMap;
import zzy.map.impl.LinkedListMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LinkedListMapTest {

    @Test
    public void testLinkedListMap() {
        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile("src/zzy/map/test/animal farm.txt",words);
        System.out.println("animal farm : total " + words.size() + " word");
        BSTMap<String, Integer> map = new BSTMap<>();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word)+1);
            } else {
                map.add(word,1);
            }
        }
        // 词频统计
        System.out.println("pig frequency : " + map.get("pig"));
        System.out.println("animal frequency : " + map.get("animal"));

    }
}