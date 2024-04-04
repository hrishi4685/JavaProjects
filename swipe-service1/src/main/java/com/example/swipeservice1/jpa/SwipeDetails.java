package com.example.swipeservice1.jpa;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SwipeDetails {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

	/**
	 * @param empId
	 * @param swipeTime
	 */
	public SwipeDetails(String empId, LocalDateTime swipeTime) {
		super();
		this.empId = empId;
		this.swipeTime = swipeTime;
	}

	/**
	 * 
	 */
	public SwipeDetails() {
		
	}

}
