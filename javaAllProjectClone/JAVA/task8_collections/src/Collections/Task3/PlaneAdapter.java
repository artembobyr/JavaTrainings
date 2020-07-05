package Collections.Task3;

import Collections.Task3.Planes.Plane;

import java.io.*;
import java.util.ArrayList;

public class PlaneAdapter implements Serializable {
    private final Aviacompany aviacompany = new Aviacompany();

    void writeObjectFromFile(String filePath) {
        aviacompany.setPlanes();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Plane plane : aviacompany.getPlanes()) {
                oos.writeObject(plane);
            }
            System.out.println("Serialized data");
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public void readObjectFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            ArrayList<Plane> planes = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                planes.add((Plane) ois.readObject());
                System.out.println("Plane: " + planes.get(i));
            }
            System.out.println("Deserialized data");
        } catch (IOException | ClassNotFoundException ex) {
            ex.getMessage();
        }
    }
}
