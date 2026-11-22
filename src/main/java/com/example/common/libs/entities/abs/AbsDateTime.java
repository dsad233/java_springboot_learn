package com.example.common.libs.entities.abs;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbsDateTime {
    @CreatedDate()
    @Column(name = "created_at", comment = "생성일")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", comment = "수정일")
    private LocalDateTime updatedAt;
}
