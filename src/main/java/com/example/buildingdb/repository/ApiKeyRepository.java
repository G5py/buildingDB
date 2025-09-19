package com.example.buildingdb.repository;

import com.example.buildingdb.entity.ApiKey;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    Optional<ApiKey> findByApiKey(String apiKey);

    default ApiKey findByApiKeyOrThrow(String apiKey) {
        return findByApiKey(apiKey).orElseThrow(() -> new EntityNotFoundException("Authentication failed. apiKey : ".concat(apiKey)));
    }

    boolean existsByApiKey(String apiKey);
}
