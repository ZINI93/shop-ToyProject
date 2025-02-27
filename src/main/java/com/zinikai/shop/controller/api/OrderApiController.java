package com.zinikai.shop.controller.api;

import com.zinikai.shop.domain.order.dto.OrdersRequestDto;
import com.zinikai.shop.domain.order.dto.OrdersResponseDto;
import com.zinikai.shop.domain.order.dto.OrdersUpdateDto;
import com.zinikai.shop.domain.order.entity.Status;
import com.zinikai.shop.domain.order.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderApiController {

    private final OrdersService orderService;

    //オーダーする　ー　POSTMEN TEST 完了
    @PostMapping
    public ResponseEntity<OrdersResponseDto> createOrder(@RequestBody OrdersRequestDto requestDto){
        OrdersResponseDto order = orderService.createOrder(requestDto);
        URI location = URI.create("/api/orders" + order.getId());
        return ResponseEntity.created(location).body(order);

    }
    // オーダーをIDで照会　ー　POSTMEN TEST 完了
    @GetMapping("{orderId}")
    public ResponseEntity<OrdersResponseDto> getOrderId(@PathVariable Long orderId){
        OrdersResponseDto orderById = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderById);
    }

    // ー　POSTMEN TEST 完了
    @GetMapping
    public ResponseEntity<Page<OrdersResponseDto>> searchOrder(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) LocalDateTime starDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) BigDecimal minAmount,
            @RequestParam(required = false) BigDecimal maxAmount,
            @RequestParam(defaultValue = "createdAt") String sortField,
            Pageable pageable
            ){
        Page<OrdersResponseDto> orders = orderService.searchOrder(status, starDate, endDate, minAmount, maxAmount, sortField, pageable);

        return ResponseEntity.ok(orders);

    }

    //オーダーをアップデート　ー　POSTMEN TEST 完了
    @PutMapping("{orderId}")
    public ResponseEntity<OrdersResponseDto> editOrder(@PathVariable Long orderId, @RequestBody OrdersUpdateDto updateDto){
        OrdersResponseDto orderUpdate = orderService.updateOrder(orderId, updateDto);
        return ResponseEntity.ok(orderUpdate);
    }

    //オーダーを削除　ー　POSTMEN TEST 完了
    @DeleteMapping("{orderId}")
    public ResponseEntity<OrdersResponseDto> deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }



}
