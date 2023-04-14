package com.DKatalis.ParkingLot.service;

public interface ParkNewVehicleService {
	
	void park(String vehicleNumber);

	void inputValidation(String[] operationArray);
}
