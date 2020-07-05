package app.web;

import app.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import preprod.qa.soap.Booking;

import java.util.List;

@RestController
@RequestMapping(value = "/bookings")
public class BookingREST
{
    @Autowired
    private final BookingService bookingService;

    public BookingREST(BookingService tvShowService)
    {
        this.bookingService = tvShowService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Booking> get()
    {
        return bookingService.get();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Booking get(@PathVariable String name)
    {
        return bookingService.get(name);
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Booking addBooking(@RequestBody Booking booking){
        return bookingService.addBooking(booking.getDoctorName(),booking.getTimeSlot(),booking.getBookingStatus());
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    @ResponseBody
    public List<Booking> delete(@PathVariable String name)
    {
        return bookingService.delete(name);
    }

}
