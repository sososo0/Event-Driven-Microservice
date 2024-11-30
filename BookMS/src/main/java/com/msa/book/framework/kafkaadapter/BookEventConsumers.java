package com.msa.book.framework.kafkaadapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.book.application.usecase.MakeAvailableUseCase;
import com.msa.book.application.usecase.MakeUnAvailableUseCase;
import com.msa.book.domain.model.event.BookEventType;
import com.msa.book.domain.model.event.EventResult;
import com.msa.book.domain.model.event.ItemRented;
import com.msa.book.domain.model.event.ItemReturned;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookEventConsumers {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MakeAvailableUseCase makeAvailableUseCase;
    private final MakeUnAvailableUseCase makeUnAvailableUseCase;
    private final BookEventProducer eventProducer;

    @KafkaListener(topics = "${consumer.topic1.name}", groupId = "${consumer.groupid.name}")
    public void consumeRental(ConsumerRecord<String, String> record) throws IOException {
        System.out.printf("rental_rent: " + record.value());

        ItemRented itemRented = objectMapper.readValue(record.value(), ItemRented.class);

        EventResult eventResult = new EventResult();
        eventResult.setEventType(BookEventType.RENT);
        eventResult.setIdName(itemRented.getIdName());
        eventResult.setItem(itemRented.getItem());
        eventResult.setPoint(itemRented.getPoint());

        try {
            System.out.printf("전송 받은 값: " + record.value());

            makeUnAvailableUseCase.unAvailable(itemRented.getItem().getNo());
            eventResult.setSuccessful(true);
        } catch(IllegalArgumentException e) {
            System.out.println("도서 상태가 논리적으로 맞지 않은 상태임");
            eventResult.setSuccessful(false);
        } catch (Exception e) {
            eventResult.setSuccessful(false);
        }

        eventProducer.occurEvent(eventResult);
    }

    @KafkaListener(topics = "${consumer.topic2.name}", groupId = "${consumer.groupid.name}")
    public void consumeReturn(ConsumerRecord<String, String> record) throws IOException {
        System.out.printf("rental_return" + record.value());

        ItemReturned itemReturned = objectMapper.readValue(record.value(), ItemReturned.class);

        EventResult eventResult = new EventResult();
        eventResult.setEventType(BookEventType.RETURN);
        eventResult.setIdName(itemReturned.getIdName());
        eventResult.setItem(itemReturned.getItem());
        eventResult.setPoint(itemReturned.getPoint());

        try {
            System.out.printf("전송 받은 값: " + record.value());

            makeAvailableUseCase.available(itemReturned.getItem().getNo());
            eventResult.setSuccessful(true);
        } catch(IllegalArgumentException e) {
            System.out.println("도서 상태가 논리적으로 맞지 않은 상태임");
            eventResult.setSuccessful(false);
        } catch (Exception e) {
            eventResult.setSuccessful(false);
        }

        eventProducer.occurEvent(eventResult);
    }
}
