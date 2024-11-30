package com.msa.member.framework.kafkaadapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.member.application.usecase.SavePointUseCase;
import com.msa.member.application.usecase.UsePointUseCase;
import com.msa.member.domain.model.event.BookEventType;
import com.msa.member.domain.model.event.EventResult;
import com.msa.member.domain.model.event.ItemRented;
import com.msa.member.domain.model.event.ItemReturned;
import com.msa.member.domain.model.event.OverdueCleared;
import com.msa.member.domain.model.event.PointUseCommand;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberEventConsumers {

    private final Logger log = LoggerFactory.getLogger(MemberEventConsumers.class);

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SavePointUseCase savePointUseCase;
    private final UsePointUseCase usePointUseCase;
    private final MemberEventProducer memberEventProducer;

    @KafkaListener(topics = "${consumer.topic1.name}", groupId = "${consumer.groupid.name}")
    public void consumeRent(ConsumerRecord<String, String> record) throws IOException {
        System.out.println("rental_rent: " + record.value());
        ItemRented itemRented = objectMapper.readValue(record.value(), ItemRented.class);

        savePointUseCase.savePoint(itemRented.getIdName(), itemRented.getPoint());
    }

    @KafkaListener(topics = "${consumer.topic2.name}", groupId = "${consumer.groupid.name}")
    public void consumeReturn(ConsumerRecord<String, String> record) throws IOException {
        System.out.println("rental_return: " + record.value());
        ItemReturned itemReturned = objectMapper.readValue(record.value(), ItemReturned.class);

        savePointUseCase.savePoint(itemReturned.getIdName(), itemReturned.getPoint());
    }

    @KafkaListener(topics = "${consumer.topic3.name}", groupId = "${consumer.groupid.name}")
    public void consumeClear(ConsumerRecord<String, String> record) throws Exception {
        System.out.println("overdue_clear: " + record.value());
        OverdueCleared overdueCleared = objectMapper.readValue(record.value(), OverdueCleared.class);

        EventResult eventResult = new EventResult();
        eventResult.setEventType(BookEventType.OVERDUE);
        eventResult.setIdName(overdueCleared.getIdName());
        eventResult.setPoint(overdueCleared.getPoint());

        System.out.printf(record.value());

        try {
            usePointUseCase.userPoint(overdueCleared.getIdName(), overdueCleared.getPoint());
            eventResult.setSuccessful(true);
        } catch (Exception e ) {
            eventResult.setSuccessful(false);
        }

        memberEventProducer.occurEvent(eventResult);
    }

    @KafkaListener(topics = "${consumer.topic4.name}", groupId = "${consumer.groupid.name}")
    public void consumeUsePoint(ConsumerRecord<String, String> record) throws Exception {
        System.out.println("rental_return: " + record.value());
        PointUseCommand pointUseCommand = objectMapper.readValue(record.value(), PointUseCommand.class);

        try {
            usePointUseCase.userPoint(pointUseCommand.getIdName(), pointUseCommand.getPoint());
        } catch(Exception e) {
            throw e;
        }
    }
}
