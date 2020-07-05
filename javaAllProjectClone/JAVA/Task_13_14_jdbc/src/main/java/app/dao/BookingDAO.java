package app.dao;

import org.springframework.stereotype.Component;
import preprod.qa.soap.Booking;
import preprod.qa.soap.Status;

import java.awt.print.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookingDAO {
    private Statement statement;
    private  PreparedStatement preparedStatement = null;

    private static Booking procesQuery(ResultSet resultSet) throws SQLException {
        Booking booking = new Booking();

        booking.setDoctorName(resultSet.getString("doctorName"));
        booking.setTimeSlot(resultSet.getInt("timeSlot"));
        booking.setBookingStatus(resultSet.getString("bookingStatus"));

        return booking;
    }

    public Booking addBooking(String name, int time, String status) {
        statement = new BookingConnector().getStatment();
        Booking booking = new Booking();

        String query = String.format("INSERT INTO booking (doctorName,timeSlot,bookingStatus) VALUES (?,?,?)");
        try {
            preparedStatement = BookingConnector.getConnection().prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,time);
            preparedStatement.setString(3, status);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            BookingConnector.closeConnection();
        } finally {
            BookingConnector.closeConnection();
        }
        return booking;
    }

    public Booking get(String name) {
        statement = new BookingConnector().getStatment();
        Booking booking = new Booking();
        String query = "SELECT doctorName, timeSlot, bookingStatus FROM booking where doctorName =?";
        try {
            preparedStatement = BookingConnector.getConnection().prepareStatement(query);
            preparedStatement.setString(1,name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                booking.setDoctorName(rs.getString("doctorName"));
                booking.setTimeSlot(rs.getInt("timeSlot"));
                booking.setBookingStatus(rs.getString("bookingStatus"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            BookingConnector.closeConnection();
        } finally {
            BookingConnector.closeConnection();
        }
        return booking;
    }

    public List<Booking> getBookingByTimeSlot(int time) {
        statement = new BookingConnector().getStatment();
        Booking booking = new Booking();
        List<Booking> brs = new ArrayList<>();
        try {
            String query = "SELECT * FROM booking where timeSlot =?";
            PreparedStatement preparedStatement = BookingConnector.getConnection().prepareStatement(query);
            int i = 1;
            preparedStatement.setInt(i,time);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                booking.setDoctorName(rs.getString("doctorName"));
                booking.setTimeSlot(rs.getInt("timeSlot"));
                booking.setBookingStatus(rs.getString("bookingStatus"));
                brs.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            BookingConnector.closeConnection();
        }
        finally {
            BookingConnector.closeConnection();
        }
        return brs.isEmpty() ? null : brs;
    }

    public List<Booking> get() {
        statement = new BookingConnector().getStatment();
        List<Booking> brs = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT  * FROM booking");
            while (rs.next()) {
                Booking booking = procesQuery(rs);
                System.out.println(booking.getDoctorName());
                brs.add(booking);
            }
            BookingConnector.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            BookingConnector.closeConnection();
        }

        return brs;
    }

    public Booking getBookingByDoctorNameAndTimeSlot(String name, int timeSlot) {
        statement = new BookingConnector().getStatment();
        Booking booking = new Booking();
        try {
            String query = "SELECT * FROM booking WHERE doctorName =? and timeSlot =?";
            PreparedStatement preparedStatement = BookingConnector.getConnection().prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,timeSlot);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                booking.setDoctorName(rs.getString("doctorName"));
                booking.setTimeSlot(rs.getInt("timeSlot"));
                booking.setBookingStatus(rs.getString("bookingStatus"));
            }
        BookingConnector.closeConnection();
            } catch (SQLException e){
            e.printStackTrace();
            BookingConnector.closeConnection();
        }

        return booking;
    }

    public List<Booking> remove(String name) {
        statement = new BookingConnector().getStatment();
        List<Booking>  brs = new ArrayList<>();
        String query = "DELETE FROM booking WHERE doctorName =?";
        try {
            preparedStatement = BookingConnector.getConnection().prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            BookingConnector.closeConnection();
        }
        finally {
            BookingConnector.closeConnection();
        }
        return brs;
    }



}
