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


@RestController
@RequestMapping("/api/medicines")
class MedicineController {
 private final MedicineService service;
 private final MedicineUpdateService updateService;
 MedicineController(MedicineService service, MedicineUpdateService updateService) { this.service = service; this.updateService = updateService; }
 @GetMapping("/{id}") MedicineView get(@PathVariable Long id) { return service.get(id); }
 @PutMapping MedicineView update(@Valid @RequestBody MedicineRequest medicine) { return updateService.update(medicine); }
}
