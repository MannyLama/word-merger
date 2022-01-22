package dev.manfred.wordMerger.services.implementations;

import dev.manfred.wordMerger.domain.Result;
import dev.manfred.wordMerger.domain.Word;
import dev.manfred.wordMerger.respositories.WordRepository;
import dev.manfred.wordMerger.services.WordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WordImplService implements WordService {
    private final WordRepository wordRepository;

    @Override
    public List<Word> getAllWordOfAResult(Result result) {
        return wordRepository.findAllByResult(result);
    }

    @Override
    public void saveAll(Result result, List<Word> words) {
        words.forEach(w -> w.setResult(result));
        wordRepository.saveAll(words);
    }
}
