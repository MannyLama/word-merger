package dev.manfred.wordMerger.domain;

import dev.manfred.wordMerger.algorithmes.AlgorithmeId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Persistable<UUID> {
    @Id
    @GeneratedValue
    private UUID id;

    private String checkSum;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private AlgorithmeId algoUsed;

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
