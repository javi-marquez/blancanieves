import java.util.concurrent.Semaphore;

public class Blancanieves extends Thread {
    Semaphore sirviendoComida = new Semaphore(1);
    boolean puedeDormir = false;

    enum Estado {
        PASEANDO, SIRVIENDO_COMIDA, DURMIENDO
    }


    public Blancanieves() {

    }


    @Override
    public void run() {


        try {
            while (!puedeDormir) {
                puedeDormir = true;
                for (Enano enano : Casa.getEnanos()) {
                    if (enano.getEstado() != Enano.Estado.DURMIENDO) puedeDormir = false;

                    if (enano.getEstado() == Enano.Estado.SENTADO) {
                        prepararComida(enano);
                        servirComida(enano);
                    } else {
                        pasear();
                    }
                }

                if (puedeDormir) {
                    dormir();
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void dormir() throws InterruptedException {
        System.out.println("Blancanieves está durmiendo...");
        Thread.sleep(500);
    }

    public void prepararComida(Enano enano) throws InterruptedException {
        System.out.println("Blancanieves está preparando la comida para el enano " + enano.getNombre() + "...");
        Thread.sleep(500);
    }

    public void servirComida(Enano enano) {
        System.out.println("Blancanieves le ha servido la comida al enano " + enano.getNombre());
        enano.getPuedeComer().release();
    }

    public void pasear() throws InterruptedException {
        System.out.println("Blancanieves está paseando...");
        Thread.sleep(500);
    }


}
