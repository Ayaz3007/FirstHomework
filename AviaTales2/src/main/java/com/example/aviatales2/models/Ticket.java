package com.example.aviatales2.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private Long id;
    private Integer price;
    private String airline;
    private Date fromDate;
    private Date toDate;
    private Time fromDepartureTime;
    private Time fromLandingTime;
    private Time toDepartureTime;
    private Time toLandingTime;
    private Integer seatPlace;
    private String airportName;
    private String fromCityName;
    private String toCityName;
    private Boolean baggage;
}
