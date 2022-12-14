package com.toy.shop.controller;

import com.toy.shop.common.ResultDto;
import com.toy.shop.domain.DeliveryStatus;
import com.toy.shop.domain.OrderStatus;
import com.toy.shop.dto.OrderDto;
import com.toy.shop.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Object saveOrder(@RequestBody @Valid OrderDto.SaveRequest requestDto) {
        OrderDto.Response responseDto = orderService.save(requestDto);

        return new ResultDto<>(responseDto);
    }

    @GetMapping
    public Object orders(@RequestParam Long memberId,
                         @RequestParam(required = false) OrderStatus orderStatus,
                         @RequestParam(required = false) DeliveryStatus deliveryStatus) {
        List<OrderDto.Response> list = orderService.findAll(memberId, orderStatus, deliveryStatus);

        return new ResultDto<>(list);
    }

    @PatchMapping("{id}")
    public Object cancelOrder(@PathVariable Long id) {
        OrderDto.Response responseDto = orderService.update(id);

        return new ResultDto<>(responseDto);
    }
}
