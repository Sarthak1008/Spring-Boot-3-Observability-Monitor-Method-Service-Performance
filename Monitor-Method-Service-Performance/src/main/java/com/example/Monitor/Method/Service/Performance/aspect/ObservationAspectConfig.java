package com.example.Monitor.Method.Service.Performance.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;

@Configuration // for setting up configuration
public class ObservationAspectConfig {

    // This ObservationRegistry comes from aop dependency , this is one of the
    // reasons why we have added that dependency
    @Bean
    public ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        observationRegistry.observationConfig().observationHandler(new PerformanceTrackerHandler()); // Registering our
                                                                                                     // handler class
                                                                                                     // with
                                                                                                     // ObservationRegistery
        return new ObservedAspect(observationRegistry);
    }
}
