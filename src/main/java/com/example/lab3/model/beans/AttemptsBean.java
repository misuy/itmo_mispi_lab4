package com.example.lab3.model.beans;

import com.example.lab3.entities.Attempt;
import com.example.lab3.model.AttemptsRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "attemptsBean", eager = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApplicationScoped
public class AttemptsBean extends NotificationBroadcasterSupport implements AttemptsMXBean, Serializable {
    private int sequenceNumber = 0;

    private EntityManager entityManager;

    private AttemptsRepository attemptsRepository;

    @PostConstruct
    private void init() {
        this.entityManager = Persistence.createEntityManagerFactory("helios").createEntityManager();
        this.attemptsRepository = new AttemptsRepository(this.entityManager);
        RegistryUtil.register(this, "AttemptsBean");
    }

    @PreDestroy
    private void destroy() {
        this.entityManager.close();
        RegistryUtil.register(this, "AttemptsBean");
    }

    @Override
    public int getAttemptsCount() {
        return this.attemptsRepository.getAllAttempts().size();
    }

    @Override
    public int getSuccessfulAttemptsCount() {
        return (int) this.attemptsRepository.getAllAttempts().stream().filter(Attempt::getResult).count();
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[] { AttributeChangeNotification.ATTRIBUTE_CHANGE };
        String name = AttributeChangeNotification.class.getName();
        String description = "attempts count is a multiple of 15";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[] { info };
    }


    public void addAttempt(Attempt attempt) {
        this.attemptsRepository.addAttempt(attempt);
        if (this.attemptsRepository.getAllAttempts().size() % 15 == 0) {
            Notification notification = new Notification(
                    "multiple of 15",
                    getClass().getSimpleName(),
                    sequenceNumber++,
                    "attempts count is a multiple of 15"
            );
            sendNotification(notification);
        }
    }

    public List<Attempt> getAllAttempts() {
        return this.attemptsRepository.getAllAttempts();
    }

    public Attempt getAttemptById(long attemptId) {
        return this.attemptsRepository.getAttemptById(attemptId);
    }
}
