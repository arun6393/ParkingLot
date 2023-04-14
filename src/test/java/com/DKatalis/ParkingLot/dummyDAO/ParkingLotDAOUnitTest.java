package com.DKatalis.ParkingLot.dummyDAO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.DKatalis.ParkingLot.dummyDAO.impl.ParkingLotDAOImpl;
import com.DKatalis.ParkingLot.dummyDB.VehicleParkingLotDB;
import com.DKatalis.ParkingLot.entity.VehicleEntity;



public class ParkingLotDAOUnitTest {

	@Mock
	private VehicleParkingLotDB vehicleParkingLotDB;
	
	private ParkingLotDAO parkingLotDAO;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		parkingLotDAO=new ParkingLotDAOImpl(vehicleParkingLotDB);
	}
	
	
	@Test
	public void PLDUT_01_initialize_success() {
		parkingLotDAO.initialize(1);

		when(vehicleParkingLotDB.getVehicles()).thenReturn(getVehiclesData());

		assertThat(parkingLotDAO.getParkingLotSize()).isEqualTo(4);
		
		verify(vehicleParkingLotDB, times(1)).initialize(anyInt());
		verify(vehicleParkingLotDB, times(1)).getVehicles();
		
	}
	
	
	@Test
	public void PLDUT_02_1_insert_at_index_0() {
		
		VehicleEntity[] vehicles=new VehicleEntity[4];
		when(vehicleParkingLotDB.getVehicles()).thenReturn(vehicles);
		
		VehicleEntity vehicle=parkingLotDAO.insert("1");
		
		assertThat(vehicle.getAllotmentId()).isEqualTo(1);
		assertThat(parkingLotDAO.getNoOfSlotsAllotted()).isEqualTo(1);
		assertThat(vehicles[0]).isNotNull();
	}
	
	@Test
	public void PLDUT_02_2_insert_at_last_index() {
		
		VehicleEntity[] vehicles=getVehiclesData();
		when(vehicleParkingLotDB.getVehicles()).thenReturn(vehicles);
		
		VehicleEntity vehicle=parkingLotDAO.insert("1");
		
		assertThat(vehicle.getAllotmentId()).isEqualTo(4);
		assertThat(vehicles[0]).isNotNull();
	}
	
	@Test
	public void PLDUT_02_3_insert_at_mid() {
		
		
		VehicleEntity[] vehicles=getVehiclesData1();
		when(vehicleParkingLotDB.getVehicles()).thenReturn(vehicles);
		assertThat(vehicles[1]).isNull();
		
		
		VehicleEntity vehicle=parkingLotDAO.insert("1");
		
		assertThat(vehicle.getAllotmentId()).isEqualTo(2);
		assertThat(vehicles[1]).isNotNull();
	}
	
	
	// delete
	@Test
	public void PLDUT_03_1_delete_at_index_0() {
		
		VehicleEntity[] vehicles=getVehiclesData();
		when(vehicleParkingLotDB.getVehicles()).thenReturn(vehicles);
		
		VehicleEntity vehicle=parkingLotDAO.delete("1234");
		
		assertThat(vehicle.getAllotmentId()).isEqualTo(1);
		assertThat(vehicles[0]).isNull();
	}
	
	@Test
	public void PLDUT_03_2_delete_at_last_index() {
		
		VehicleEntity[] vehicles=getVehiclesData1();
		when(vehicleParkingLotDB.getVehicles()).thenReturn(vehicles);
		assertThat(vehicles[3]).isNotNull();
		
		VehicleEntity vehicle=parkingLotDAO.delete("123456");
		
		assertThat(vehicle.getAllotmentId()).isEqualTo(4);
		assertThat(vehicles[3]).isNull();
	}
	
	@Test
	public void PLDUT_03_3_delete_at_mid() {
		
		
		VehicleEntity[] vehicles=getVehiclesData1();
		when(vehicleParkingLotDB.getVehicles()).thenReturn(vehicles);
		assertThat(vehicles[2]).isNotNull();
		
		
		VehicleEntity vehicle=parkingLotDAO.delete("12345");
		
		assertThat(vehicle.getAllotmentId()).isEqualTo(3);
		assertThat(vehicles[2]).isNull();
	}
	
	
	@Test
	public void PLDUT_04_fetchAll() {
		
		when(vehicleParkingLotDB.getVehicles()).thenReturn(getVehiclesData1());
		
		VehicleEntity[] vehicles=parkingLotDAO.fetchAll();
		
		assertThat(vehicles.length).isEqualTo(getVehiclesData1().length);
		
	}
	
	
	
	
	private VehicleEntity[] getVehiclesData() {
		VehicleEntity[] vehicles=new VehicleEntity[4];
		vehicles[0]=new VehicleEntity(1, "1234");
		vehicles[1]=new VehicleEntity(2, "12345");
		vehicles[2]=new VehicleEntity(3, "123456");
		vehicles[3]=null;
		
		return vehicles;
	}
	
	private VehicleEntity[] getVehiclesData1() {
		VehicleEntity[] vehicles=new VehicleEntity[4];
		vehicles[0]=new VehicleEntity(1, "1234");
		vehicles[2]=new VehicleEntity(3, "12345");
		vehicles[3]=new VehicleEntity(4, "123456");
		
		return vehicles;
	}


}
