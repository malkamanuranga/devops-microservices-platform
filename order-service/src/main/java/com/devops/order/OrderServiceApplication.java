package main.java.com.devops.order;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class OrderServiceApplication {
    private final RestTemplate http = new RestTemplate();

    @Value("${user.service.url:http://localhost:8081}")
    private String userServiceUrl;

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @GetMapping("/orders/{userId}")
    public Map<String, Object> order(@PathVariable int userId) {
        Map<?, ?> user = http.getForObject(userServiceUrl + "/users/" + userId, Map.class);
        return Map.of("orderId", 1000 + userId, "item", "DevOps Book", "user", user);
    }
}