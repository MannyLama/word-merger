package dev.manfred.wordMerger.algorithmes;

import dev.manfred.wordMerger.domain.Word;

import java.util.List;

public interface Algorithme {
    List<Word> getResult(List<String> input);

    boolean checkWords(String result, String... words);
}


