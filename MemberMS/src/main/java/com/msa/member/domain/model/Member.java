package com.msa.member.domain.model;

import com.msa.member.domain.model.vo.Authority;
import com.msa.member.domain.model.vo.Email;
import com.msa.member.domain.model.vo.IDName;
import com.msa.member.domain.model.vo.MemberPoint;
import com.msa.member.domain.model.vo.PassWord;
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
      private List<Authority> authorites = new ArrayList<Authority>();
      private MemberPoint point;
}
