package com.dodone.dodone.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String name;
    private String dueDate;
    private String createdDate;
    private boolean priority;
    private boolean done;
}
