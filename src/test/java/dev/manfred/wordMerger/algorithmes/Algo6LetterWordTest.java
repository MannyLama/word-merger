package dev.manfred.wordMerger.algorithmes;


import dev.manfred.wordMerger.algorithmes.implementations.Algo6LetterWord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Algo6LetterWordTest {
    private final Algo6LetterWord algo = new Algo6LetterWord();

    @Test
    public void algoTest() {

    }

    @Test
    public void checkWordsTest(){
        assertTrue(algo.checkWords("hello", "ello", "h"));
        assertTrue(algo.checkWords("hello", "h", "ello"));
        assertFalse(algo.checkWords("wrong", "h", "ello"));
    }

}
