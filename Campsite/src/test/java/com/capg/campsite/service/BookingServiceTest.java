package com.capg.campsite.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.capg.campsite.entity.Booking;
import com.capg.campsite.entity.Campsite;
import com.capg.campsite.exception.BookingNotFoundException;

import com.capg.campsite.repository.BookingRepository;


@ExtendWith(MockitoExtension.class)
 class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
     void testFindAvailableBookings() throws BookingNotFoundException{
        
        LocalDate arrivalDate = LocalDate.of(2023, 3, 6);
        LocalDate departureDate = LocalDate.of(2023, 3, 8);
        Booking booking1 = new Booking(1L, LocalDate.of(2023, 3, 4), LocalDate.of(2023, 3, 7), true, LocalDate.now(),
                null);
        Booking booking2 = new Booking(2L, LocalDate.of(2023, 3, 5), LocalDate.of(2023, 3, 9), true, LocalDate.now(),
                null);
        Booking booking3 = new Booking(3L, LocalDate.of(2023, 3, 7), LocalDate.of(2023, 3, 10), true, LocalDate.now(),
                null);
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        when(bookingRepository.findAll()).thenReturn(bookings);

        
        List<Booking> availableBookings = bookingService.findAvailableBookings(arrivalDate, departureDate);

        
    }


@Test
 void testDeleteBooking() throws BookingNotFoundException {
   
    long bookingId = 1L;
    Booking booking = new Booking(bookingId, LocalDate.of(2023, 3, 6), LocalDate.of(2023, 3, 8), true,
            LocalDate.now(), null);
    when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
    doNothing().when(bookingRepository).delete(any(Booking.class));

    
    bookingService.deleteBooking(bookingId);

    
}
@Test
 void testGetAllBookings() throws BookingNotFoundException {
    
    List<Booking> bookings = Arrays.asList(new Booking(), new Booking(), new Booking());
    when(bookingRepository.findAll()).thenReturn(bookings);

    
    List<Booking> result = bookingService.getAllBookings();

    
    assertThat(result).isEqualTo(bookings);
}
@Test
 void testGetBookingById() throws BookingNotFoundException {
    
    long bookingId = 123L;
    Booking booking = new Booking();
    when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

    
    Booking result = bookingService.getBookingById(bookingId);

    
    assertThat(result).isEqualTo(booking);
}
@Test
void testAddBooking() throws Exception {
    
    int campsiteId = 1;
    Campsite campsite = new Campsite();
    campsite.setSiteId(campsiteId);

    
}
@Test
void testUpdateBooking() throws BookingNotFoundException {
    
    long bookingId = 1L;



    Booking booking = new Booking();
    booking.setBookingId(bookingId);
    booking.setArrivalDate(LocalDate.now());
    booking.setDepartureDate(LocalDate.now().plusDays(1));
    booking.setAvailability(false);
    booking.setBookingDate(LocalDate.now().minusDays(1));
    booking.setCampsite(new Campsite());

   }
}