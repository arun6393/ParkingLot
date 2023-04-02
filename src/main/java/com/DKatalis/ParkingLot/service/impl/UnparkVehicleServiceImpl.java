package com.DKatalis.ParkingLot.service.impl;

import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DKatalis.ParkingLot.charges.service.ChargesCalculatorService;
import com.DKatalis.ParkingLot.dto.UnParkDTO;
import com.DKatalis.ParkingLot.dummyDAO.ParkingLotDAO;
import com.DKatalis.ParkingLot.entity.VehicleEntity;
import com.DKatalis.ParkingLot.service.UnParkVehicleService;



@Service("unparkVehicleService")
public class UnparkVehicleServiceImpl implements UnParkVehicleService{

	@Autowired
	private ParkingLotDAO parkingLotDAO;
	@Autowired
	private ChargesCalculatorService calculatorService;
	
	@Override
	public void unpark(UnParkDTO dto) {
		
		String vehicleNumber=dto.getVehicleNumber();
		
		
		if(proceed(dto)) {
		
		VehicleEntity deletedVehicle=parkingLotDAO.delete(vehicleNumber);
		
		long charges=calculatorService.calculate(dto.getDuration());
		
		System.out.println("Registration Number "+vehicleNumber+" from Slot "+(deletedVehicle.getAllotmentId()+1)+" has left with Charge"+ charges);
		}
	}
	

	private boolean proceed(UnParkDTO dto) {
		
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

}
