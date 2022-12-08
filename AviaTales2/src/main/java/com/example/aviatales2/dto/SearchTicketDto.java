package com.example.aviatales2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchTicketDto {
    private String fromCityName;
    private String toCityName;
    private Date fromDate;
    private Date toDate;
}
