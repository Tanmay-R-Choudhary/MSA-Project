package GradeManager.GradeManager.Controller;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/circuit-breaker")
public class CircuitBreakerController {

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @GetMapping("/status")
    public Map<String, Object> getCircuitBreakersStatus() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("notificationService");

        Map<String, Object> status = new HashMap<>();
        status.put("state", circuitBreaker.getState());
        status.put("metrics", Map.of(
                "failureRate", circuitBreaker.getMetrics().getFailureRate(),
                "slowCallRate", circuitBreaker.getMetrics().getSlowCallRate(),
                "numberOfSuccessfulCalls", circuitBreaker.getMetrics().getNumberOfSuccessfulCalls(),
                "numberOfFailedCalls", circuitBreaker.getMetrics().getNumberOfFailedCalls()
        ));

        return status;
    }

    @GetMapping("/force/{state}")
    public String forceState(@PathVariable String state) {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("notificationService");

        switch (state.toLowerCase()) {
            case "open":
                circuitBreaker.transitionToOpenState();
                return "Circuit breaker forced to OPEN state";
            case "half-open":
                circuitBreaker.transitionToHalfOpenState();
                return "Circuit breaker forced to HALF_OPEN state";
            case "closed":
                circuitBreaker.transitionToClosedState();
                return "Circuit breaker forced to CLOSED state";
            default:
                return "Invalid state. Use open, half-open, or closed";
        }
    }
}
