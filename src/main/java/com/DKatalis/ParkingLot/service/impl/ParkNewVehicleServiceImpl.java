package com.DKatalis.ParkingLot.service.impl;

import java.util.Objects;


import com.DKatalis.ParkingLot.enums.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DKatalis.ParkingLot.command.ParkingCommand;
import com.DKatalis.ParkingLot.dummyDAO.ParkingLotDAO;
import com.DKatalis.ParkingLot.entity.VehicleEntity;
import com.DKatalis.ParkingLot.service.ParkNewVehicleService;

import lombok.AllArgsConstructor;



@Service("parkNewVehicleService")
@AllArgsConstructor
public class ParkNewVehicleServiceImpl implements ParkNewVehicleService,ParkingCommand{

	@Autowired
	private final ParkingLotDAO parkingLotDAO;
	
	@Override
	public void park(String vehicleNumber) {
		
		
		if(proceed(vehicleNumber)) {
		
		VehicleEntity newVehicle=parkingLotDAO.insert(vehicleNumber);
		
		System.out.println("Allocated slot number::"+ newVehicle.getAllotmentId());
		}
	}

	private boolean proceed(String vehicleNumber) {
		
		
		if(parkingLotDAO.isParkingLotFull()) {
			System.out.println("Sorry, parking lot is full");
			return false;
		}
		
		if(Objects.isNull(vehicleNumber)) {
			System.out.println("Vehicle Number not valid");
			return false;
		}

		if(parkingLotDAO.findOne(vehicleNumber).isPresent()) {
			System.out.println("Vehicle Already parked");
			return false;
		}
		
		
		return true;
	}
	
	@Override
	public boolean isValidCommand(String[] command) {
		
		if(Operation.PARK.getName().equalsIgnoreCase(command[0])){
			if(Operation.PARK.getArraySize()!=command.length){
				System.out.println("Invalid PARK command ");
				return false;
			}
			return true;
		}
		return false;

	}

	@Override
	public void process(String[] command) {
		park(command[1]);
	}

}
