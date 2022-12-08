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
    private Date fromDate;
    private Date toDate;
    private Integer seatPlace;
    private Long fromAirportId;
    private Long toAirportId;
    private Time fromTime;
    private Time toTime;
    private Long fromPlane;
    private Long toPlane;
    private String fromCity;
    private String toCity;
}
