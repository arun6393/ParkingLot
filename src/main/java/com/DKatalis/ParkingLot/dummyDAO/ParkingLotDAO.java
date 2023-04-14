package com.DKatalis.ParkingLot.dummyDAO;


import java.util.Optional;

import com.DKatalis.ParkingLot.entity.VehicleEntity;

public interface ParkingLotDAO {


	void initialize(int parkingLotSize);
	
	VehicleEntity insert(String vehicleNumber);
	
	VehicleEntity delete(String vehicleNumber);
	
	VehicleEntity[] fetchAll();
	
	int getParkingLotSize();
	
	Optional<VehicleEntity> findOne(String vehicleNumber);
	
	int getNoOfSlotsAllotted();
	
	boolean isParkingLotFull();
	
}
