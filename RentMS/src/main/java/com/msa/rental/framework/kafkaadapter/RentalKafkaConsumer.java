package com.msa.rental.framework.kafkaadapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.rental.application.usecase.CompensationUseCase;
import com.msa.rental.domain.model.event.BookEventType;
import com.msa.rental.domain.model.event.EventResult;
import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.Item;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalKafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(RentalKafkaConsumer.class);

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CompensationUseCase compensationUseCase;

    @KafkaListener(topics = "${consumer.topic1.name}", groupId = "${consumer.groupid.name}")
    public void consumeRent(ConsumerRecord<String, String> record) throws Exception {

        try {
            System.out.println("rental_rent: " + record.value());

            EventResult eventResult = objectMapper.readValue(record.value(), EventResult.class);
            IDName idName = eventResult.getIdName();
            Item item = eventResult.getItem();
            long point = eventResult.getPoint();

            if (!eventResult.isSuccessful()) {
                BookEventType eventType = eventResult.getEventType();

                System.out.println("eventType =" + eventType.toString());

                switch (eventType) {
                    case RENT:
                        compensationUseCase.cancelRentItem(idName, item);
                        System.out.println("대여 취소 보상 트랜잭션 실행");
                        break;
                    case RETURN:
                        compensationUseCase.cancelReturnItem(idName, item, point);
                        System.out.println("반납취소 보상트랜젝션 실행");
                        break;
                    case OVERDUE:
                        compensationUseCase.cancelMakeAvailableRental(idName, point);
                        System.out.println("연체해제처리취소 보상트랜젝션 실행");
                        break;
                    default:
                }
            } else {
                System.out.println("eventResult.isSuccessed()" + eventResult.isSuccessful());
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
