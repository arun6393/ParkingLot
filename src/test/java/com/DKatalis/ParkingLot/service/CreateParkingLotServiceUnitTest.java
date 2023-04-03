package com.DKatalis.ParkingLot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.DKatalis.ParkingLot.dummyDAO.ParkingLotDAO;
import com.DKatalis.ParkingLot.service.impl.CreateParkingLotServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CreateParkingLotServiceUnitTest {
	
	
	@Mock
	private ParkingLotDAO parkingLotDAO;
	
	private CreateParkingLotService createParkingLotService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		createParkingLotService=new CreateParkingLotServiceImpl(parkingLotDAO);
	}
	
	@Test
	public void CPLSUT_01_create_size_less_then_zero() {
		
		
		RuntimeException exception=catchThrowableOfType(()->{
			createParkingLotService.create(-1);
		}, RuntimeException.class);
		
		
		assertThat(exception).hasMessage("Parking lot size cannot be 0 or negative");
		
	}
	
	@Test
	public void CPLSUT_02_create_size_zero() {
		
		
		RuntimeException exception=catchThrowableOfType(()->{
			createParkingLotService.create(0);
		}, RuntimeException.class);
		
		
		assertThat(exception).hasMessage("Parking lot size cannot be 0 or negative");
		
	}
	
	@Test
	public void CPLSUT_03_create_parking_lot_already_initialized() {
		
		when(parkingLotDAO.getParkingLotSize()).thenReturn(10);
		
		RuntimeException exception=catchThrowableOfType(()->{
			createParkingLotService.create(1);
		}, RuntimeException.class);
		
		
		assertThat(exception).hasMessage("Parking lot is already inialitized");
		
	}
	
	@Test
	public void CPLSUT_04_createsuccess() {
		
		when(parkingLotDAO.getParkingLotSize()).thenReturn(0).thenReturn(1);
		assertDoesNotThrow(()->{createParkingLotService.create(1);}); 
		
	}

}
