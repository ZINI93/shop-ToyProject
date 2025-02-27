package com.zinikai.shop.controller.api;

import com.zinikai.shop.domain.payment.dto.PaymentRequestDto;
import com.zinikai.shop.domain.payment.dto.PaymentResponseDto;
import com.zinikai.shop.domain.payment.dto.PaymentUpdateDto;
import com.zinikai.shop.domain.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentApiController {

    private final PaymentService paymentService;

    // payment 생성 - POSTMEN TEST 完了

    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody PaymentRequestDto requestDto){
        PaymentResponseDto savedPayment = paymentService.createPayment(requestDto);
        URI location = URI.create("/api/payments/" + savedPayment.getId());
        return ResponseEntity.created(location).body(savedPayment);
    }
    // product IDで探す- POSTMEN TEST 完了
    @GetMapping("{paymentId}")
    public ResponseEntity<PaymentResponseDto> findByPaymentId(@PathVariable Long paymentId){
        PaymentResponseDto paymentById = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(paymentById);
    }

    // product アップデート- POSTMEN TEST 完了
    @PutMapping("{paymentId}")
    public ResponseEntity<PaymentResponseDto> editPayment(@PathVariable Long paymentId,
                                                          @RequestBody PaymentUpdateDto updateDto){
        PaymentResponseDto paymentResponseDto = paymentService.updatePayment(paymentId, updateDto);
        return ResponseEntity.ok(paymentResponseDto);
    }
    // product 削除- POSTMEN TEST 完了
    @DeleteMapping("{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId){
        paymentService.deletePayment(paymentId);
       return ResponseEntity.noContent().build();
    }

}
