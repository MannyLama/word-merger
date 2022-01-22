package dev.manfred.wordMerger.services;

import dev.manfred.wordMerger.domain.Result;
import dev.manfred.wordMerger.domain.Word;

import java.util.List;

public interface WordService{
    List<Word> getAllWordOfAResult(Result result);

    void saveAll(Result result, List<Word> words);

    void clearWords(List<Result> oldResults);
}
