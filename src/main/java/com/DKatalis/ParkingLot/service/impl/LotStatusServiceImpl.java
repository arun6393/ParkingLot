package com.DKatalis.ParkingLot.service.impl;


import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DKatalis.ParkingLot.dummyDAO.ParkingLotDAO;
import com.DKatalis.ParkingLot.entity.VehicleEntity;
import com.DKatalis.ParkingLot.service.ParkingLotStatusService;

import lombok.AllArgsConstructor;


@Service("lotStatusService")
@AllArgsConstructor
public class LotStatusServiceImpl implements ParkingLotStatusService{

	@Autowired
	private final ParkingLotDAO parkingLotDAO;
	
	@Override
	public void status() {
		
		VehicleEntity[] vehicles=parkingLotDAO.fetchAll();
		System.out.println("Slot No. Registration No.");
		
		Stream.of(vehicles).filter(vehicle-> Objects.nonNull(vehicle)).forEach(vehicle-> System.out.println(vehicle.getAllotmentId() +"     "+ vehicle.getNumber()));
				
	}

}
