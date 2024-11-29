package com.msa.member.framework.jpaadapter;

import com.msa.member.domain.model.Member;
import com.msa.member.domain.model.vo.IDName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Optional<Member> findMemberByIdName(IDName idName);
}
