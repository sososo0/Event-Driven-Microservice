package com.msa.member.application.usecase;

import com.msa.member.domain.model.vo.IDName;
import com.msa.member.framework.web.dto.MemberOutPutDTO;

public interface UsePointUseCase {
    public MemberOutPutDTO userPoint(IDName idName, long point) throws Exception;
}
