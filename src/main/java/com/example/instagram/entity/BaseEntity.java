package com.example.instagram.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass   // 테이블 생성 X
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate    // 생성될 때 자동으로 사용
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate   // 수정될 때 자동으로 사용
    private LocalDateTime updatedAt;

}
