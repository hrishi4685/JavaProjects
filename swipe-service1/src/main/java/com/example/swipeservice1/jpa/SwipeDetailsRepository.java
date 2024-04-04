package com.example.swipeservice1.jpa;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SwipeDetailsRepository extends JpaRepository<SwipeDetails, Long> {
	
	@Query("SELECT e FROM SwipeDetails e WHERE e.empId = ?1 AND FORMATDATETIME(e.swipeTime,'yyyy-MM-dd') = ?2")
    List<SwipeDetails> findByEmpIdAndDate(String empId, LocalDate todaysDate);
	
	@Query("SELECT e FROM SwipeDetails e WHERE FORMATDATETIME(e.swipeTime,'yyyy-MM-dd') = ?1 AND e.swipeType= ?2")
    List<SwipeDetails> findBySwipeTime(LocalDate todaysDate,String swipeType);
	
}
