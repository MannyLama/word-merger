package dev.manfred.wordMerger.respositories;

import dev.manfred.wordMerger.algorithmes.AlgorithmeId;
import dev.manfred.wordMerger.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ResultRepository extends JpaRepository<Result, UUID> {
    Optional<Result> findResultByCheckSumAndAlgoUsed(String checkSum, AlgorithmeId algorithmeId);
}
