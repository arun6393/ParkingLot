package com.DKatalis.ParkingLot.service.impl;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.DKatalis.ParkingLot.dummyDAO.ParkingLotDAO;
import com.DKatalis.ParkingLot.service.CreateParkingLotService;


@Component("createParkingLotService")
public class CreateParkingLotServiceImpl implements CreateParkingLotService{

	@Autowired
	private ParkingLotDAO parkingLotDAO;
	
	@Override
	public void create(int parkingLotSize) {
		
	
		if(parkingLotSize<=0) {
			throw new RuntimeException("Parking lot size cannot be 0 or negative");
		}
		if(parkingLotDAO.getParkingLotSize()!=0) {
			throw new RuntimeException("Parking lot is already inialitized");
		}
		
		parkingLotDAO.initliaze(parkingLotSize);
		
		System.out.println("Created parking lot with::"+parkingLotSize+" slots");
		
	
	}

}
