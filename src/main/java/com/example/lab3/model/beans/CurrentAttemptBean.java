package com.example.lab3.model.beans;

import com.example.lab3.entities.Attempt;
import com.example.lab3.entities.Dot;
import com.example.lab3.model.AttemptsRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.*;

import java.io.Serializable;

@ManagedBean(name = "currentAttemptBean")
@RequestScoped
public class CurrentAttemptBean implements Serializable {
    private Dot dot;

    @PostConstruct
    private void init() {
        this.dot = new Dot();
    }

    public Dot getDot() {
        return this.dot;
    }
}
