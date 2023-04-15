package com.DKatalis.ParkingLot.service.impl;


import java.util.Objects;
import java.util.stream.Stream;

import com.DKatalis.ParkingLot.enums.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DKatalis.ParkingLot.command.ParkingCommand;
import com.DKatalis.ParkingLot.dummyDAO.ParkingLotDAO;
import com.DKatalis.ParkingLot.entity.VehicleEntity;
import com.DKatalis.ParkingLot.service.ParkingLotStatusService;

import lombok.AllArgsConstructor;


@Service("lotStatusService")
@AllArgsConstructor
public class LotStatusServiceImpl implements ParkingLotStatusService,ParkingCommand{

	@Autowired
	private final ParkingLotDAO parkingLotDAO;
	
	@Override
	public void status() {
		
		VehicleEntity[] vehicles=parkingLotDAO.fetchAll();
		System.out.println("Slot No. Registration No.");
		
		Stream.of(vehicles).filter(vehicle-> Objects.nonNull(vehicle)).forEach(vehicle-> System.out.println(vehicle.getAllotmentId() +"     "+ vehicle.getNumber()));
				
	}

	
	@Override
	public boolean isValidCommand(String[] command) {
		
		if(Operation.STATUS.getName().equalsIgnoreCase(command[0])){
			if(Operation.STATUS.getArraySize()!=command.length){
				System.out.println("Invalid STATUS command ");
				return false;
			}
			return true;
		}
		return false;

	}

	@Override
	public void process(String[] command) {
			status();	
	}

}
