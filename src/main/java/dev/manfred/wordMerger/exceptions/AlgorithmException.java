package dev.manfred.wordMerger.exceptions;

public class AlgorithmException extends RuntimeException {
    public AlgorithmException() {
        super("Algorithm exception");
    }

    public AlgorithmException(String reason) {
        super(reason);
    }
}
