package com.example.demo.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class BusStatus {
    @Id
    private Integer id = 1; // Chỉ dùng 1 xe duy nhất
    private Integer currentStationId;
    private Integer totalRounds;
    private Integer currentPassengers;
}