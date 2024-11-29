package com.msa.member.domain.model.vo;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MemberPoint {

    public long pointValue;

    public long addPoint(long point) {
        this.setPointValue(this.pointValue + point);
        return this.pointValue;
    }

    public long removePoint(long point) throws Exception {
        if (point > this.pointValue) {
            throw new Exception("기존 보유 Point 보다 적어 삭제할 수 없습니다.");
        }
        this.setPointValue(this.pointValue - point);
        return this.pointValue;
    }

    public static MemberPoint createPoint() {
        return new MemberPoint(0L);
    }

    public static MemberPoint sample() {
        return new MemberPoint(10L);
    }
}
