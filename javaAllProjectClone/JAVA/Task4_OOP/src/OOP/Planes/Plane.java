package OOP.Planes;

public abstract class Plane implements Comparable{
    private final String name;
    private final int distance_of_flight;
    private final int carrying_range;
    private final int capacity;

    protected Plane(String name, int distance_of_flight, int carrying_range, int capacity) {
        this.name = name;
        this.distance_of_flight = distance_of_flight;
        this.carrying_range = carrying_range;
        this.capacity = capacity;
    }

    public abstract void possibleToFly();

    public String toString() {
        return "Plane{" +
                "name='" + name + '\'' +
                ", distance_of_flight=" + distance_of_flight +
                ", carrying_range=" + carrying_range +
                ", capacity=" + capacity +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getDistance_of_flight() {
        return distance_of_flight;
    }

    public int getCarrying_range() {
        return carrying_range;
    }

    public int getCapacity() {
        return capacity;
    }

    public int compareTo(Object obj)
    {
        Plane tmp = (Plane) obj;
        if(this.distance_of_flight < tmp.distance_of_flight)
        {
            return 1;
        }
        else if(this.distance_of_flight > tmp.distance_of_flight)
        {
            return -1;
        }
        return 0;
    }
}
