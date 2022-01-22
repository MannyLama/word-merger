package dev.manfred.wordMerger.config;

import dev.manfred.wordMerger.services.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class SchedulerConfig {
    private final ResultService resultService;

    /**
     * Execute every day at 03:00 (AM for the Americans)
     */
    @Scheduled(cron = "0 0 3 1/1 * ?")
    public void scheduleClearingOldResults() {
        resultService.clearOldResults();
    }
}
