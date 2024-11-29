package com.msa.member.domain.model;

import com.msa.member.domain.model.vo.Authority;
import com.msa.member.domain.model.vo.Email;
import com.msa.member.domain.model.vo.IDName;
import com.msa.member.domain.model.vo.MemberPoint;
import com.msa.member.domain.model.vo.PassWord;
import com.msa.member.domain.model.vo.UserRole;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private Long MemberNo;
    private IDName idName;
    private PassWord password;
    private Email email;
    private List<Authority> authorities = new ArrayList<Authority>();
    private MemberPoint point;

    public static Member registerMember(IDName idName, PassWord pwd, Email email) {
        Member member = new Member();
        member.setIdName(idName);
        member.setPassword(pwd);
        member.setEmail(email);
        member.setPoint(MemberPoint.createPoint());
        member.addAuthority(new Authority(UserRole.USER));
        return member;
    }

    private void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public long savePoint(long point) {
        return this.point.addPoint(point);
    }

    public long usePoint(long point) throws Exception {
        return this.point.removePoint(point);
    }

    public Member login(IDName idName, PassWord pwd) {
        return this;
    }

    public void logout(IDName idName) {
    }
}
