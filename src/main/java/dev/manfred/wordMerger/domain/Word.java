package dev.manfred.wordMerger.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Word implements Persistable<UUID> {
    @Id
    @GeneratedValue
    private UUID id;

    @ElementCollection
    private List<String> parts;

    private String solution;

    @ManyToOne
    private Result result;

    @Override
    public String toString() {
        return String.join("+", parts) + "=" + solution;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
}
