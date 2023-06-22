package com.ubeydekara.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPayload {
    private UUID orderId;
    private LocalDateTime createDate;
    private BigDecimal totalPrice;
    private UUID customerId;
}
