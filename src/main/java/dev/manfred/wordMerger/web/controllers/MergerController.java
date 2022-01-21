package dev.manfred.wordMerger.web.controllers;

import dev.manfred.wordMerger.algorithmes.Algorithme;
import dev.manfred.wordMerger.exceptions.AlgorithmeException;
import dev.manfred.wordMerger.services.implementations.MergerImplService;
import dev.manfred.wordMerger.validation.FileValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
public class MergerController {
    private final MergerImplService mergerService;
    private final List<FileValidator> fileValidators;
    private final List<Algorithme> algorithms;

    public MergerController(MergerImplService mergerService, List<FileValidator> fileValidators, List<Algorithme> algorithms) {
        this.mergerService = mergerService;
        this.fileValidators = fileValidators;
        this.algorithms = algorithms;
    }

    @PostMapping(path = "/file")
    public ResponseEntity<?> parseFile(@RequestParam("file") MultipartFile file) {
        if (fileValidators.stream()
                .map(fv -> fv.validateFile(file))
                .collect(Collectors.toList())
                .contains(false))
            return new ResponseEntity<>("File did exceed the limits.", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(mergerService.mergeWords(algorithms.stream().findFirst().orElseThrow(AlgorithmeException::new), file), HttpStatus.OK);
    }
}
