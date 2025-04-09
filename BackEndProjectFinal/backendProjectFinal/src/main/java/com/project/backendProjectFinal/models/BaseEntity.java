package com.project.backendProjectFinal.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;

import java.time.LocalDateTime;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class BaseEntity {
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "entry_time")
    private LocalDateTime entryTime;

    @Column(name = "exit_time")
    private LocalDateTime exitTime;

    private String status;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        entryTime = LocalDateTime.now();
        this.status = "PROCESS";
    }

    @PreUpdate
    protected void onUpdate() {
        exitTime =LocalDateTime.now();
        this.status = "DONE";
    }
}
