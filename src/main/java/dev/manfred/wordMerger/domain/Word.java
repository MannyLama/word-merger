package dev.manfred.wordMerger.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class Word {
    private List<String> parts;
    private String result;

    @Override
    public String toString() {
        return String.join("+", parts) + "=" + result;
    }
}
