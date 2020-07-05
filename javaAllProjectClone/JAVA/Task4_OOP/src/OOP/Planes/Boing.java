package OOP.Planes;

import OOP.Interfaces.IPossibleofTerrorism;
import OOP.Interfaces.ITransportable;

public class Boing extends Plane implements ITransportable, IPossibleofTerrorism {
    private final int turbins;

    public Boing(String name, int distance_of_flight, int carrying_range, int capacity, int turbins) {
        super(name, distance_of_flight, carrying_range, capacity);
        this.turbins = turbins;
    }

    public String typeOfBoing() {
        String result = "";
        if (turbins > 4) {
            System.out.println("I'm ТУ-32");
        } else {
            System.out.println("I'm ИЛ-96");
        }
        return result;
    }

    @Override
    public String toString() {
        return "Boing{" +
                "name='" + getName() + '\'' +
                ", distance_of_flight=" + getDistance_of_flight() +
                ", carrying_range=" + getCarrying_range() +
                ", capacity=" + getCapacity() +
                ", turbins=" + turbins +
                '}';
    }

    @Override
    public void possibleToFly() {
        System.out.println(typeOfBoing() + " and I can fly and ");
    }

    @Override
    public void possibilityOfTerroristAttack() {
        System.out.println("can murder civil population");
    }

    @Override
    public void possibilityOfTransportPeople() {
        System.out.println(" and transport people");
    }
}
