package com.DKatalis.ParkingLot.enums;

public enum Operation {
	
	CREATE("create"),
	PARK("park"),
	LEAVE("leave"),
	STATUS("status");

	private final String name;
	
	private Operation(String name) {
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}

}
