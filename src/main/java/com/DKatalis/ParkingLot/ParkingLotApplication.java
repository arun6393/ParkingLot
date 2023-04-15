package com.DKatalis.ParkingLot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.DKatalis.ParkingLot.command.ParkingCommand;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication(scanBasePackages = { "com.DKatalis" })
public class ParkingLotApplication implements CommandLineRunner{
		
	@Autowired
	List<ParkingCommand> commands;
	
	public static void main(String[] args) {
		SpringApplication.run(ParkingLotApplication.class, args);

	}


	@Override
	public void run(String... args){
	
		if(args.length!=0 && args[0]!=null) {
		try (Stream<String> stream = Files.lines(Paths.get(args[0]), StandardCharsets.UTF_8)) {
			
			stream.forEach(line-> {
				
				String[] splitText=line.split(" ");
				
				for(ParkingCommand command:commands) {
					command.execute(splitText);
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
