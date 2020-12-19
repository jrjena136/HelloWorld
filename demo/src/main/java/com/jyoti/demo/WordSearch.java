package com.jyoti.demo;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordSearch {

    private int[] matchCount(int N, int M, int Q, String[] words,  String[] queries) {
        int[] arr = new int[queries.length];
        Pattern p = null;
        Matcher m = null;
        int count = 0;
        
        for (int i=0; i<Q; i++) {
            
            p = Pattern.compile(queries[i].replace('?','.'));
            for (int j=0; j<N; j++) {
                m = p.matcher(words[j]);
                if (m.find()) {
                    count++;
                }
            }
            arr[i] = count;
            System.out.println("For query word '"+ queries[i] + "', the count is: " + count) ;
            count=0;
        }
        System.out.println("\n");
        return arr;
    }

    public static void main(String[] args) {
        
        WordSearch ws = new WordSearch();
        int N = 5; int M=3; int Q=4;
        String[] w = new String[] {"cat", "map", "bat", "man", "pen"};
        String[] q = new String[] {"?at", "ma?", "?a?", "??n" };
        System.out.println(Arrays.toString(ws.matchCount(N, M, Q, w, q)));
        
        w = new String[] {"uqqur", "1xzev", "ydfgz"}; 
        q = new String[] {"?z???", "???i?", "???e?", "???f?", "?z???"};
        N=3; M=5; Q=5;
        System.out.println(Arrays.toString(ws.matchCount(N, M, Q, w, q)));
        
    }
}