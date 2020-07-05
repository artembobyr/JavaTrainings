package OOP;

import OOP.Planes.Boing;
import OOP.Planes.Destroyer;
import OOP.Planes.Kukuruznik;
import OOP.Planes.Plane;

import java.util.Arrays;
import java.util.Scanner;

public class Aviacompany {

    private final Plane[] planes = new Plane[3];

    public Plane[] getPlanes() {
        return planes;
    }

    public void setPlanes() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input quantity of turbines");
        planes[0] = new Boing("Transporter", 2000, 5000, 500, 2);
        System.out.println("Input quantity of guns");
        planes[1] = new Destroyer("Hunter", 400, 100, 2, 3);
        System.out.println("Input fueling (true or false)");
        planes[2] = new Kukuruznik("Soviet", 50, 200, 4, false);
    }

    public int allCarryingRange() {
        int sum = 0;
        for (Plane plane : getPlanes()) {
            sum += plane.getCarrying_range();
        }
        return sum;
    }

    public int allCapacity() {
        int sum = 0;
        for (Plane plane : getPlanes()) {
            sum += plane.getCapacity();
        }
        return sum;
    }

    public void sortForDistance() {
        Arrays.sort(planes);
    }

    public void findPlane() {
        for (int i = 0; i < planes.length; i++) {
            if (planes[i].getCapacity() < 5 && planes[i].getCarrying_range() > 150) {
                System.out.println(planes[i]);
            }
        }
    }
}