package OOP.Planes;

import OOP.Interfaces.IPossibleofTerrorism;

public class Destroyer extends Plane implements IPossibleofTerrorism {
    private final int guns;

    public Destroyer(String name, int distance_of_flight, int carrying_range, int capacity, int guns) {
        super(name, distance_of_flight, carrying_range, capacity);
        this.guns = guns;
    }

    public String typeOfGuns() {
        String result = "";
        if (guns > 2) {
            System.out.println("I'm flattop");
        } else {
            System.out.println("I'm F22");
        }
        return result;
    }

    @Override
    public String toString() {
        return "Destroyer{" +
                "name='" + getName() + '\'' +
                ", distance_of_flight=" + getDistance_of_flight() +
                ", carrying_range=" + getCarrying_range() +
                ", capacity=" + getCapacity() +
                ", guns=" + guns +
                '}';
    }

    @Override
    public void possibleToFly() {
        System.out.print(typeOfGuns() + " and I can fly and ");
    }

    @Override
    public void possibilityOfTerroristAttack() {
        System.out.println("can murder civil population");
    }
}
