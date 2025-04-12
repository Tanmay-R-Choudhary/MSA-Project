package GradeManager.GradeManager.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.gateway.url}")
    private String apiGatewayUrl;

    @CircuitBreaker(name = "notificationService", fallbackMethod = "sendNotificationFallback")
    public String sendNotification(HttpServletRequest request, String message) {
        String token = request.getHeader("Authorization");

        String url = UriComponentsBuilder
                .fromHttpUrl("http://NOTIFICATION-SERVICE/notifications/send")
                .queryParam("message", message)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.postForObject(url, entity, String.class);
    }

    public String sendNotificationFallback(Long userId, String message, Exception ex) {
        // Log the exception
        System.out.println("Circuit breaker activated for notification to user ID: " + userId);
        System.out.println("Exception: " + ex.getMessage());

        // Return a fallback response
        return "Unable to send notification at this time. Notification will be queued for later delivery.";
    }
}
