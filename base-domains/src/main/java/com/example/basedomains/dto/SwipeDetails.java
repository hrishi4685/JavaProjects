package com.example.basedomains.dto;

import java.time.LocalDateTime;



public class SwipeDetails {
	

    private Long id;

  
    private String empId;

    private LocalDateTime swipeTime;

    private String swipeType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getSwipeType() {
		return swipeType;
	}

	public void setSwipeType(String swipeType) {
		this.swipeType = swipeType;
	}

}
