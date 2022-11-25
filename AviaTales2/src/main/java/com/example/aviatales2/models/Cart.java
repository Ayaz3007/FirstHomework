package com.example.aviatales2.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Long id;
    private Long usernameId;
    private Long ticketId;
}