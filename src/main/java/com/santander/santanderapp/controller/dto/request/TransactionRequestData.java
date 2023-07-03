package com.santander.santanderapp.controller.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestData {

    @Email(message = "senderId must be a valid email")
    @NotBlank(message = "sender field cant be blank")
    @Size(min =10,max=100,message ="senderId must be valid")
    private String sender;
    @Email(message = "receiverId must be a valid email")
    @NotBlank(message = "receiver field cant be blank")
    @Size(min =10,max=100,message ="receiverId must be valid")
    private String receiver;
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;
}
