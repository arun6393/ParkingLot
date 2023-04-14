package com.DKatalis.ParkingLot.dummyDAO.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.DKatalis.ParkingLot.dummyDAO.ParkingLotDAO;
import com.DKatalis.ParkingLot.dummyDB.VehicleParkingLotDB;
import com.DKatalis.ParkingLot.entity.VehicleEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ParkingLotDAOImpl implements ParkingLotDAO {

	@Autowired
	private final VehicleParkingLotDB vehicleParkingLotDB;
	private int noOfSlotsAllotted;

	
	@Override
	public void initialize(int parkingLotSize) {
		
		vehicleParkingLotDB.initialize(parkingLotSize);
	}

	@Override
	public VehicleEntity insert(String vehicleNumber) {
		
		VehicleEntity vehicleEntity = null;
		VehicleEntity[] vehicles=vehicleParkingLotDB.getVehicles();
		for(int i=0;i<vehicles.length;i++) {
			if(vehicles[i]== null) {
				vehicleEntity= new VehicleEntity(i+1,vehicleNumber);
				vehicles[i]=vehicleEntity;
				noOfSlotsAllotted++;
				break;
			}
		}
		
		return vehicleEntity;
	}

	@Override
	public VehicleEntity delete(String vehicleNumber) {

		VehicleEntity vehicleEntity = null;
		VehicleEntity[] vehicles=vehicleParkingLotDB.getVehicles();
		for(int i=0;i<vehicles.length;i++) {
			if(vehicles[i]!=null && vehicles[i].getNumber().equals(vehicleNumber)) {
				vehicleEntity=vehicles[i];
				vehicles[i]=null;
				noOfSlotsAllotted--;
				break;
			}
		}
		
		
		return vehicleEntity;
	}

	@Override
	public VehicleEntity[] fetchAll() {
		return vehicleParkingLotDB.getVehicles();
	}

	@Override
	public int getParkingLotSize() {

		VehicleEntity[] vehicles=vehicleParkingLotDB.getVehicles();
		return vehicles == null ? 0 : vehicles.length;
	}

	@Override
	public Optional<VehicleEntity> findOne(String vehicleNumber) {
		
		if(this.noOfSlotsAllotted ==0) {
			return Optional.empty();
		}
		
		VehicleEntity[] vehicles=vehicleParkingLotDB.getVehicles();
		for(int i=0;i<vehicles.length;i++) {
			if(vehicles[i]!=null && vehicles[i].getNumber().equals(vehicleNumber)) {
				return Optional.of(vehicles[i]);
			}
		}
		
		return Optional.empty();
	}

	@Override
	public int getNoOfSlotsAllotted() {
		return this.noOfSlotsAllotted;
	}

	@Override
	public boolean isParkingLotFull() {
		return getParkingLotSize()==this.noOfSlotsAllotted;
	}
	
	
}
