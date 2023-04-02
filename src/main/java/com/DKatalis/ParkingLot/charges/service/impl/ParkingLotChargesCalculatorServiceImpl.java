package com.DKatalis.ParkingLot.charges.service.impl;

import org.springframework.stereotype.Service;

import com.DKatalis.ParkingLot.charges.service.ChargesCalculatorService;


@Service
public class ParkingLotChargesCalculatorServiceImpl implements ChargesCalculatorService{

	
	@Override
	public long calculate(int duration) {
		
		int charges=10;
		int remainingDuration=duration-2;
		if(remainingDuration>0) {
			while(remainingDuration!=0) {
				remainingDuration--;
				charges=charges+10;
			}
		}
		
		return charges;
	}
	
	

}
