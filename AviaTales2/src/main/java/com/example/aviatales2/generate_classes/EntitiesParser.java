package com.example.aviatales2.generate_classes;

import com.example.aviatales2.connection.PostgresConnectionProvider;
import com.example.aviatales2.models.City;
import com.example.aviatales2.models.Country;
import com.example.aviatales2.repositories.CityRepository;
import com.example.aviatales2.repositories.CountryRepository;
import com.example.aviatales2.repositories.impl.CityRepositoryImpl;
import com.example.aviatales2.repositories.impl.CountryRepositoryImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class EntitiesParser {
    public static void saveCountries() {
        CountryRepository countryRepository = new CountryRepositoryImpl(PostgresConnectionProvider.getConnection());
        try (Scanner sc = new Scanner(new File("C:\\Users\\Den\\Documents\\ОРИС\\AviaTales2\\src\\main\\resources\\countries.txt"))) {
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                countryRepository.save(Country.builder()
                        .name(line.substring(0, line.indexOf('(')-1)).build());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveRussiaCities(File citiesFile) {
        CityRepository cityRepository = new CityRepositoryImpl(PostgresConnectionProvider.getConnection());
        try {
            Set<String> lines = new HashSet<>();
            Scanner sc = new Scanner(citiesFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                int setLength = lines.size();
                lines.add(line);
                if(!line.equals("") && setLength != lines.size()) {
                    cityRepository.save(City.builder().name(line).countryName("Россия").build());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveCities(File citiesFile, String countryName) {
        CityRepository cityRepository = new CityRepositoryImpl(PostgresConnectionProvider.getConnection());
        try {
            Set<String> lines = new HashSet<>();
            Scanner sc = new Scanner(citiesFile, "Windows-1251");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                int setLength = lines.size();
                lines.add(line);
                if(!line.equals("") && setLength != lines.size()) {
                    cityRepository.save(City.builder().name(line).countryName(countryName).build());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        saveRussiaCities(new File("C:\\Users\\Den\\Documents\\ОРИС\\AviaTales2\\src\\main\\resources\\Russia.txt"));
        saveCities(new File("C:\\Users\\Den\\Documents\\ОРИС\\AviaTales2\\src\\main\\resources\\Goroda_Belorussii.txt"),
                "Беларусь");
        saveCities(new File("C:\\Users\\Den\\Documents\\ОРИС\\AviaTales2\\src\\main\\resources\\Spisok_gorodov_Kazakhstana.txt"),
                "Казахстан");
    }
}
