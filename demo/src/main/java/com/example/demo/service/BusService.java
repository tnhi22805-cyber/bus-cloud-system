package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BusService {
    private final BusRepository busRepo;
    private final StationRepository stationRepo;

    @Value("${station.id}")
    private int myStationId; // Server tự biết mình là trạm mấy

    public BusService(BusRepository busRepo, StationRepository stationRepo) {
        this.busRepo = busRepo;
        this.stationRepo = stationRepo;
    }

    @Transactional
    public String processBus() {
        // Tìm xe buýt số 1
        BusStatus bus = busRepo.findById(1).orElse(null);
        if (bus == null) return "LỖI: Không tìm thấy dữ liệu xe buýt trong DB!";

        // 1. Kiểm tra vị trí xe
        if (bus.getCurrentStationId() != myStationId) {
            return "XE CHƯA ĐẾN: Xe đang ở Trạm " + bus.getCurrentStationId() + ". Vui lòng chờ!";
        }

        // 2. Tìm trạm hiện tại để cập nhật
        Station s = stationRepo.findById(myStationId).orElse(null);
        if (s == null) return "LỖI: Không tìm thấy dữ liệu Trạm " + myStationId + " trong DB!";

        // Logic xử lý khách
        int in = (int) (Math.random() * 5) + 1;
        bus.setCurrentPassengers(bus.getCurrentPassengers() + in);
        s.setPassengersIn(s.getPassengersIn() + in);
        stationRepo.save(s);

        // 3. Di chuyển xe
        int nextStation = (myStationId % 5) + 1;
        bus.setCurrentStationId(nextStation);

        // 4. Tính số vòng
        if (nextStation == 1) {
            bus.setTotalRounds(bus.getTotalRounds() + 1);
        }

        busRepo.save(bus);
        return "THÀNH CÔNG: Xe đã rời Trạm " + myStationId + " để đi Trạm " + nextStation;
    }
}