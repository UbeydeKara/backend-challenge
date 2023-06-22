package com.ubeydekara.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubeydekara.payload.CustomerPayload;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "orders")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    UUID Id;

    LocalDateTime createDate;

    @NotNull(message = "'Total Price' field is mandatory.")
    BigDecimal totalPrice;

    @NotNull(message = "'CustomerId' field is mandatory.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    UUID customerId;

    @Transient
    CustomerPayload customer;
}
