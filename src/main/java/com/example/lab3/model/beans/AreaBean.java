package com.example.lab3.model.beans;

import com.example.lab3.entities.Attempt;
import com.example.lab3.model.AttemptsRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ManagedProperty;
import jakarta.persistence.Persistence;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@ManagedBean(eager = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApplicationScoped
public class AreaBean implements AreaMXBean, Serializable {
    @ManagedProperty(value = "#{attemptsBean}")
    private AttemptsBean attemptsBean;

    @PostConstruct
    private void init() {
        RegistryUtil.register(this, "AreaBean");
    }

    @PreDestroy
    private void destroy() {
        RegistryUtil.register(this, "AreaBean");
    }

    @Override
    public Double getArea(long attemptId) {
        for (Attempt attempt: attemptsBean.getAllAttempts()) {
            if (attempt.getId() == attemptId) {
                double r = attempt.getDot().getR();
                return Math.PI * Math.pow(r / 2, 2) / 4 + Math.pow(r / 2, 2) / 2 + r * (r / 2);
            }
        }
        return null;
    }
}
