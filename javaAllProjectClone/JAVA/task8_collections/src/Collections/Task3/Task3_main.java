package Collections.Task3;

class Task3_main {

    private static final String path = "\\JAVA\\task8 collections\\src\\Collections\\Task3\\planes.dat";
    public static void main(String[] args) {

        PlaneAdapter planeAdapter = new PlaneAdapter();
        planeAdapter.writeObjectFromFile(System.getProperty("user.dir") + path);
        System.out.println();
        planeAdapter.readObjectFromFile(System.getProperty("user.dir") + path);
    }
}


