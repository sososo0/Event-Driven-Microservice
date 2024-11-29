package com.msa.rental.domain.model.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RentalCardNo implements Serializable {

    // RentalCard 의 식별자가 되는 VO
    private String no;

    public static RentalCardNo createRentalCardNo() {
        UUID uuid = UUID.randomUUID();
        String year = String.valueOf(LocalDate.now().getYear());
        String str = year + "-" + uuid;

        RentalCardNo rentalCardNo = new RentalCardNo();
        rentalCardNo.setNo(str);

        return rentalCardNo;
    }

    // 샘플 코드 - 객체가 제대로 정의가 되었는지 확인하는 작업
    public static RentalCardNo sample() {
        return RentalCardNo.createRentalCardNo();
    }

    // 테스트 해보기
    // TODO : 추후에 지우기
    public static void main(String[] args) {
        System.out.println(RentalCardNo.sample());
    }
}
