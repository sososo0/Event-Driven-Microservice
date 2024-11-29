package com.msa.member.framework.web.controller;

import com.msa.member.application.usecase.AddMemberUseCase;
import com.msa.member.application.usecase.InquiryMemberUseCase;
import com.msa.member.framework.web.dto.MemberInfoDTO;
import com.msa.member.framework.web.dto.MemberOutPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final AddMemberUseCase addMemberUseCase;
    private final InquiryMemberUseCase inquiryMemberUseCase;

    @PostMapping("/member")
    public ResponseEntity<MemberOutPutDTO> addMember(
        @RequestBody MemberInfoDTO memberInfoDTO
    ) {
        MemberOutPutDTO addedMember = addMemberUseCase.addMember(memberInfoDTO);
        return new ResponseEntity<>(addedMember, HttpStatus.CREATED);
    }

    @GetMapping("/member/{no}")
    public ResponseEntity<MemberOutPutDTO> getMember(
        @PathVariable("no") long no
    ) {
        MemberOutPutDTO member = inquiryMemberUseCase.getMember(no);
        return member != null ? new ResponseEntity<>(member, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
