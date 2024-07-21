package com.example.controller;

import com.example.entity.Bus;
import com.example.entity.BusStops;
import com.example.entity.Stop;
import com.example.repository.BusRepository;
import com.example.repository.BusStopsRepository;
import com.example.repository.StopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bus")
public class BusStopsController {

    private BusRepository busRepository;
    private StopRepository stopRepository;
    private BusStopsRepository busStopsRepository;

    public BusStopsController(BusRepository busRepository, StopRepository stopRepository, BusStopsRepository busStopsRepository) {
        this.busRepository = busRepository;
        this.stopRepository = stopRepository;
        this.busStopsRepository = busStopsRepository;
    }

    @PostMapping
    public ResponseEntity<BusStops> allocateBusRoute(

            @RequestParam long busId,
            @RequestParam long stopId,

            @RequestBody BusStops busStops

    ){
    Bus bus = busRepository.findById(busId).get();
    Stop stop = stopRepository.findById(stopId).get();


    busStops.setBus(bus);
    busStops.setStop(stop);
        BusStops save = busStopsRepository.save(busStops);

        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
}
