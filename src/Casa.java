import java.sql.SQLOutput;
import java.util.concurrent.Semaphore;

public class Casa {
    public static final int SILLAS = 4;
    public static Semaphore mesa = new Semaphore(SILLAS);
    private static Enano[] enanos;

    public Casa(Enano[] enanos) {
        this.enanos = enanos;
    }

    public static void sentarseEnLaMesa(int enanoId) throws InterruptedException {
        mesa.acquire();
        enanos[enanoId].setEstado(Enano.Estado.SENTADO);
        System.out.println("El enano " + enanos[enanoId].getNombre() + " se ha sentado a la mesa. Hay " + mesa.availablePermits() + " sillas disponibles");
    }

    public static void levantarseDeLaMesa(int enanoId) throws InterruptedException {
        mesa.release();
        System.out.println("El enano " + enanos[enanoId].getNombre() + " se ha levantado de la mesa. Hay " + mesa.availablePermits() + " sillas disponibles");
    }

    public static Enano[] getEnanos() {
        return enanos;
    }
}
