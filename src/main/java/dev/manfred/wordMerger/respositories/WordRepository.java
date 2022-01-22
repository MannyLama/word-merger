package dev.manfred.wordMerger.respositories;

import dev.manfred.wordMerger.domain.Result;
import dev.manfred.wordMerger.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WordRepository extends JpaRepository<Word, UUID> {
    List<Word> findAllByResult(Result result);
}
