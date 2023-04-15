package com.DKatalis.ParkingLot.service.impl;

import java.util.Objects;


import com.DKatalis.ParkingLot.enums.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DKatalis.ParkingLot.charges.service.ChargesCalculatorService;
import com.DKatalis.ParkingLot.command.ParkingCommand;
import com.DKatalis.ParkingLot.dto.UnParkDTO;
import com.DKatalis.ParkingLot.dummyDAO.ParkingLotDAO;
import com.DKatalis.ParkingLot.entity.VehicleEntity;
import com.DKatalis.ParkingLot.service.UnParkVehicleService;

import lombok.AllArgsConstructor;



@Service("unparkVehicleService")
@AllArgsConstructor
public class UnparkVehicleServiceImpl implements UnParkVehicleService,ParkingCommand{

	@Autowired
	private final ParkingLotDAO parkingLotDAO;
	@Autowired
	private final ChargesCalculatorService calculatorService;
	
	@Override
	public void unpark(UnParkDTO dto) {
		
		if(proceed(dto)) {
		
     	String vehicleNumber=dto.getVehicleNumber();
		VehicleEntity deletedVehicle=parkingLotDAO.delete(vehicleNumber);
		
		long charges=calculatorService.calculate(dto.getDuration());
		
		System.out.println("Registration Number "+vehicleNumber+" from Slot "+deletedVehicle.getAllotmentId()+" has left with Charge"+ charges);
		}
	}


	private boolean proceed(UnParkDTO dto) {
		
		if(Objects.isNull(dto)) {
			System.out.println("Vehicle Number not valid");
			return false;
		}
		
		
		String vehicleNumber=dto.getVehicleNumber();
		if(Objects.isNull(vehicleNumber)) {
			System.out.println("Vehicle Number not valid");
			return false;
		}

		if(!parkingLotDAO.findOne(vehicleNumber).isPresent()) {
			System.out.println("Registration Number "+dto.getVehicleNumber()+" not found");
			return false;
		}
		
		
		return true;
	}
	
	@Override
	public boolean isValidCommand(String[] command) {
		
		if(Operation.LEAVE.getName().equalsIgnoreCase(command[0])){
			if(Operation.LEAVE.getArraySize()!=command.length){
				System.out.println("Invalid UNPARK command ");
				return false;
			}
			return true;
		}
		return false;

	}

	@Override
	public void process(String[] command) {
		unpark(new UnParkDTO(command[1],Integer.parseInt(command[2])));

	}

}
