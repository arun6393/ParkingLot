package com.DKatalis.ParkingLot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.DKatalis.ParkingLot.dto.UnParkDTO;
import com.DKatalis.ParkingLot.enums.Operation;
import com.DKatalis.ParkingLot.service.CreateParkingLotService;
import com.DKatalis.ParkingLot.service.ParkNewVehicleService;
import com.DKatalis.ParkingLot.service.ParkingLotStatusService;
import com.DKatalis.ParkingLot.service.UnParkVehicleService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream;

@SpringBootApplication(scanBasePackages = { "com.DKatalis" })
public class ParkingLotApplication implements CommandLineRunner{
		
	@Autowired
	private CreateParkingLotService createParkingLotService;
	@Autowired
	private ParkingLotStatusService parkingLotStatusService;
	@Autowired
	private ParkNewVehicleService parkNewVehicleServic;
	@Autowired
	private UnParkVehicleService unParkVehicleService;
	
	public static void main(String[] args) {
		SpringApplication.run(ParkingLotApplication.class, args);

	}


	@Override
	public void run(String... args){
	
		if(args.length!=0 && args[0]!=null) {
		try (Stream<String> stream = Files.lines(Paths.get(args[0]), StandardCharsets.UTF_8)) {
			
			stream.forEach(line-> {
				
				String[] splitText=line.split(" ");
				String operation=splitText[0];
				
				if(Operation.CREATE.getName().equals(operation)) {
					createParkingLotService.inputValidation(splitText);
					createParkingLotService.create(Integer.parseInt(splitText[1]));
				}
				
				if(Operation.PARK.getName().equals(operation)) {
					parkNewVehicleServic.inputValidation(splitText);
					parkNewVehicleServic.park(splitText[1]);
				}
				
				if(Operation.LEAVE.getName().equals(operation)) {
					unParkVehicleService.inputValidation(splitText);
					unParkVehicleService.unpark(new UnParkDTO(splitText[1],Integer.parseInt(splitText[2])));
				}
				
				if(Operation.STATUS.getName().equals(operation)) {
					parkingLotStatusService.inputValidation(splitText);
					parkingLotStatusService.status();
				}
			});
			
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		
	}
	else {
	
		 System.out.println("Please provide file path");
	}
}
}
