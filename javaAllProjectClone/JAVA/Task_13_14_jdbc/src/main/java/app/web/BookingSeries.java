package app.web;

import app.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import preprod.qa.soap.*;

@Endpoint
public class BookingSeries
{
    private static final String NAMESPACE_URI = "http://preprod/qa/soap";

    private final BookingService bookingShowService;

    @Autowired
    public BookingSeries(BookingService bookingShowService)
    {
        this.bookingShowService = bookingShowService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addBookingRequest")
    @ResponsePayload
    public AddBookingResponse addBooking(@RequestPayload AddBookingRequest request)
    {
        AddBookingResponse response = new AddBookingResponse();
        response.setBooking(bookingShowService.addBooking(request.getDoctorName(),request.getTimeSlot(), request.getBookingStatus()));
        return response;
    }

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateBookingRequest")
//    @ResponsePayload
//    public UpdateBookingResponse updateBooking(@RequestPayload UpdateBookingRequest request) {
//        UpdateBookingResponse response = new UpdateBookingResponse();
//        response.setBooking(bookingShowService.update(request.getDoctorName(), request.getTimeSlot()));
//        return response;
//    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookingByDoctorNameRequest")
    @ResponsePayload
    public GetBookingByDoctorNameResponse getByDoctorName (@RequestPayload GetBookingByDoctorNameRequest request)
    {
        GetBookingByDoctorNameResponse response = new GetBookingByDoctorNameResponse();
        response.setBooking(bookingShowService.getBookingByDoctorName(request.getDoctorName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookingByTimeSetRequest")
    @ResponsePayload
    public GetBookingByTimeSetResponse getBookingByTimeSet(@RequestPayload GetBookingByTimeSetRequest request)
    {
        GetBookingByTimeSetResponse response = new GetBookingByTimeSetResponse();
        response.getBooking().addAll(bookingShowService.getBookingByTimeSet(request.getTimSet()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeBookingRequest")
    @ResponsePayload
    public RemoveBookingResponse removeBooking(@RequestPayload RemoveBookingRequest request)
    {
        RemoveBookingResponse response = new RemoveBookingResponse();
        response.getBooking().addAll(bookingShowService.delete(request.getDoctorName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookingByDoctorNameAndTimeSlotRequest")
    @ResponsePayload
    public GetBookingByDoctorNameAndTimeSlotResponse getBookingByDoctorNameAndTimeSlot(@RequestPayload GetBookingByDoctorNameAndTimeSlotRequest request)
    {
       GetBookingByDoctorNameAndTimeSlotResponse response = new GetBookingByDoctorNameAndTimeSlotResponse();
       response.setBooking(bookingShowService.getBookingByDoctorNameAndTimeSlot(request.getDoctorName(),request.getTimSet()));
        return response;
    }
}
