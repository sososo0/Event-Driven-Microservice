package com.msa.member.application.inputport;

import com.msa.member.application.usecase.UsePointUseCase;
import com.msa.member.domain.model.Member;
import com.msa.member.domain.model.vo.IDName;
import com.msa.member.application.outputport.MemberOutputPort;
import com.msa.member.framework.web.dto.MemberOutPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsePointInputPort implements UsePointUseCase {

    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutPutDTO userPoint(IDName idName, long point) throws Exception {
        Member loadMember = memberOutputPort.loadMemberByIdName(idName);
        loadMember.usePoint(point);
        return MemberOutPutDTO.mapToDTO(loadMember);
    }
}
