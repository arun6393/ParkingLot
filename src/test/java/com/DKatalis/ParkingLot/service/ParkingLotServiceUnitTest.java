package com.DKatalis.ParkingLot.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.DKatalis.ParkingLot.dummyDAO.ParkingLotDAO;
import com.DKatalis.ParkingLot.entity.VehicleEntity;
import com.DKatalis.ParkingLot.service.impl.LotStatusServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ParkingLotServiceUnitTest {
	
	
	@Mock
	private ParkingLotDAO parkingLotDAO;
	
	private ParkingLotStatusService parkingLotStatusService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		parkingLotStatusService=new LotStatusServiceImpl(parkingLotDAO);
	}
	
	@Test
	public void PLSUT_01_status() {
		
		when(parkingLotDAO.fetchAll()).thenReturn(getVehiclesData());
		assertDoesNotThrow(()->{parkingLotStatusService.status();}); 
		
		
	}
	
	
	private VehicleEntity[] getVehiclesData() {
		VehicleEntity[] vehicles=new VehicleEntity[4];
		vehicles[0]=new VehicleEntity(0, "1234");
		vehicles[1]=new VehicleEntity(1, "12345");
		vehicles[2]=new VehicleEntity(2, "123456");
		vehicles[3]=null;
		
		return vehicles;
	}
	

}
