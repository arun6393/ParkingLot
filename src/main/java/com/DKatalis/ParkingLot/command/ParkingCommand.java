package com.DKatalis.ParkingLot.command;

public interface ParkingCommand {
	
	
	boolean isValidCommand(String[] command);
	
	void process(String[] command);
	
	default void execute(String[] command) {
		if(isValidCommand(command)) {
			process(command);
		}
	}

}
