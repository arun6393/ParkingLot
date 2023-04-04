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
	
	private int parkingLotSize=0;
	private int noOfSoltsAlloted=0;

	
	@Override
	public void initliaze(int parkingLotSize) {
		
		vehicleParkingLotDB.initliaze(parkingLotSize);
		this.parkingLotSize=parkingLotSize;
	}

	@Override
	public VehicleEntity insert(String vehicleNumber) {
		
		VehicleEntity vehicleEntity = null;
		VehicleEntity[] vehicles=vehicleParkingLotDB.getVehicles();
		for(int i=0;i<vehicles.length;i++) {
			if(vehicles[i]== null) {
				vehicleEntity= new VehicleEntity(i+1,vehicleNumber);
				vehicles[i]=vehicleEntity;
				noOfSoltsAlloted++;
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
				noOfSoltsAlloted--;
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
		return this.parkingLotSize;
	}

	@Override
	public Optional<VehicleEntity> findOne(String vehicleNumber) {
		
		if(this.noOfSoltsAlloted==0) {
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
	public int getNoOfSlotsAlloted() {
		return this.noOfSoltsAlloted;
	}

	@Override
	public boolean isParkingLotFull() {
		return this.parkingLotSize==this.noOfSoltsAlloted;
	}
	
	
}
