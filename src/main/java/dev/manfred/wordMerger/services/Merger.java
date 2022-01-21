package dev.manfred.wordMerger.services;

import dev.manfred.wordMerger.algorithmes.Algorithme;
import dev.manfred.wordMerger.domain.Word;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Merger {
    List<Word> mergeWords(Algorithme algo, MultipartFile input);
}
