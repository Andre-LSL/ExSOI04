package view;

import java.util.concurrent.Semaphore;
import controller.CozinharThread;

public class ClasseMain {
    public static void main (String [] args) {
        Semaphore espaços = new Semaphore(1);
        for (int contador = 0; contador < 5; contador ++) {
            CozinharThread t = new CozinharThread(espaços, contador);
            t.start();
        }
    }
}
