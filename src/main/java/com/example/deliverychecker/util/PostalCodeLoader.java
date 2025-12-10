package com.example.deliverychecker.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PostalCodeLoader {

    private Set<String> allowed;

    @PostConstruct
    public void init() {
        try {
            ClassPathResource r = new ClassPathResource("static-postalcodes.txt");
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(r.getInputStream(), StandardCharsets.UTF_8))) {
                allowed = br.lines()
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toSet());
            }
        } catch (Exception e) {
            // fallback: empty set
            allowed = Set.of();
        }
    }

    public boolean isServiceable(String postalCode) {
        return allowed.contains(postalCode);
    }

    // for tests / admin endpoints you can expose allowed set if needed
}
