package dev.manfred.wordMerger.algorithmes.implementations;

import dev.manfred.wordMerger.algorithmes.Algorithme;
import dev.manfred.wordMerger.domain.Word;
import org.hibernate.cfg.NotYetImplementedException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Algo6LetterWord implements Algorithme {

    @Override
    public List<Word> getResult(List<String> input) {
        final Map<Integer, Set<String>> stringsByLength = input.stream().collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        final ConcurrentHashMap<String, Word> results = new ConcurrentHashMap<>();

        stringsByLength.get(6).parallelStream().forEach(s -> {
            for (int i = 1; i < 4; i++) {
                var first = s.substring(0, i);
                var second = s.substring(i);
                if (checkWords(first, second, s))
                    results.put(s, Word.builder().parts(List.of(first, second)).result(s).build());
            }

        });

        return new ArrayList<>(results.values());
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
            default -> throw new NotYetImplementedException("This many optiosn is not yet implemneted");
        };
    }
}
