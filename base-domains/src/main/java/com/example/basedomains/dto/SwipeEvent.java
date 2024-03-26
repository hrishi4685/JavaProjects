package com.example.basedomains.dto;

import java.time.LocalDateTime;

public class SwipeEvent {
	
	
	private String empId;
	private LocalDateTime swipeTime;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public LocalDateTime getSwipeTime() {
		return swipeTime;
	}
	public void setSwipeTime(LocalDateTime swipeTime) {
		this.swipeTime = swipeTime;
	}
	@Override
	public String toString() {
		return "SwipeEvent [empId=" + empId + ", swipeTime=" + swipeTime + "]";
	}

}
