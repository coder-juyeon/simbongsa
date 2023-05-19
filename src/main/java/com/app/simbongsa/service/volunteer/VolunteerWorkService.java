package com.app.simbongsa.service.volunteer;

import com.app.simbongsa.domain.FileDTO;
import com.app.simbongsa.domain.InquiryDTO;
import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.file.File;
import com.app.simbongsa.entity.file.VolunteerWorkFile;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.search.admin.AdminVolunteerSearch;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface VolunteerWorkService {
    // 1. 메인페이지 - 봉사 활동 8개 TEST
    public List<VolunteerWorkDTO> getVolunteerList();

//    현재 시퀀스 가져오기
    public VolunteerWork getCurrentSequence();

//    봉사 전체 조회
    public Page<VolunteerWorkDTO> getVolunteerWork(Integer page, AdminVolunteerSearch adminVolunteerSearch);

//    봉사 등록
    public void saveVolunteerWork(VolunteerWorkDTO volunteerWorkDTO);

//    봉사 상세보기
    public VolunteerWorkDTO getVolunteerWorkDetail(Long id);

//    봉사 삭제
    public void deleteVolunteerWorkByIds(List<Long> ids);

    default VolunteerWorkDTO toVolunteerWorkDTO(VolunteerWork volunteerWork){
        return VolunteerWorkDTO.builder()
                .volunteerWorkTitle(volunteerWork.getVolunteerWorkTitle())
                .volunteerWorkPlace(volunteerWork.getVolunteerWorkPlace())
                .volunteerWorkCategory(volunteerWork.getVolunteerWorkCategory())
                .id(volunteerWork.getId())
                .volunteerWorkEndDate(volunteerWork.getVolunteerWorkEndDate())
//                .fileDTO(toFileDTO(volunteerWork.getVolunteerWorkFile()))
                .volunteerWorkJoinEndDate(volunteerWork.getVolunteerWorkJoinEndDate())
                .volunteerWorkJoinStartDate(volunteerWork.getVolunteerWorkJoinStartDate())
                .volunteerWorkRecruitNumber(volunteerWork.getVolunteerWorkRecruitNumber())
                .volunteerWorkRegisterAgency(volunteerWork.getVolunteerWorkRegisterAgency())
                .volunteerWorkStartDate(volunteerWork.getVolunteerWorkStartDate())
                .volunteerWorkTime(volunteerWork.getVolunteerWorkTime())
                .build();
    }

    default FileDTO toFileDTO(VolunteerWorkFile volunteerWorkFile) {
        return FileDTO.builder()
                .fileName(volunteerWorkFile.getFileName())
                .filePath(volunteerWorkFile.getFilePath())
                .fileUuid(volunteerWorkFile.getFileUuid())
                .fileRepresentationalType(volunteerWorkFile.getFileRepresentationalType())
                .build();
    }

    default List<FileDTO> FileToDTO(List<VolunteerWorkFile> volunteerWorkFiles){
        List<FileDTO> volunteerFileList = new ArrayList<>();
        volunteerWorkFiles.forEach(
                volunteerFile->{
                    FileDTO fileDTO = FileDTO.builder()
                            .id(volunteerFile.getId())
                            .fileName(volunteerFile.getFileName())
                            .fileUuid(volunteerFile.getFileUuid())
                            .fileRepresentationalType(volunteerFile.getFileRepresentationalType())
                            .filePath(volunteerFile.getFilePath())
                            .build();
                    volunteerFileList.add(fileDTO);
                } );
        return volunteerFileList;
    }

    default VolunteerWork toVolunteerWorkEntity(VolunteerWorkDTO volunteerWorkDTO){
        return VolunteerWork.builder()
                .volunteerWorkCategory(volunteerWorkDTO.getVolunteerWorkCategory())
                .volunteerWorkEndDate(volunteerWorkDTO.getVolunteerWorkEndDate())
                .volunteerWorkJoinEndDate(volunteerWorkDTO.getVolunteerWorkJoinEndDate())
                .volunteerWorkJoinStartDate(volunteerWorkDTO.getVolunteerWorkJoinStartDate())
//                .volunteerWorkFile(toVolunteerWorkFileEntity(volunteerWorkDTO.getFileDTO()))
                .volunteerWorkPlace(volunteerWorkDTO.getVolunteerWorkPlace())
                .volunteerWorkRecruitNumber(volunteerWorkDTO.getVolunteerWorkRecruitNumber())
                .volunteerWorkRegisterAgency(volunteerWorkDTO.getVolunteerWorkRegisterAgency())
                .volunteerWorkStartDate(volunteerWorkDTO.getVolunteerWorkStartDate())
                .volunteerWorkTime(volunteerWorkDTO.getVolunteerWorkTime())
                .volunteerWorkTitle(volunteerWorkDTO.getVolunteerWorkTitle())
                .volunteerWorkRegisterAgency(volunteerWorkDTO.getVolunteerWorkRegisterAgency())
                .build();
    }

    default VolunteerWorkFile toVolunteerWorkFileEntity(FileDTO fileDTO) {
        return VolunteerWorkFile.builder()
                .fileName(fileDTO.getFileName())
                .filePath(fileDTO.getFilePath())
                .fileUuid(fileDTO.getFileUuid())
                .fileRepresentationalType(fileDTO.getFileRepresentationalType())
                .volunteerWork(fileDTO.getVolunteerWork())
                .build();
    }
}
