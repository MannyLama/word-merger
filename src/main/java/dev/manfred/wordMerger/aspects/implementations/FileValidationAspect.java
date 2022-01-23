package dev.manfred.wordMerger.aspects.implementations;

import dev.manfred.wordMerger.validation.FileValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class FileValidationAspect {

    private final List<FileValidator> fileValidators;

    /**
     * Aspect that will be invoked around a method when that method has the @ValidateFile annotation.
     * This method will go through al the file validations needed to validate the file.
     *
     * @param method
     * @return
     * @throws Throwable
     */
    @Around(value = "@annotation(dev.manfred.wordMerger.aspects.ValidateFile) && within(dev.manfred.wordMerger..*)")
    public Object fileValidationHandling(ProceedingJoinPoint method) throws Throwable {
        var args = method.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (!(args[i] instanceof MultipartFile)) continue;

            var file = (MultipartFile) method.getArgs()[i];
            if (file == null || fileValidators.stream()
                    .map(fv -> fv.validateFile(file))
                    .collect(Collectors.toList())
                    .contains(false)) {
                log.info("The file does not pass al the file validations required" + createJoinPointTraceName(method));
                return new ResponseEntity<>("The file does not pass al the file validations required", HttpStatus.BAD_REQUEST);
            }
        }

        return method.proceed();
    }

    private String createJoinPointTraceName(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        return signature.getDeclaringType().getSimpleName() + '.' + signature.getName();
    }
}
