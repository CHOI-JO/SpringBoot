package org.choi.choiboard.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value={AuditingEntityListener.class}) // 엔티티 추가 및 변경 여부 확인
@Getter // DB에서 날짜 가져옴
abstract class BaseEntity {

    @CreatedDate // 생성일
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate // 수정일
    @Column(name = "moddate")
    private LocalDateTime modDate;

}
