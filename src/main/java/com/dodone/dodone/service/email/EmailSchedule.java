package com.dodone.dodone.service.email;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;

@Service
@AllArgsConstructor
public class EmailSchedule {

    private final TodoEmailScheduler emailScheduler;

    private final int sec = 1000;

    @Scheduled(fixedDelay = sec)
    public void sendEmailSpecificTime() throws MessagingException {
        emailScheduler.sendEmail();
    }

}
