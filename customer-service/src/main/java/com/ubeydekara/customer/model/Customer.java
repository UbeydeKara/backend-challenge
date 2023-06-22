package com.ubeydekara.customer.model;

import com.ubeydekara.payload.OrderPayload;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity(name = "customers")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private UUID Id;

    @NotBlank(message = "'Name' field is mandatory.")
    private String name;

    @NotNull(message = "'Age' field is mandatory.")
    private Byte age;

    @Transient
    private List<OrderPayload> orders;
}
