package com.msa.rental.framework.kafkaadapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.msa.rental.application.outputport.EventOutputPort;
import com.msa.rental.domain.model.event.ItemRented;
import com.msa.rental.domain.model.event.ItemReturned;
import com.msa.rental.domain.model.event.OverdueCleared;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
public class RentalKafkaProducer implements EventOutputPort {

    @Value(value = "${producer.topic1.name}")
    private String TOPIC_RENT;

    @Value(value = "${producer.topic2.name}")
    private String TOPIC_RETURN;

    @Value(value = "${producer.topic3.name}")
    private String TOPIC_CLEAR;

    private final KafkaTemplate<String, ItemRented> kafkaTemplate1;
    private final KafkaTemplate<String, ItemReturned> kafkaTemplate2;
    private final KafkaTemplate<String, OverdueCleared> kafkaTemplate3;

    @Override
    public void occurRentalEvent(ItemRented rentalItem) throws JsonProcessingException {

        ListenableFuture<SendResult<String, ItemRented>> future = this.kafkaTemplate1.send(TOPIC_RENT, rentalItem);

        future.addCallback(new ListenableFutureCallback<SendResult<String, ItemRented>>() {

            private final Logger LOGGER = LoggerFactory.getLogger(RentalKafkaProducer.class);

            @Override
            public void onSuccess(SendResult<String, ItemRented> result) {
                ItemRented g = result.getProducerRecord().value();
                LOGGER.info("Sent message=[" + g.getItem().getNo()+"] with offset=["
                    +result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("Unable to sent message=[" + rentalItem.getItem().getNo() + "] due to : "
                    + ex.getMessage(), ex);
            }
        });
    }

    @Override
    public void occurReturnEvent(ItemReturned returnItem) throws JsonProcessingException {

        ListenableFuture<SendResult<String, ItemReturned>> future = this.kafkaTemplate2.send(TOPIC_RETURN, returnItem);

        future.addCallback(new ListenableFutureCallback<SendResult<String, ItemReturned>>() {

            private final Logger LOGGER = LoggerFactory.getLogger(RentalKafkaProducer.class);

            @Override
            public void onSuccess(SendResult<String, ItemReturned> result) {
                ItemReturned g = result.getProducerRecord().value();
                LOGGER.info("Sent message=[" + g.getItem().getNo()+"] with offset=["
                    +result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("Unable to sent message=[" + returnItem.getItem().getNo() + "] due to : "
                    + ex.getMessage(), ex);
            }
        });
    }

    @Override
    public void occurOverdueClearedEvent(OverdueCleared overdueCleared) throws JsonProcessingException {

        ListenableFuture<SendResult<String, OverdueCleared>> future = this.kafkaTemplate3.send(TOPIC_CLEAR, overdueCleared);

        future.addCallback(new ListenableFutureCallback<SendResult<String, OverdueCleared>>() {

            private final Logger LOGGER = LoggerFactory.getLogger(RentalKafkaProducer.class);

            @Override
            public void onSuccess(SendResult<String, OverdueCleared> result) {
                OverdueCleared g = result.getProducerRecord().value();
                LOGGER.info("Sent message=[" + g.getIdName().getId()+"] with offset=["
                    +result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("Unable to sent message=[" + overdueCleared.getIdName().getId() + "] due to : "
                    + ex.getMessage(), ex);
            }
        });
    }
}
