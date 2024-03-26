package com.example.swipeservice1.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.basedomains.dto.SwipeEvent;
import com.example.swipeservice1.kafka.SwipeProducer;






@RestController
@RequestMapping("/swipe")
public class SwipeContoller {

	public SwipeProducer swipeProducer;

    public SwipeContoller(SwipeProducer swipeProducer) {
        this.swipeProducer = swipeProducer;
    }

    @PostMapping("/saves")
    public String placeOrder(@RequestBody SwipeEvent SwipeEvent){

    	SwipeEvent.setSwipeTime(LocalDateTime.now());
        swipeProducer.sendMessage(SwipeEvent);

        return "Swiped successfully ...";
    }
}
