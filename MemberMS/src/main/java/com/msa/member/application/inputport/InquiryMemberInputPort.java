package com.msa.member.application.inputport;

import com.msa.member.application.usecase.InquiryMemberUseCase;
import com.msa.member.domain.model.Member;
import com.msa.member.framework.jpaadapter.MemberOutputPort;
import com.msa.member.framework.web.dto.MemberOutPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InquiryMemberInputPort implements InquiryMemberUseCase {

    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutPutDTO getMember(long memberNo) {
        Member loadMember = memberOutputPort.loadMember(memberNo);
        return MemberOutPutDTO.mapToDTO(loadMember);
    }
}
