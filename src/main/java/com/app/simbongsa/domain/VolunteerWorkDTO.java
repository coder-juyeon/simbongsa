package com.app.simbongsa.domain;

import com.app.simbongsa.type.VolunteerWorkCategoryType;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class VolunteerWorkDTO {
    @NotNull
    private Long id;
    private LocalDateTime volunteerWorkStartDate;
    private LocalDateTime volunteerWorkEndDate;
    private int volunteerWorkTime;
    private LocalDate volunteerWorkJoinStartDate;
    private LocalDate volunteerWorkJoinEndDate;
    private int volunteerWorkRecruitNumber;
    private VolunteerWorkCategoryType volunteerWorkCategory;
    private String volunteerWorkRegisterAgency;
    private String volunteerWorkPlace;
    private String volunteerWorkTitle;

    @Builder
    public VolunteerWorkDTO(VolunteerWorkCategoryType volunteerWorkCategory, String volunteerWorkPlace, String volunteerWorkTitle) {
        this.volunteerWorkCategory = volunteerWorkCategory;
        this.volunteerWorkPlace = volunteerWorkPlace;
        this.volunteerWorkTitle = volunteerWorkTitle;
    }
}