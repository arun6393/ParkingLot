package com.DKatalis.ParkingLot.enums;

public enum Operation {
	
	CREATE("create",2),
	PARK("park",2),
	LEAVE("leave",3),
	STATUS("status",1);

	private final String name;
	private final int arraySize;
	
	Operation(String name,int arraySize) {
		this.name=name;
		this.arraySize=arraySize;
	}
	
	public String getName() {
		return this.name;
	}

	public int getArraySize(){
		return this.arraySize;
	}

}
