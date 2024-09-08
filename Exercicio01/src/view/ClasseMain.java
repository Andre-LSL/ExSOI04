package view;
import java.util.concurrent.Semaphore;

import controller.MetodosThread;

public class ClasseMain {
    public static void main (String [] args) {
        int espaço = 1;
        Semaphore semaforo = new Semaphore(espaço);

        for (int i = 0; i < 22; i++ ) {
            MetodosThread t = new MetodosThread(semaforo, i);
            t.start();
        }
    }
}
