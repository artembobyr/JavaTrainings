package HTTP;

public class Ticket {
    private String category;
    private String date;
    private int id;
    private int place;
    private String title;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getPlace() {
        return place;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "category='" + category + '\'' +
                ", date='" + date + '\'' +
                ", id=" + id +
                ", place=" + place +
                ", title='" + title + '\'' +
                '}';
    }
}
