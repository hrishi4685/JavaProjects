



package com.example.attendanceservice1.kafka;

import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.attendanceservice1.Attendancee;
import com.example.attendanceservice1.AttendanceRepository;
import com.example.basedomains.dto.Attendance;



@Service
public class SwipeConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(SwipeConsumer.class);
	
	@Autowired
	private AttendanceRepository repo;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(Attendance a){
        LOGGER.info(String.format("Swipe event received in Attendance service => %s", a.toString()));
        
        List<Attendancee> attendanceEmpList=new ArrayList<Attendancee>();        
       
        	
        	Attendancee e=new Attendancee();
        	e.setEmpId(a.getEmpId() );
        	e.setInTime(a.getInTime());
        	e.setHalfday(a.isHalfday() );
        	e.setOutTime( a.getOutTime());
        	e.setPresent(a.isPresent() );
        	e.setIsabsent(a.isIsabsent());
        	
        	attendanceEmpList.add(e);
        
        
        
        repo.saveAll(attendanceEmpList);
        
        // send an email to the customer
    }
}
