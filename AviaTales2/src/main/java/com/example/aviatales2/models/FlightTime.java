package com.example.aviatales2.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightTime {
    private Long id;
    private Long airportId;
    private Time flightTime;
}
