package com.example.TaskManagementSystem.Task.Management.System.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TaskEntity {
    @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    boolean completed;

    public TaskEntity(){}

    public TaskEntity(int id,String name,boolean completed){
        this.id=id;
        this.name=name;
        this.completed=completed;
    }
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
