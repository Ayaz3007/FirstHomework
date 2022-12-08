package com.example.aviatales2.generate_classes;

import com.example.aviatales2.connection.PostgresConnectionProvider;
import com.example.aviatales2.models.*;
import com.example.aviatales2.repositories.*;
import com.example.aviatales2.repositories.impl.*;

import java.sql.Connection;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.sql.Date;

public class GenerateEntities {
    private static final Connection connection = PostgresConnectionProvider.getConnection();
    private static final PlaneRepository planeRepository = new PlaneRepositoryImpl(connection);
    private static final AirportRepository airportRepository = new AirportRepositoryImpl(connection);
    private static final PlaneToAirportRepository planeToAirportRepository =
            new PlaneToAirportRepositoryImpl(connection);
    private static final FlightTimesRepository flightTimesRepository = new FlightTimesRepositoryImpl(connection);
    private static final CityRepository cityRepository = new CityRepositoryImpl(connection);
    private static final TicketRepository ticketRepository = new TicketRepositoryImpl(connection);

    private static void savePlanesToAirports() {
        List<Long> planes = planeRepository.findAll().stream().map(Plane::getId).collect(Collectors.toList());
        List<Long> airports = airportRepository.findAll().stream().map(Airport::getId).collect(Collectors.toList());
        for(Long airportId: airports) {
            Set<Long> planeIdSet= new HashSet<>();
            for(int i = 0; i < 4; i++) {
                while(planeIdSet.contains(planes.get(0))) {
                    Collections.shuffle(planes);
                }
                planeIdSet.add(planes.get(0));
                planeToAirportRepository.save(PlaneToAirport.builder().airportId(airportId)
                        .planeId(planes.get(0)).build());
            }
        }
    }
    private static void saveFlightTimes() {
        List<Long> airports = airportRepository.findAll().stream().map(Airport::getId).collect(Collectors.toList());
        for(Long airportId: airports) {
            for(int i = 0; i < 3; i++) {
                flightTimesRepository.save(FlightTime.builder().airportId(airportId).flightTime(getTime()).build());
            }
        }
    }
    private static void saveTickets() {
        List<String> cities = cityRepository.findAll().stream().map(City::getName).collect(Collectors.toList());
        for(String fromCity: cities) {
            for(String toCity: cities) {
                if(fromCity.equals(toCity)) {
                    continue;
                }
                List<Airport> fromAirports = airportRepository.findAllByCityName(fromCity);
                List<Airport> toAirports = airportRepository.findAllByCityName(toCity);
                for(int i = 0; i < 5; i++) {
                    Date[] date = getDates();
                    Collections.shuffle(fromAirports);
                    Collections.shuffle(toAirports);
                    Long fromAirportId = fromAirports.get(0).getId();
                    Long toAirportId = toAirports.get(0).getId();
                    Ticket ticket = Ticket.builder()
                            .price(randBetween(2000, 5000))
                            .fromDate(date[0])
                            .toDate(date[1])
                            .seatPlace(randBetween(1, 300))
                            .fromAirportId(fromAirportId)
                            .toAirportId(toAirportId)
                            .fromTime(getTimeByAirportId(fromAirportId))
                            .toTime(getTimeByAirportId(toAirportId))
                            .fromPlane(getPlaneIdByAirportId(fromAirportId))
                            .toPlane(getPlaneIdByAirportId(toAirportId))
                            .fromCity(fromCity)
                            .toCity(toCity)
                            .build();
                    ticketRepository.save(ticket);
                }

            }
        }
    }


    private static int randBetween(int start, int end) {
        return (int)(Math.random()*((end-start)+1))+start;
    }

    private static Time getTime() {
        int hours = randBetween(1, 23);
        int minutes = randBetween(1, 59)/10*10;

        String stringHours = hours < 10 ? "0" + hours : "" + hours;
        String stringMinutes = minutes < 10 ? "0" + minutes : "" + minutes;

        java.util.Date time;
        try {
            time = new SimpleDateFormat("hh:mm:ss")
                    .parse(stringHours + ":" + stringMinutes + ":00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return new Time(time.getTime());
    }
    private static Date[] getDates() {
        int fromDay = randBetween(1, 30);
        int toDay = randBetween(fromDay + 1, 31);
        String stringFromDay = fromDay < 10 ? "0" + fromDay : "" + fromDay;
        String stringToDay = toDay < 10 ? "0" + toDay : "" + toDay;

        java.sql.Date[] date = new java.sql.Date[2];

        try {
            date[0] = new Date(new SimpleDateFormat("yyyy.MM.dd").parse("2022.12." + stringFromDay).getTime());
            date[1] = new Date(new SimpleDateFormat("yyyy.MM.dd").parse("2022.12." + stringToDay).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
    private static Time getTimeByAirportId(Long id) {
        List<Time> flightTimes = flightTimesRepository.getAllTimesByAirportId(id);
        Collections.shuffle(flightTimes);
        return flightTimes.get(0);
    }
    private static Long getPlaneIdByAirportId(Long id) {
        List<Long> planes = planeToAirportRepository.getAllPlanesByAirportId(id);
        Collections.shuffle(planes);
        return planes.get(0);
    }

    public static void main(String[] args) {
        saveTickets();
    }
}

