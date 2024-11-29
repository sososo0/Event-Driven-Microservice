package com.msa.member.framework.jpaadapter;

import com.msa.member.application.outputport.MemberOutputPort;
import com.msa.member.domain.model.Member;
import com.msa.member.domain.model.vo.IDName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberAdapter implements MemberOutputPort {

    private final MemberRepository memberRepository;

    @Override
    public Member loadMember(long memberNo) {
        return memberRepository.findById(memberNo).get();
    }

    @Override
    public Member loadMemberByIdName(IDName idName) {
        return memberRepository.findMemberByIdName(idName).get();
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }
}
