package com.ubeydekara.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

// DTO for Customer
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerResponse {
    private UUID Id;
    private String name;
    private Byte age;
    private List<UUID> orderIds;
}
