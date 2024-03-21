package com.example.Monitor.Method.Service.Performance.aspect;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import lombok.extern.slf4j.Slf4j;

/* We have one observation handler interface given by this observability in SpringBoot 3 from which we can take that interface and can create 
our own custom handler to print the log statement or to track the onstart and on end execution event time */

@Slf4j // added for logging purpose
public class PerformanceTrackerHandler implements ObservationHandler<Observation.Context> {

    /*
     * Since we just need to find out the execution time we would just require the
     * start time method and the end time method
     */

    /*
     * Now since I have created this handler class , how would our observatory will
     * know that we have written this handler class to track the method
     * execution or how he can he call this on start or on end method. For that you
     * need to register this handler to the observation registery. And
     * for that you will basically create observationAspectConfig class
     */
    @Override
    public void onStart(Observation.Context context) {
        log.info("execution started {}", context.getName());
        context.put("time", System.currentTimeMillis());
    }

    @Override
    public void onError(Observation.Context context) {
        log.info("Error occurred {} ", context.getError().getMessage());
    }

    @Override
    public void onEvent(Observation.Event event, Observation.Context context) {
        ObservationHandler.super.onEvent(event, context);
    }

    @Override
    public void onScopeOpened(Observation.Context context) {
        ObservationHandler.super.onScopeOpened(context);
    }

    @Override
    public void onScopeClosed(Observation.Context context) {
        ObservationHandler.super.onScopeClosed(context);
    }

    @Override
    public void onScopeReset(Observation.Context context) {
        ObservationHandler.super.onScopeReset(context);
    }

    @Override
    public void onStop(Observation.Context context) {
        log.info(
                "execution stopped "
                        + context.getName()
                        + " duration "
                        + (System.currentTimeMillis() - context.getOrDefault("time", 0L)));
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return true;
    }
}
