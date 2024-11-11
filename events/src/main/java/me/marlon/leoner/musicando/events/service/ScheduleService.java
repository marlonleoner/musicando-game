package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduledExecutorService scheduler;

    public void executeAfterDelay(Runnable task, long delayInSeconds) {
        scheduler.schedule(task, delayInSeconds, TimeUnit.SECONDS);
    }
}
