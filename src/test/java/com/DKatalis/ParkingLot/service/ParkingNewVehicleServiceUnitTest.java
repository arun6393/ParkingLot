package com.DKatalis.ParkingLot.service;


import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.DKatalis.ParkingLot.dummyDAO.ParkingLotDAO;
import com.DKatalis.ParkingLot.entity.VehicleEntity;

import com.DKatalis.ParkingLot.service.impl.ParkNewVehicleServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ParkingNewVehicleServiceUnitTest {
	
	
	@Mock
	private ParkingLotDAO parkingLotDAO;
	
	private ParkNewVehicleService parkNewVehicleService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		parkNewVehicleService=new ParkNewVehicleServiceImpl(parkingLotDAO);
	}
	
	@Test
	public void PNVSLUT_01_create_parking_lot_full() {
		
		when(parkingLotDAO.isParkingLotFull()).thenReturn(true);
		
		parkNewVehicleService.park("1");
		Mockito.verify(parkingLotDAO, times(1)).isParkingLotFull();
		Mockito.verify(parkingLotDAO, times(0)).insert(any());
		
	}
	
	@Test
	public void PNVSLUT_02_create_null_vehicle_number() {
		
		when(parkingLotDAO.isParkingLotFull()).thenReturn(false);
		
		parkNewVehicleService.park(null);
		
		Mockito.verify(parkingLotDAO, times(1)).isParkingLotFull();
		Mockito.verify(parkingLotDAO, times(0)).insert(any());
		
	}
	
	@Test
	public void PNVSLUT_03_create_vehicle_already_parked() {
		
		when(parkingLotDAO.isParkingLotFull()).thenReturn(false);
		when(parkingLotDAO.findOne(any(String.class))).thenReturn(Optional.of(new VehicleEntity()));
		
		parkNewVehicleService.park("1");
		
		Mockito.verify(parkingLotDAO, times(1)).isParkingLotFull();
		Mockito.verify(parkingLotDAO, times(0)).insert(any());
		Mockito.verify(parkingLotDAO, times(1)).findOne(any());
		
	}
	
	@Test
	public void PNVSLUT_04_create_success() {
		
		when(parkingLotDAO.isParkingLotFull()).thenReturn(false);
		when(parkingLotDAO.findOne(any(String.class))).thenReturn(Optional.empty());
		when(parkingLotDAO.insert(any(String.class))).thenReturn(new VehicleEntity(1, "1"));
		
		parkNewVehicleService.park("1");
		
		Mockito.verify(parkingLotDAO, times(1)).isParkingLotFull();
		Mockito.verify(parkingLotDAO, times(1)).insert(any());
		Mockito.verify(parkingLotDAO, times(1)).findOne(any());
		
	}
}
