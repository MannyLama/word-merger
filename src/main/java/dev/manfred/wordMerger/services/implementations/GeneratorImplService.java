package dev.manfred.wordMerger.services.implementations;

import dev.manfred.wordMerger.algorithmes.Algorithm;
import dev.manfred.wordMerger.domain.Result;
import dev.manfred.wordMerger.domain.Word;
import dev.manfred.wordMerger.services.GeneratorService;
import dev.manfred.wordMerger.services.ResultService;
import dev.manfred.wordMerger.services.WordService;
import dev.manfred.wordMerger.utils.FileHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GeneratorImplService implements GeneratorService {
    private final ResultService resultService;
    private final WordService wordService;

    @Override
    public List<Word> getResult(Algorithm algo, MultipartFile input) {
        var checkSum = FileHelper.getMD5CheckSum(input);
        var oldResult = getOldResult(algo, checkSum);
        if (!oldResult.isEmpty()) return oldResult;

        return computeAndSaveResult(algo, input, checkSum);
    }

    private List<Word> getOldResult(Algorithm algo, String checkSum) {
        Optional<Result> oldResult = resultService.findResultByChecksum(checkSum, algo.getAlgorithmId());
        if (oldResult.isEmpty())
            return new ArrayList<>();
        return wordService.getAllWordOfAResult(oldResult.get());
    }

    private List<Word> computeAndSaveResult(Algorithm algo, MultipartFile file, String checkSum) {
        try {
            List<String> lines = FileHelper.convertFileToList(file);
            var result = resultService.createNewResult(algo.getAlgorithmId(), checkSum);
            var words = algo.getResult(lines);
            wordService.saveAll(result, words);
            return words;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
