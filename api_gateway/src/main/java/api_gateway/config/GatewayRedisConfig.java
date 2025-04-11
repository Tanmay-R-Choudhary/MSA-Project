package api_gateway.config;

// In a @Configuration class within your api-gateway project
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class GatewayRedisConfig {

    // Inject values directly from the resolved Spring Environment
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Bean
    @Primary
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        // Log the exact values being used when this bean is created
        System.out.println(">>> Explicitly Creating Redis Connection Factory for Host: " + redisHost + ":" + redisPort);
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    // If you uncommented the rate limiter later, ensure IpKeyResolver bean is defined
    // @Bean
    // public org.springframework.cloud.gateway.filter.ratelimiter.KeyResolver ipKeyResolver() {
    //    System.out.println(">>> Creating IpKeyResolver Bean");
    //    return exchange -> reactor.core.publisher.Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    // }
}