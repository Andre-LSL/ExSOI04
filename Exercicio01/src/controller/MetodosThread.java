package controller;

import java.util.concurrent.Semaphore;

public class MetodosThread extends Thread {

    private Semaphore semaforo;
    private int ID;
    public MetodosThread(Semaphore semaforo, int ID) {
        this.semaforo = semaforo;
        this.ID = ID;
    }

    public void run() {
        if (ID % 3 == 1) {
            calculo(1000, 200, ID);
            transacao(1000, ID);
            calculo(1000, 200, ID);
            transacao(1000, ID);            
        } else if ( ID % 3 == 2 ) {
            calculo(1500, 500, ID);
            transacao(1500, ID);
            calculo(1500, 500, ID);
            transacao(1500, ID);
        } else {
            calculo(2000, 1000, ID);
            transacao(1500, ID);
            calculo(2000, 1000, ID);
            transacao(1500, ID);
        }

    }

    private void transacao(int valorTempo, int IDThread) {


        int tempo = valorTempo; 
        try {
            semaforo.acquire();
            sleep(tempo);
            System.out.println("A thread " + IDThread + " está realizando transações " + tempo +"ms" );
        } catch (Exception e) {
            System.out.println(e);
        }
        semaforo.release();
    }

    private void calculo(int valorMax, int valorMin, int IDThread) {
        int tempo = (int) ((Math.random() * valorMax) +  valorMin); 
        System.out.println("A thread " + IDThread + " está realizando cálculos por " + tempo +"ms" );
        try {
            sleep(tempo);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
