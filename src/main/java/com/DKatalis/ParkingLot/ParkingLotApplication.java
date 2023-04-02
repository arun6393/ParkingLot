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
	public void run(String... args) throws Exception {
		try (Stream<String> stream = Files.lines(Paths.get(args[0]), StandardCharsets.UTF_8)) {
			
			stream.forEach(line-> {
				
				String[] splitText=line.split(" ");
				
				if(splitText[0].equals(Operation.CREATE.getName())) {
					createParkingLotService.create(Integer.parseInt(splitText[1]));
				}
				
				if(splitText[0].equals(Operation.PARK.getName())) {
					parkNewVehicleServic.park(splitText[1]);
				}
				
				if(splitText[0].equals(Operation.LEAVE.getName())) {
					unParkVehicleService.unpark(new UnParkDTO(splitText[1],Integer.parseInt(splitText[2])));
				}
				
				if(splitText[0].equals(Operation.STATUS.getName())) {
					parkingLotStatusService.status();
				}
				
				
				
			});
			
			} catch (IOException e) {
				
			}
		
	}

}