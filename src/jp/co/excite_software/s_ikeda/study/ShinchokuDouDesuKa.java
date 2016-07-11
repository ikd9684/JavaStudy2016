package jp.co.excite_software.s_ikeda.study;

import java.util.Random;

public class ShinchokuDouDesuKa {

    public static void main(String[] args) {

        final String[] words = { "進捗", "どう", "ですか？", };

        int totalWordCount = 0;
        int matchNumber = 0;
        Random rnd = new Random();

        while (matchNumber < words.length) {

            String word = words[rnd.nextInt(words.length)];
            System.out.print(word);
            totalWordCount += word.length();

            matchNumber = (word == words[matchNumber]) ? matchNumber + 1 : 0;
        }

        System.out.println();
        System.out.printf("%d文字で煽られました。", totalWordCount).println();
    }
}
