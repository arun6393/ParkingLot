package com.DKatalis.ParkingLot.dummyDAO;


import java.util.Optional;

import com.DKatalis.ParkingLot.entity.VehicleEntity;

public interface ParkingLotDAO {


	void initliaze(int parkingLotSize);
	
	VehicleEntity insert(String vehicleNumber);
	
	VehicleEntity delete(String vehicleNumber);
	
	VehicleEntity[] fetchAll();
	
	int getParkingLotSize();
	
	Optional<VehicleEntity> findOne(String vehicleNumber);
	
	int getNoOfSlotsAlloted();
	
	boolean isParkingLotFull();
	
}
