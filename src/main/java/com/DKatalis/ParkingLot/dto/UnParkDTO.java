package com.DKatalis.ParkingLot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnParkDTO {
	
	private String vehicleNumber;
	private int duration;

}
