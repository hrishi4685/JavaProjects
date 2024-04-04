package com.example.swipeservice1.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.basedomains.dto.Attendance;
import com.example.swipeservice1.jpa.SwipeDetails;
import com.example.swipeservice1.jpa.SwipeDetailsRepository;
import com.example.swipeservice1.kafka.SwipeProducer;






@RestController
@RequestMapping("/swipe")
public class SwipeContoller {

	public SwipeProducer swipeProducer;
	@Autowired
	public SwipeDetailsRepository repo;

    public SwipeContoller(SwipeProducer swipeProducer) {
        this.swipeProducer = swipeProducer;
    }

    @PostMapping("/saves")
    public String swipe(@RequestBody SwipeDetails swipeDetails){

    	swipeDetails.setSwipeTime(LocalDateTime.now());
        //swipeProducer.sendMessage(swipeDetails);
    	List<SwipeDetails> currentDateRecords=repo.findByEmpIdAndDate(swipeDetails.getEmpId(), swipeDetails.getSwipeTime().toLocalDate());
    	
    	if(currentDateRecords.isEmpty()) {
    		swipeDetails.setSwipeType("In");
    		repo.save(swipeDetails);
    	}else {
    		swipeDetails.setSwipeType("Out");
    		repo.save(swipeDetails);
    	}
        return "Swiped successfully ...";
    }
    
    @GetMapping("/calculates")
    public String calculateAttendance(){
    	
    	
    	List<SwipeDetails> IntimeList=repo.findBySwipeTime(LocalDateTime.now().toLocalDate(),"In");
    	
    	Map<String, LocalDateTime> inTimeMap=IntimeList.stream()
                .collect(Collectors.groupingBy(SwipeDetails::getEmpId,
                        Collectors.mapping(SwipeDetails::getSwipeTime, Collectors.maxBy(Comparator.naturalOrder()))))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().orElse(LocalDateTime.MIN)));

    	List<SwipeDetails> OutTimeList=repo.findBySwipeTime(LocalDateTime.now().toLocalDate(),"Out");
    	
    	Map<String, LocalDateTime> outTimeMap=OutTimeList.stream()
        .collect(Collectors.groupingBy(SwipeDetails::getEmpId,
                Collectors.mapping(SwipeDetails::getSwipeTime, Collectors.minBy(Comparator.naturalOrder()))))
        .entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().orElse(LocalDateTime.now())));
    	
    	
    	List<Attendance> attendanceList= new ArrayList<>();
    	

    	
    	inTimeMap.forEach((empId, swipTime) -> {
    		
    		Attendance a = new Attendance();
    		a.setEmpId(empId);
    		a.setInTime((LocalDateTime)swipTime);
    		a.setOutTime(outTimeMap.get(empId));
    		Long timeDiff=Duration.between(a.getInTime(),a.getOutTime()).toHours();
    		if (Duration.between(a.getInTime(),a.getOutTime()).toHours() < 4) {
    			a.setIsabsent(true);    			
    		}else if (timeDiff > 4 && timeDiff < 8) {
    			
    			a.setHalfday(true);
    		}else if(timeDiff >=8 ) {
    			a.setPresent(true); 
    			
    		}
    		
    		attendanceList.add(a);
    		
    	});
    	
 
    	
    	swipeProducer.sendMessage(attendanceList);
    	
        return "Swiped successfully ...";
    }
    
    
}
