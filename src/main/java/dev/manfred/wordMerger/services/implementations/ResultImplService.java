package dev.manfred.wordMerger.services.implementations;

import dev.manfred.wordMerger.algorithmes.AlgorithmeId;
import dev.manfred.wordMerger.domain.Result;
import dev.manfred.wordMerger.respositories.ResultRepository;
import dev.manfred.wordMerger.services.ResultService;
import dev.manfred.wordMerger.services.WordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResultImplService implements ResultService {
    private final ResultRepository resultRepository;

    private final WordService wordService;

    @Override
    public Optional<Result> findResultByChecksum(String checkSum, AlgorithmeId algorithmeId) {
        return resultRepository.findResultByCheckSumAndAlgoUsed(checkSum, algorithmeId);
    }

    @Override
    public Result createNewResult(AlgorithmeId algorithmeId, String checkSum) {
        return resultRepository.save(Result.builder().algoUsed(algorithmeId).checkSum(checkSum).timestamp(LocalDateTime.now()).build());
    }

    @Override
    public void clearOldResults() {
        var oldResults = resultRepository.findAllByTimestampBefore(LocalDateTime.now().minusDays(1));
        wordService.clearWords(oldResults);
        resultRepository.deleteAll(oldResults);
    }
}
