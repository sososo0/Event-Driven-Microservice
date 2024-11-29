package com.msa.member.application.inputport;

import com.msa.member.application.usecase.SavePointUseCase;
import com.msa.member.domain.model.Member;
import com.msa.member.domain.model.vo.IDName;
import com.msa.member.application.outputport.MemberOutputPort;
import com.msa.member.framework.web.dto.MemberOutPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class SavePointInputPort implements SavePointUseCase {

    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutPutDTO savePoint(IDName idName, Long point) {
        Member loadMember = memberOutputPort.loadMemberByIdName(idName);
        loadMember.savePoint(point);
        return MemberOutPutDTO.mapToDTO(loadMember);
    }
}
