package Collections.Task3;

import Collections.Task3.Planes.Boing;
import Collections.Task3.Planes.Destroyer;
import Collections.Task3.Planes.Kukuruznik;
import Collections.Task3.Planes.Plane;

import java.io.Serializable;
import java.util.Arrays;

public class Aviacompany implements Serializable {

    private final Plane[] planes = new Plane[3];

    public Plane[] getPlanes() {
        return planes;
    }

    public void setPlanes() {
        planes[0] = new Boing("Transporter", 2000, 5000, 500, 5);
        planes[1] = new Destroyer("Hunter", 400, 100, 2, 5);
        planes[2] = new Kukuruznik("Soviet", 50, 200, 4, true);
    }
}