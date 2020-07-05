package app.service;

import app.dao.BookingDAO;
import org.springframework.stereotype.Component;
import preprod.qa.soap.Booking;
import preprod.qa.soap.Status;

import java.util.List;

@Component
public class BookingService
{
    private final BookingDAO bookingDAO = new BookingDAO();

    public Booking addBooking(String name, int timeSlot, String status) {
        if (name == null)
        {
            throw new IllegalArgumentException("Booking name is null.");
        }
        if (timeSlot == 0)
        {
            throw new IllegalArgumentException("Time slot is null.");
        }
        return bookingDAO.addBooking(name,timeSlot, status);
    }

    public Booking getBookingByDoctorName(String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("Name is null.");
        }
        return bookingDAO.get(name);
    }
    public List<Booking> getBookingByTimeSet(int timSlot )
    {
        if (timSlot == 0)
        {
            throw new IllegalArgumentException("Time slot is null.");
        }
        return bookingDAO.getBookingByTimeSlot(timSlot);
    }
    public Booking getBookingByDoctorNameAndTimeSlot(String name, int timeSlot){
        if(timeSlot == 0 && name == null){
            throw  new IllegalArgumentException("Wrong input");
        }
        return  bookingDAO.getBookingByDoctorNameAndTimeSlot(name, timeSlot);
    }


    public List<Booking> get()
    {
        return bookingDAO.get();
    }

    public Booking get(String name)
    {
        return bookingDAO.get(name);
    }

    public List<Booking> delete(String name)
    {
        return bookingDAO.remove(name);
    }
}
