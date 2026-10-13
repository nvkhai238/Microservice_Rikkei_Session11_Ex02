package com.rikkei.session11.ex02;

import java.math.BigDecimal;
import java.time.Duration;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.cache.*;
import org.springframework.data.redis.serializer.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@SpringBootApplication
@EnableCaching
public class Application {
 public static void main(String[] args) { SpringApplication.run(Application.class, args); }
 @Bean RedisCacheManager cacheManager(org.springframework.data.redis.connection.RedisConnectionFactory factory) {
  RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
   .entryTtl(Duration.ofMinutes(TTL_MINUTES))
   .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
  return RedisCacheManager.builder(factory).cacheDefaults(config).build();
 }
 private static final int TTL_MINUTES = 60;
}
