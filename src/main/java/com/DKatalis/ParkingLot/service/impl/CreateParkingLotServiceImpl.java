package com.DKatalis.ParkingLot.service.impl;




import com.DKatalis.ParkingLot.enums.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.DKatalis.ParkingLot.command.ParkingCommand;
import com.DKatalis.ParkingLot.dummyDAO.ParkingLotDAO;
import com.DKatalis.ParkingLot.service.CreateParkingLotService;

import lombok.AllArgsConstructor;


@Component("createParkingLotService")
@AllArgsConstructor
public class CreateParkingLotServiceImpl implements CreateParkingLotService,ParkingCommand{

	@Autowired
	private final ParkingLotDAO parkingLotDAO;
	
	@Override
	public void create(int parkingLotSize) {

		if(parkingLotSize<=0) {
			throw new RuntimeException("Parking lot size cannot be 0 or negative");
		}
		if(parkingLotDAO.getParkingLotSize()!=0) {
			throw new RuntimeException("Parking lot is already initialized");
		}
		
		parkingLotDAO.initialize(parkingLotSize);
		
		System.out.println("Created parking lot with::"+parkingLotDAO.getParkingLotSize()+" slots");
		
	
	}

	@Override
	public boolean isValidCommand(String[] command) {
		
		if(Operation.CREATE.getName().equalsIgnoreCase(command[0])){
			if(Operation.CREATE.getArraySize()!=command.length){
				throw new RuntimeException("Invalid CREATE command , Unable to create Parking Lot");
			}
			return true;
		}
		return false;

	}

	@Override
	public void process(String[] command) {
		create(Integer.parseInt(command[1]));
	}

}
