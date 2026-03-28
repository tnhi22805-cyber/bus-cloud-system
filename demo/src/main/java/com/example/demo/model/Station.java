package com.example.demo.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity 
@Table(name = "stations")
@Data
public class Station {
    @Id
    private Integer stationId;
    private String name;
    private Integer passengersIn = 0;
    private Double revenue = 0.0;
}