package com.ubeydekara.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPayload {
    private UUID customerId;
    private String name;
    private Byte age;
    private List<OrderPayload> orders;
}
