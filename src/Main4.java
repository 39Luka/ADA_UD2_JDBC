import model.Empleado;
import service.Service;

public class Main4 {

    public static void main(String[] args) {
        Empleado empleado = new Empleado("15165","A", "vzvx@alu.edu.gva.es", "IT");

        Thread t1 = new Thread(() -> {
            Service service = new Service();
            service.onboarding(empleado, "PC-001");
        });

        Thread t2 = new Thread(() -> {
            Service service = new Service();
            service.onboarding(empleado, "PC-001");
        });


        t1.start();
        t2.start();
    }
}
