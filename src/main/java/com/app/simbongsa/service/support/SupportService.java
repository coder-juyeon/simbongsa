package com.app.simbongsa.service.support;

import com.app.simbongsa.domain.FileDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.SupportDTO;
import com.app.simbongsa.domain.SupportRequestDTO;
import com.app.simbongsa.entity.file.SupportRequestFile;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.entity.support.SupportRequest;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface SupportService {
    // 후원 참여 내역
    public Page<SupportDTO> getAllSupportAttendWithMember_QueryDSL(Integer page, Long id);
    // 후원 총 참여 수
    public Long getAllSupportAttend_QueryDSL(Long id);
    //    후원 요청 작성
    public void saveSupport(SupportDTO supportDTO, Long id);

    //    후원 공양미 업데이트 작성
    public void updateRice(int price, Long id);

//    내 후원 조회
    public Page<SupportDTO> getSupportById(Integer page, Long id);

    default SupportDTO toSupportDTO(Support support){
        return SupportDTO.builder()
                .id(support.getId())
                .memberDTO(toMemberDTO(support.getMember()))
                .supportPrice(support.getSupportPrice())
                .supportRequestDTO(toSupportRequestDTO(support.getSupportRequest()))
                .build();
    }
    default Support toSupportEntity(SupportDTO supportDTO){
        return Support.builder()
                .id(supportDTO.getId())
                .supportPrice(supportDTO.getSupportPrice())
                .supportRequest(toSupportRequestEntity(supportDTO.getSupportRequestDTO()))
                .member(toMemberEntity(supportDTO.getMemberDTO()))
                .build();
    }
    default SupportRequest toSupportRequestEntity(SupportRequestDTO supportRequestDTO){
        return SupportRequest.builder()
                .id(supportRequestDTO.getId())
                .supportRequestTitle(supportRequestDTO.getSupportRequestTitle())
                .supportRequestContent(supportRequestDTO.getSupportRequestContent())
                .supportRequestStatus(supportRequestDTO.getSupportRequestStatus())
                .build();
    }
    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder()
                .id(member.getId())
                .memberRank(member.getMemberRank())
                .memberName(member.getMemberName())
                .memberVolunteerTime(member.getMemberVolunteerTime())
                .memberAddress(member.getMemberAddress())
                .memberEmail(member.getMemberEmail())
                .memberAge(member.getMemberAge())
                .memberPassword(member.getMemberPassword())
                .memberInterest(member.getMemberInterest())
                .memberJoinType(member.getMemberJoinType())
                .memberRice(member.getMemberRice())
                .memberRole(member.getMemberRole())
                .memberStatus(member.getMemberStatus())
                .build();
    }
    default Member toMemberEntity(MemberDTO memberDTO) {
        return Member.builder().id(memberDTO.getId())
                .memberName(memberDTO.getMemberName())
                .memberEmail(memberDTO.getMemberEmail())
                .memberPassword(memberDTO.getMemberPassword())
                .memberAddress(memberDTO.getMemberAddress())
                .memberAge(memberDTO.getMemberAge())
                .memberInterest(memberDTO.getMemberInterest())
                .memberRole(memberDTO.getMemberRole())
                .memberJoinType(memberDTO.getMemberJoinType())
                .memberRank(memberDTO.getMemberRank())
                .memberRice(memberDTO.getMemberRice())
                .memberVolunteerTime(memberDTO.getMemberVolunteerTime())
                .randomKey(memberDTO.getRandomKey())
                .memberStatus(memberDTO.getMemberStatus())
                .build();
    }

    default SupportRequestDTO toSupportRequestDTO(SupportRequest supportRequest) {
        return SupportRequestDTO.builder()
                .id(supportRequest.getId())
                .supportRequestTitle(supportRequest.getSupportRequestTitle())
                .supportRequestContent(supportRequest.getSupportRequestContent())
                .supportRequestStatus(supportRequest.getSupportRequestStatus())
                .createdDate(supportRequest.getCreatedDate())
                .updatedDate(supportRequest.getUpdatedDate())
                .memberDTO(toMemberDTO(supportRequest.getMember()))
                .supportDTOS(toSupportDTO(supportRequest.getSupports()))
                .fileDTOS(toFileDTO(supportRequest.getSupportRequestFiles()))
                .build();
    }

    default List<SupportDTO> toSupportDTO(List<Support> supports){
        List<SupportDTO> supportDTOList = new ArrayList<>();
        supports.forEach(
                support -> {
                    SupportDTO supportDTO = SupportDTO.builder()
                            .id(support.getId())
                            .memberDTO(toMemberDTO(support.getMember()))
                            .supportPrice(support.getSupportPrice())
                            .build();
                    supportDTOList.add(supportDTO);
                }
        );
        return supportDTOList;
    }

    default List<FileDTO> toFileDTO(List<SupportRequestFile> supportRequestFiles){
        List<FileDTO> supportRequestFileDTOList = new ArrayList<>();
        supportRequestFiles.forEach(
                supportRequestFile -> {
                    FileDTO fileDTO = FileDTO.builder()
                            .id(supportRequestFile.getId())
                            .fileName(supportRequestFile.getFileName())
                            .filePath(supportRequestFile.getFilePath())
                            .fileUuid(supportRequestFile.getFileUuid())
                            .fileRepresentationalType(supportRequestFile.getFileRepresentationalType())
                            .build();
                    supportRequestFileDTOList.add(fileDTO);
                }
        );
        return supportRequestFileDTOList;
    }
}
