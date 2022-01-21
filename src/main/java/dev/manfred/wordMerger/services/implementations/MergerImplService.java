package dev.manfred.wordMerger.services.implementations;

import dev.manfred.wordMerger.algorithmes.Algorithme;
import dev.manfred.wordMerger.domain.Word;
import dev.manfred.wordMerger.services.Merger;
import dev.manfred.wordMerger.utils.FileConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MergerImplService implements Merger {
    private final List<Algorithme> algorithmes;

    public MergerImplService(List<Algorithme> algorithmes) {
        this.algorithmes = algorithmes;
    }

    @Override
    public List<Word> mergeWords(Algorithme algo, MultipartFile input) {
        try {
            List<String> lines = FileConverter.convertFileToList(input);
            return algo.getResult(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException(); // TODO: 21/01/2022  correct exception
    }
}
