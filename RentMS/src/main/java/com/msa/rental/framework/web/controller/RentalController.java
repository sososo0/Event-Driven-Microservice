package com.msa.rental.framework.web.controller;

import com.msa.rental.application.usecase.ClearOverdueItemUseCase;
import com.msa.rental.application.usecase.CreateRentalCardUseCase;
import com.msa.rental.application.usecase.InquiryUseCase;
import com.msa.rental.application.usecase.OverdueItemUseCase;
import com.msa.rental.application.usecase.RentItemUseCase;
import com.msa.rental.application.usecase.ReturnItemUseCase;
import com.msa.rental.framework.web.dto.ClearOverdueInfoDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import com.msa.rental.framework.web.dto.RentItemOutputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.RentalResultOutputDTO;
import com.msa.rental.framework.web.dto.ReturnItemOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDTO;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RentalController {

    private final RentItemUseCase rentItemUseCase;
    private final ReturnItemUseCase returnItemUseCase;
    private final CreateRentalCardUseCase createRentalCardUseCase;
    private final OverdueItemUseCase overdueItemUseCase;
    private final ClearOverdueItemUseCase clearOverdueItemUseCase;
    private final InquiryUseCase inquiryUseCase;

    @PostMapping("/rentalCard")
    public ResponseEntity<RentalCardOutputDTO> createRentalCard(
        @RequestBody UserInputDTO userInputDTO
    ) {
        RentalCardOutputDTO createRentalCard = createRentalCardUseCase.createRentalCard(userInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createRentalCard);
    }

    @GetMapping("/rentalCard/{userId}")
    public ResponseEntity<RentalCardOutputDTO> getRentalCard(
        @PathVariable String userId
    ) {
        Optional<RentalCardOutputDTO> rentalCard = inquiryUseCase.getRentalCard(new UserInputDTO(userId, ""));
        return ResponseEntity.ok(rentalCard.get());
    }

    @GetMapping("/rentalCard/{userId}/rentBook")
    public ResponseEntity<List<RentItemOutputDTO>> getAllRentItem(
        @PathVariable String userId
    ) {
        Optional<List<RentItemOutputDTO>> allRentItem = inquiryUseCase.getAllRentItem(new UserInputDTO(userId, ""));
        return ResponseEntity.ok(allRentItem.get());
    }

    @GetMapping("/rentalCard/{userId}/returnBook")
    public ResponseEntity<List<ReturnItemOutputDTO>> getAllReturnItem(
        @PathVariable String userId
    ) {
        Optional<List<ReturnItemOutputDTO>> allReturnItem = inquiryUseCase.getAllReturnItem(new UserInputDTO(userId, ""));
        return ResponseEntity.ok(allReturnItem.get());
    }

    @PostMapping("/rentalCard/rent")
    public ResponseEntity<RentalCardOutputDTO> rentItem(
        @RequestBody UserItemInputDTO userItemInputDTO
    ) throws Exception {
        RentalCardOutputDTO rentalCardOutputDTO = rentItemUseCase.rentItem(userItemInputDTO);
        return ResponseEntity.ok(rentalCardOutputDTO);
    }

    @PostMapping("/rentalCard/return")
    public ResponseEntity<RentalCardOutputDTO> returnItem(
        @RequestBody UserItemInputDTO userItemInputDTO
    ) throws Exception {
        RentalCardOutputDTO rentalCardOutputDTO = returnItemUseCase.returnItem(userItemInputDTO);
        return ResponseEntity.ok(rentalCardOutputDTO);
    }

    @PostMapping("/rentalCard/overdue")
    public ResponseEntity<RentalCardOutputDTO> overdueItem(
        @RequestBody UserItemInputDTO userItemInputDTO
    ) throws Exception {
        RentalCardOutputDTO rentalCardOutputDTO = overdueItemUseCase.overDueItem(userItemInputDTO);
        return ResponseEntity.ok(rentalCardOutputDTO);
    }

    @PostMapping("/rentalCard/clearOverdue")
    public ResponseEntity<RentalResultOutputDTO> clearOverdueItem(
        @RequestBody ClearOverdueInfoDTO clearOverdueInfoDTO
    ) throws Exception {
        RentalResultOutputDTO rentalResultOutputDTO = clearOverdueItemUseCase.clearOverdue(clearOverdueInfoDTO);
        return ResponseEntity.ok(rentalResultOutputDTO);
    }
}
