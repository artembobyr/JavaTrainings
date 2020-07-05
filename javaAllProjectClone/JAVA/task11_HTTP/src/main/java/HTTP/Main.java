package HTTP;

public class Main {
    public static void main(String[] args) throws Exception {
        new CreateXML().readURL("http://10.23.149.167:9999/BookingTicket/getAvailableTicket.xml");
        new ParseXML().parseToList("tickets.xml");
        new Post().getPost("http://10.23.149.167:9999/BookingTicket/bookTicket.json?userId=1");
        new Excel().readFromJsonToList("tickets.json");
    }
}