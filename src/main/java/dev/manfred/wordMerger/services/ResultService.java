package dev.manfred.wordMerger.services;

import dev.manfred.wordMerger.algorithmes.AlgorithmeId;
import dev.manfred.wordMerger.domain.Result;

import java.util.Optional;

public interface ResultService {
    Optional<Result> findResultByChecksum(String checkSum, AlgorithmeId algorithmeId);

    Result createNewResult(AlgorithmeId algorithmeId, String checkSum);

    void clearOldResults();
}
