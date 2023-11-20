import java.util.concurrent.Semaphore;

public class Blancanieves extends Thread {
    //Semaphore sirviendoComida = new Semaphore(1);
    boolean puedeDormir = false;

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
                    }
                }

                if (puedeDormir) {
                    dormir();
                } else {
                    pasear();
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void dormir() throws InterruptedException {
        System.out.println("Blancanieves está durmiendo" + Casa.getTime());
        Thread.sleep(500);
    }

    public void prepararComida(Enano enano) throws InterruptedException {
        System.out.println("        " + enano.enanoIdToString() + "Blancanieves está preparando la comida para el enano " + enano.getNombre() + Casa.getTime());
        Thread.sleep(500);
    }

    public void servirComida(Enano enano) {
        System.out.println("        " + enano.enanoIdToString() + "Blancanieves le ha servido la comida al enano " + enano.getNombre() + Casa.getTime());
        enano.getPuedeComer().release();
    }

    public void pasear() throws InterruptedException {
        System.out.println("Blancanieves está paseando..." + Casa.getTime());
        Thread.sleep(500);
    }


}
