package com.samin.hchart.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass   // 테이블이 생성 되지 않음
@EntityListeners(value = { AuditingEntityListener.class })      // JPA 내부에서 엔티티 객체가 생성/변경 되는 것을 감지
@Getter
abstract class BaseEntity {

    @CreatedDate    // 엔티티의 생성 시간을 처리
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate   // 최종 수정 시간을 자동으로 처리
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
