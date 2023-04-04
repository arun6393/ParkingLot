package com.DKatalis.ParkingLot.dummyDB;

import org.springframework.stereotype.Component;

import com.DKatalis.ParkingLot.entity.VehicleEntity;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class VehicleParkingLotDB {

	private VehicleEntity[] vehicles;
	
	public void initliaze(int parkingLotSize) {
		vehicles=new VehicleEntity[parkingLotSize];
	}
	
	public VehicleEntity[] getVehicles() {
		return this.vehicles;
	}
	
}
