package com.example.aviatales2.dto;

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
public class TicketDto {
    private Long id;
    private Integer price;
    private Date fromDate;
    private Date toDate;
    private Time fromFlightTime;
    private Time toFlightTime;
    private Integer seatPlace;
    private String fromCityName;
    private String toCityName;
    private String fromAirportName;
    private String toAirportName;
    private String fromPlaneName;
    private String toPlaneName;
}
