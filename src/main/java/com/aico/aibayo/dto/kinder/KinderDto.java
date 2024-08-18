package com.aico.aibayo.dto.kinder;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KinderDto {
    private Long kinderNo; // 유치원 번호
    private LocalTime kinderOpenTime; // 유치원 등원시간
    private LocalTime kinderCloseTime; // 유치원 하원시간
    private String kinderName; // 유치원 이름
    private String principalName; // 유치원 이름
    private String kinderPostCode;
    private String kinderTel;
    private String kinderAddr;
    private LocalDateTime kinderRegDate; // 유치원 등록일자
    private LocalDateTime kinderModifyDate; // 유치원 수정일자
    private LocalDateTime kinderDeleteDate; // 유치원 삭제일자
    private String sidoList; // API 시도 리스트
    private String sggList; // API 시군구 리스트

    private Integer announceStatus; //공지사항 상태
    private Integer notepadStatus; //알림장 상태
    private Integer mealStatus; //식단표 상태
    private Integer medicationStatus; //투약 의뢰서 상태
    private Integer returnHomeStatus; //귀가 동의서 상태
    private Integer attendanceStatus; //출석부 상태
    private Integer scheduleStatus; //일정표 상태
    private Integer pickDropStatus; //안심 승하차 상태
    private Integer lifeRecordStatus; //생활 기록 상태
    private Integer chatStatus; //열린소통 상태
}
