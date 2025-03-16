package com.dodone.dodone.service.email;

import com.dodone.dodone.entity.Todo;
import com.dodone.dodone.service.TodoService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoEmailScheduler {

    @Value("${EMAIL_ADDRESS}")
    private String emailAddress;

    private final TodoService todoService;

    public TodoEmailScheduler(TodoService todoService) {
        this.todoService = todoService;
    }

    private String subtractHour(String time) {
        LocalDateTime originalDateTime = LocalDateTime.parse(time,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime oneHourBefore = originalDateTime.minusHours(1);
        return oneHourBefore.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private String timeNow () {
        Date timeNow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(timeNow);
    }


    public void sendEmail() throws MessagingException {

        String timeNow = timeNow();

        List<Todo> todos = todoService.getTodos();
        for (Todo todo : todos) {

            if (!todo.getDueDate().isEmpty() ) {

                String time = todo.getDueDate();
                String formattedTime = subtractHour(time);

                if (todo.getDueDate() != null && formattedTime.equals(timeNow)) {
                    EmailService.sendMail(emailAddress, todo);
                }
            }
        }
    }
}
