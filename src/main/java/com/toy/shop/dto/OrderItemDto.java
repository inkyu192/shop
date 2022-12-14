package com.toy.shop.dto;

import com.toy.shop.domain.OrderItem;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class OrderItemDto {

    @Getter
    public static class SaveRequest {

        @NotEmpty
        private Long itemId;

        @NotNull
        private int count;
    }

    @Getter
    public static class Response {
        private String itemName;
        private int orderPrice;
        private int count;

        public Response(OrderItem orderItem) {
            this.itemName = orderItem.getItem().getName();
            this.orderPrice = orderItem.getOrderPrice();
            this.count = orderItem.getCount();
        }
    }
}
