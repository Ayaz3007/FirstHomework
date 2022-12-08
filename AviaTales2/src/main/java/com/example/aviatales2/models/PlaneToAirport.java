package com.example.aviatales2.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaneToAirport {
    private Long id;
    private Long airportId;
    private Long planeId;
}
