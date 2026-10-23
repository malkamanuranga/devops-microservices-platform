package com.devops.user;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class UserServiceApplication {
    private static final Map<Integer, String> USERS = Map.of(1, "Alice", 2, "Bob", 3, "Charlie");

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @GetMapping("/users")
    public Map<Integer, String> all() { return USERS; }

    @GetMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> one(@PathVariable int id) {
        String name = USERS.get(id);
        if (name == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("id", id, "name", name));
    }
}