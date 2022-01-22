package dev.manfred.wordMerger.services;

import dev.manfred.wordMerger.algorithmes.Algorithm;
import dev.manfred.wordMerger.domain.Word;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GeneratorService {
    List<Word> getResult(Algorithm algo, MultipartFile input);
}
