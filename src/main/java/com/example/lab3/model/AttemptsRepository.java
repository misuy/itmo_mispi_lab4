package com.example.lab3.model;

import com.example.lab3.entities.Attempt;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class AttemptsRepository {
    private final EntityManager manager;

    public AttemptsRepository(EntityManager manager) {
        this.manager = manager;
    }

    public List<Attempt> getAllAttempts() {
        return this.manager.createQuery("SELECT a FROM Attempt a", Attempt.class).getResultList();
    }

    public Attempt getAttemptById(long attemptId) {
        return this.manager.createQuery("SELECT a FROM Attempt a WHERE a.id=" + attemptId, Attempt.class).getSingleResult();
    }

    public void addAttempt(Attempt attempt) {
        System.out.println(":33333333333333333333333");
        EntityTransaction transaction = this.manager.getTransaction();
        transaction.begin();
        this.manager.persist(attempt);
        transaction.commit();
    }
}
