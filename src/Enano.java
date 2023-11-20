import java.util.concurrent.Semaphore;

public class Enano extends Thread {
    enum Estado {
        TRABAJANDO, SENTADO, COMIENDO, DURMIENDO
    }

    private Estado estado;
    private int id;
    private String nombre;

    private Semaphore puedeComer = new Semaphore(0);

    public Enano(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.estado = Estado.TRABAJANDO;
    }

    @Override
    public void run() {
        try {
            trabajar();
            Casa.sentarseEnLaMesa(id);
            comer();
            Casa.levantarseDeLaMesa(id);
            trabajar();
            Casa.sentarseEnLaMesa(id);
            comer();
            Casa.levantarseDeLaMesa(id);
            trabajar();
            descansar();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void trabajar() throws InterruptedException {
        estado = Estado.TRABAJANDO;
        System.out.println(enanoIdToString() + "El enano " + nombre + " está trabajando" + Casa.getTime());
        sleepRandomTime(500, 1500);
    }

    public void comer() throws InterruptedException {
        puedeComer.acquire();
        estado = Estado.COMIENDO;
        System.out.println("                " + enanoIdToString() + "El enano " + nombre + " está comiendo" + Casa.getTime());
        sleepRandomTime(600, 800);
    }

    public void descansar() throws InterruptedException {
        estado = Estado.DURMIENDO;
        System.out.println(enanoIdToString() + "El enano " + nombre + " está descansando" + Casa.getTime());
        sleepRandomTime(800, 1000);
    }

    public void sleepRandomTime(int min, int max) throws InterruptedException {
        Thread.sleep((long) (Math.random() * (max - min)) + min);
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public Semaphore getPuedeComer() {
        return puedeComer;
    }

    public String getNombre() {
        return nombre;
    }

    public String enanoIdToString() {
        return "(" + id + ") ";
    }
}
