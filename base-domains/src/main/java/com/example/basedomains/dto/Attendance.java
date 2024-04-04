package com.example.basedomains.dto;

import java.time.LocalDateTime;



public class Attendance {
	

    private Long id;

  
    private String empId;

    private LocalDateTime inTime;

    private LocalDateTime outTime;
    
    private boolean isPresent;
    
    private boolean isabsent; 
    
    private boolean isHalfday;

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

	public LocalDateTime getInTime() {
		return inTime;
	}

	public void setInTime(LocalDateTime inTime) {
		this.inTime = inTime;
	}

	public LocalDateTime getOutTime() {
		return outTime;
	}

	public void setOutTime(LocalDateTime outTime) {
		this.outTime = outTime;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public boolean isIsabsent() {
		return isabsent;
	}

	public void setIsabsent(boolean isabsent) {
		this.isabsent = isabsent;
	}

	public boolean isHalfday() {
		return isHalfday;
	}

	public void setHalfday(boolean isHalfday) {
		this.isHalfday = isHalfday;
	}


}
