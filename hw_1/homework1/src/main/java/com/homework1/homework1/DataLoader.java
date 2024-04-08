package com.homework1.homework1;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.springframework.cache.CacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.homework1.homework1.models.Ticket;
import com.homework1.homework1.models.Trip;
import com.homework1.homework1.repository.TicketRepository;
import com.homework1.homework1.repository.TripRepository;
import com.homework1.homework1.services.CacheService;

@Generated
@Component
public class DataLoader implements CommandLineRunner {
    private final TripRepository tripRepository;
    private final CacheManager cacheManager;
    private final TicketRepository ticketRepository;

    public DataLoader(TripRepository tripRepository, CacheManager cacheManager, TicketRepository ticketRepository) {
        this.tripRepository = tripRepository;
        this.ticketRepository = ticketRepository;
        this.cacheManager = cacheManager;
    }

    @Override
    public void run(String... args) throws Exception {
        Trip trip = new Trip();
        trip.setDeparture("Porto");
        trip.setDestination("London");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2021);
        calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date departureDate = calendar.getTime();
        trip.setDepartureDate(departureDate);
        trip.setTripDuration(3);
        trip.setPrice(140);
        trip.setAvailableSeats(10);

        Trip trip2 = new Trip();
        trip2.setDeparture("Madrid");
        trip2.setDestination("London");
        trip2.setDepartureDate(new Date());
        trip2.setTripDuration(3);
        trip2.setPrice(100);
        trip2.setAvailableSeats(5);

        Trip trip3 = new Trip();
        trip3.setDeparture("Lisbon");
        trip3.setDestination("London");
        trip3.setDepartureDate(new Date());
        trip3.setTripDuration(3);
        trip3.setPrice(120);
        trip3.setAvailableSeats(19);

        Trip trip4 = new Trip();
        trip4.setDeparture("Porto");
        trip4.setDestination("Paris");
        trip4.setDepartureDate(new Date());
        trip4.setTripDuration(3);
        trip4.setPrice(120);
        trip4.setAvailableSeats(10);

        Trip trip5 = new Trip();
        trip5.setDeparture("Madrid");
        trip5.setDestination("Paris");
        trip5.setDepartureDate(new Date());
        trip5.setTripDuration(3);
        trip5.setPrice(70);
        trip5.setAvailableSeats(10);

        Trip trip6 = new Trip();
        trip6.setDeparture("Lisbon");
        trip6.setDestination("Paris");
        trip6.setDepartureDate(new Date());
        trip6.setTripDuration(3);
        trip6.setPrice(100);
        trip6.setAvailableSeats(10);

        Trip trip7 = new Trip();
        trip7.setDeparture("Porto");
        trip7.setDestination("Barcelona");
        trip7.setDepartureDate(new Date());
        trip7.setTripDuration(3);
        trip7.setPrice(60);
        trip7.setAvailableSeats(10);
        
        Trip trip8 = new Trip();
        trip8.setDeparture("Madrid");
        trip8.setDestination("Barcelona");
        trip8.setDepartureDate(new Date());
        trip8.setTripDuration(3);
        trip8.setPrice(20);
        trip8.setAvailableSeats(10);
        
        Trip trip9 = new Trip();
        trip9.setDeparture("Lisbon");
        trip9.setDestination("Barcelona");
        trip9.setDepartureDate(new Date());
        trip9.setTripDuration(3);
        trip9.setPrice(50);
        trip9.setAvailableSeats(10);
        
        tripRepository.save(trip);
        tripRepository.save(trip2);
        tripRepository.save(trip3);
        tripRepository.save(trip4);
        tripRepository.save(trip5);
        tripRepository.save(trip6);
        tripRepository.save(trip7);
        tripRepository.save(trip8);
        tripRepository.save(trip9);

        Ticket t = new Ticket();
        t.setAddress("Avenida 25 de Abril, Alijó");
        t.setFirstName("Gonçalo");
        t.setId(1L);
        t.setLastName("Ferreira");
        t.setId_Number(123456789L);
        t.setZipCode("5070-011");
        t.setInsertToken("T-TEST123");
        
        ticketRepository.save(t);
        refreshCurrencyData();   
    }

    public Map<String, Object> fetchAndCacheCurrencyData() throws IOException {
        // This method will be cached based on the specified cache name "currencies"
        APICaller apiCaller = new APICaller();
        apiCaller.call();
        CacheService cacheService = new CacheService(cacheManager);
        cacheService.clearCache();
        cacheService.putIntoCache("currencies", apiCaller.getCurrencies());
        System.out.println("Cache:" + cacheService.getFromCache("currencies"));
        return apiCaller.getCurrencies();
    }

    public void refreshCurrencyData() throws IOException {
        fetchAndCacheCurrencyData();
    }
}
