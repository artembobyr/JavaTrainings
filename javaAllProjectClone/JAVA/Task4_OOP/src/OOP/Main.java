package OOP;

class Main {

    public static void main(String[] args) {
        Aviacompany aviacompany = new Aviacompany();
        aviacompany.setPlanes();

        System.out.println(aviacompany.allCarryingRange());
        System.out.println(aviacompany.allCapacity());
        aviacompany.sortForDistance();
        for (Object o : aviacompany.getPlanes()) {
            System.out.println(o);
        }
        aviacompany.findPlane();

    }
}


