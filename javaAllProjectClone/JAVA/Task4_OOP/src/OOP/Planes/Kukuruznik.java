package OOP.Planes;

import OOP.Interfaces.ITransportable;

public class Kukuruznik extends Plane implements ITransportable {
    private final boolean fueling;

    public Kukuruznik(String name, int distance_of_flight, int carrying_range, int capacity, boolean fueling) {
        super(name, distance_of_flight, carrying_range, capacity);
        this.fueling = fueling;
    }

    public String possibleToFertilize() {
        String result = "";
        if (fueling) {
            result += "I can fertilize";
        } else {
            result += "I can't fertilize";
        }
        return result;
    }

    @Override
    public String toString() {
        return "Kukuruznik{" +
                "name='" + getName() + '\'' +
                ", distance_of_flight=" + getDistance_of_flight() +
                ", carrying_range=" + getCarrying_range() +
                ", capacity=" + getCapacity() +
                ", fueling=" + fueling +
                '}';
    }

    @Override
    public void possibleToFly() {
        System.out.print("I can fly and " + possibleToFertilize());
    }

    @Override
    public void possibilityOfTransportPeople() {
        System.out.println(" and transport people");
    }
}
