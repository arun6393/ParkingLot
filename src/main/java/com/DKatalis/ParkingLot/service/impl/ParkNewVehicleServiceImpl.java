package com.DKatalis.ParkingLot.service.impl;

import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DKatalis.ParkingLot.dummyDAO.ParkingLotDAO;
import com.DKatalis.ParkingLot.entity.VehicleEntity;
import com.DKatalis.ParkingLot.service.ParkNewVehicleService;



@Service("parkNewVehicleService")
public class ParkNewVehicleServiceImpl implements ParkNewVehicleService{

	@Autowired
	private ParkingLotDAO parkingLotDAO;
	
	@Override
	public void park(String vehicleNumber) {
		
		
		if(proceed(vehicleNumber)) {
		
		VehicleEntity newVehicle=parkingLotDAO.insert(vehicleNumber);
		
		System.out.println("Allocated slot number::"+(newVehicle.getAllotmentId()+1));
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

}
