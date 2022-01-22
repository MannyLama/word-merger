package dev.manfred.wordMerger.web.controllers;

import dev.manfred.wordMerger.algorithmes.Algorithm;
import dev.manfred.wordMerger.exceptions.AlgorithmException;
import dev.manfred.wordMerger.services.implementations.GeneratorImplService;
import dev.manfred.wordMerger.validation.FileValidator;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping("/api")
public class MergerController {
    private final GeneratorImplService mergerService;
    private final List<FileValidator> fileValidators;
    private final List<Algorithm> algorithms;

    @PostMapping(path = "/file")
    public ResponseEntity<?> parseFile(@RequestParam("file") MultipartFile file) {
        if (fileValidators.stream()
                .map(fv -> fv.validateFile(file))
                .collect(Collectors.toList())
                .contains(false)) {
            return new ResponseEntity<>("File did exceed the limits.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(mergerService.getResult(algorithms.stream().findFirst().orElseThrow(AlgorithmException::new), file).stream().map(Object::toString).collect(Collectors.joining("\n")), HttpStatus.OK);
    }
}
