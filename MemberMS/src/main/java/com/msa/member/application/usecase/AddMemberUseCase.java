package com.msa.member.application.usecase;

import com.msa.member.framework.web.dto.MemberInfoDTO;
import com.msa.member.framework.web.dto.MemberOutPutDTO;

public interface AddMemberUseCase {
    public MemberOutPutDTO addMember(MemberInfoDTO memberInfoDTO);
}
