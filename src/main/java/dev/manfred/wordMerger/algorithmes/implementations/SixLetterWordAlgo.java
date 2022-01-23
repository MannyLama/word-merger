package dev.manfred.wordMerger.algorithmes.implementations;

import dev.manfred.wordMerger.algorithmes.Algorithm;
import dev.manfred.wordMerger.algorithmes.AlgorithmeId;
import dev.manfred.wordMerger.domain.Word;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SixLetterWordAlgo implements Algorithm {

    @Override
    public AlgorithmeId getAlgorithmId() {
        return AlgorithmeId.SIX_LETTER_WORD;
    }

    @Override
    public List<Word> getResult(List<String> input) {
        final Map<Integer, Set<String>> stringsByLength = input.stream().collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        final ConcurrentHashMap<String, Word> results = new ConcurrentHashMap<>();

        stringsByLength.get(6).parallelStream().forEach(s -> {
            for (int i = 1; i < 4; i++) {
                var a = s.substring(0, i); //first part
                var b = s.substring(i); //second part
                if (containsWord(a, stringsByLength) && containsWord(b, stringsByLength) && checkWords(s, a, b))
                    results.put(s, Word.builder().parts(List.of(a, b)).solution(s).build());
            }
        });

        results.values().forEach(r -> log.debug(r.toString()));
        return new ArrayList<>(results.values());
    }

    /**
     * Check if the given part is present in the list of strings by length
     *
     * @param part            a given string to be searched.
     * @param stringsByLength a Map with a Set where as strings are diveded by string length in the map
     * @return true if a part is present in list of strings by length
     */
    private boolean containsWord(String part, Map<Integer, Set<String>> stringsByLength) {
        try {
            return stringsByLength.get(part.length()).contains(part);
        } catch (NullPointerException ex) {
            return false;
        }
    }

    /**
     * checks if the given result can be formed with the given words
     *
     * @param result
     * @param words
     * @return
     */
    @Override
    public boolean checkWords(String result, String... words) {
        if (Arrays.stream(words).mapToInt(String::length).sum() != result.length()) return false;
        return switch (words.length) {
            case 1 -> words[0].equals(result);
            case 2 -> (words[0] + words[1]).equals(result) || (words[1] + words[0]).equals(result);
            default -> throw new NotYetImplementedException("This many options is not yet implemented");
        };
    }
}
