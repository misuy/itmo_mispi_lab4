package com.example.lab3.entities;

import com.example.lab3.model.DotProcessor;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "lab3_attempts")
public class Attempt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Embedded
    private Dot dot = null;
    private Boolean result = null;
    private ZonedDateTime processedAt = null;
    private Long processingTime = null;

    public Attempt() {}

    public Attempt(Dot dot, long startOfProcessing) {
        this.dot = dot;
        this.result = DotProcessor.processDot(dot);
        this.processedAt = ZonedDateTime.now();
        this.processingTime = System.nanoTime() - startOfProcessing;
    }

    public long getId() { return this.id; }

    public Dot getDot() { return this.dot; }

    public Boolean getResult() { return this.result; }

    public ZonedDateTime getProcessedAt() { return this.processedAt; }

    public Long getProcessingTime() { return this.processingTime; }
}
