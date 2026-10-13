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


@Service
class MedicineService {
 private final MedicineRepository repository;
 MedicineService(MedicineRepository repository) { this.repository = repository; }
 @Cacheable(value = "medicines", key = "#id")
 public MedicineView get(Long id) {
  Medicine medicine = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Khong tim thay thuoc"));
  return new MedicineView(medicine.id, medicine.name, medicine.price);
 }
}
