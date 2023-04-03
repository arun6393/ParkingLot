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

import com.DKatalis.ParkingLot.charges.service.ChargesCalculatorService;
import com.DKatalis.ParkingLot.dto.UnParkDTO;
import com.DKatalis.ParkingLot.dummyDAO.ParkingLotDAO;
import com.DKatalis.ParkingLot.entity.VehicleEntity;

import com.DKatalis.ParkingLot.service.impl.UnparkVehicleServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UnParkingVehicleServiceUnitTest {
	
	
	@Mock
	private ParkingLotDAO parkingLotDAO;
	@Mock
	private ChargesCalculatorService calculatorService;
	
	private UnParkVehicleService unParkVehicleService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		unParkVehicleService=new UnparkVehicleServiceImpl(parkingLotDAO,calculatorService);
	}
	
	
	@Test
	public void UPVSUT_01_1_unpark_null_vehicle_number() {
		
		unParkVehicleService.unpark(null);
		
		Mockito.verify(parkingLotDAO, times(0)).delete(any(String.class));
		Mockito.verify(parkingLotDAO, times(0)).findOne(any(String.class));
		
	}
	
	@Test
	public void UPVSUT_01_2_unpark_null_vehicle_number() {
		
		unParkVehicleService.unpark(new UnParkDTO());
		
		Mockito.verify(parkingLotDAO, times(0)).delete(any(String.class));
		Mockito.verify(parkingLotDAO, times(0)).findOne(any(String.class));
		
	}
	
	@Test
	public void UPVSUT_02_unpark_vehicle_not_found_in_parking() {
		
		
		when(parkingLotDAO.findOne(any(String.class))).thenReturn(Optional.empty());
		
		unParkVehicleService.unpark(new UnParkDTO("1", 1));
		
		Mockito.verify(parkingLotDAO, times(0)).delete(any(String.class));
		Mockito.verify(parkingLotDAO, times(1)).findOne(any(String.class));
		
	}
	
	@Test
	public void UPVSUT_02_unpark_success() {
		
		when(parkingLotDAO.findOne(any(String.class))).thenReturn(Optional.of(new VehicleEntity(1, "1")));
		when(parkingLotDAO.delete(any(String.class))).thenReturn(new VehicleEntity(1, "1"));
		when(calculatorService.calculate(any(Integer.class))).thenReturn(10L);
		
		unParkVehicleService.unpark(new UnParkDTO("1", 1));
		
		Mockito.verify(parkingLotDAO, times(1)).findOne(any());
		Mockito.verify(calculatorService, times(1)).calculate(any(Integer.class));
		Mockito.verify(parkingLotDAO, times(1)).delete(any());
	}
}
