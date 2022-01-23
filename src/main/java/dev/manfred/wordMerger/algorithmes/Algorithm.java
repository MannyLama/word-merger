package dev.manfred.wordMerger.algorithmes;

import dev.manfred.wordMerger.domain.Word;

import java.util.List;

public interface Algorithm {
    AlgorithmeId getAlgorithmId();

    List<Word> getResult(List<String> input);

    boolean checkWords(String result, String... words);
}


