package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bus")
@CrossOrigin("*")
public class BusController {
    @Autowired private BusService busService;
    @Autowired private BusRepository busRepo;
    @Autowired private StationRepository stationRepo;

    @PostMapping("/check-in")
    public String checkIn() { return busService.processBus(); }

    @GetMapping("/status")
    public BusStatus getStatus() { return busRepo.findById(1).get(); }

    @GetMapping("/stations")
    public List<Station> getStations() { return stationRepo.findAll(); }
}