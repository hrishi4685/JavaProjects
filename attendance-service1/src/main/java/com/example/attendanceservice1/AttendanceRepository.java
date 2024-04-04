package com.example.attendanceservice1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


	@Repository
	public interface AttendanceRepository extends JpaRepository<Attendancee, Long> {

}
