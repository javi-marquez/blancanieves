public class Main {
    public static void main(String[] args) {
        //Enano e0 = new Enano(0, "Sabio");
        //Enano e1 = new Enano(1, "Gruñón");
        //Enano e2 = new Enano(2, "Feliz");
        //Enano e3 = new Enano(3, "Dormilón");
        //Enano e4 = new Enano(4, "Tímido");
        //Enano e5 = new Enano(5, "Mocoso");
        //Enano e6 = new Enano(6, "Mudito");
        //Blancanieves blancanieves = new Blancanieves();
        //Casa casa = new Casa(new Enano[]{e0, e1, e2, e3, e4, e5, e6});
//
//
        //e0.start();
        //e1.start();
        //e2.start();
        //e3.start();
        //e4.start();
        //e5.start();
        //e6.start();
        //blancanieves.start();

        boolean maxReached = false, minReached = false;
        int min = 75, max = 100;
        while (true) {
            int i = (int) ((Math.random() * max - min) + min);
            if (i == min) System.out.println(minReached = true);
            if (i == max) maxReached = true;
            System.out.println(i);
            if (minReached && maxReached) break;
        }
    }
}