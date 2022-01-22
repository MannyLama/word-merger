package dev.manfred.wordMerger.algorithmes;

import dev.manfred.wordMerger.algorithmes.implementations.SixLetterWordAlgo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class Algo6LetterWordTest {
    private final SixLetterWordAlgo algo = new SixLetterWordAlgo();

    @Test
    public void algoTest() {
        var result = algo.getResult(Arrays.asList("fo", "obar", "foobar"));
        assertEquals(1, result.size());
        assertEquals("foobar", result.stream().findAny().get().getSolution());

        var result2 = algo.getResult(Arrays.asList("not", "obar", "foobar"));
        assertEquals(0, result2.size());
    }

    @Test
    public void checkWordsTest() {
        assertTrue(algo.checkWords("hello", "ello", "h"));
        assertTrue(algo.checkWords("hello", "h", "ello"));

        assertFalse(algo.checkWords("wrong", "h", "ello"));
    }
}
