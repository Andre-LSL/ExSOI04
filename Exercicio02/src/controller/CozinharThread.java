package controller;

import java.util.concurrent.Semaphore;

public class CozinharThread extends Thread {
    private int IDThread;
    private Semaphore SemaforoId;
    public CozinharThread(Semaphore SemaforoId,int IDThread) {
        this.SemaforoId = SemaforoId;
        this.IDThread = IDThread;
    }

    public void run() {
        String nomePrato;
        if (IDThread % 2 == 0 ) {
            nomePrato = "Lasanha";
            pratoFazer(nomePrato, 1200, 600, IDThread);
            entregar(SemaforoId, IDThread, nomePrato);
        } else {
            nomePrato = "Sopa de Cebola";
            pratoFazer(nomePrato, 800, 500, IDThread);
            entregar(SemaforoId, IDThread, nomePrato);
        }

    }

    private void entregar(Semaphore semaforo, int ID, String Nome) {
        try {
            System.err.println("Preparando para entrega. " + ID);
            semaforo.acquire();
            sleep(5000);
            System.err.println("Prato Entregue! " + Nome +
                               "\nIDthread: " + ID);


        } catch (Exception e) {
            System.err.println(e);
        }
        semaforo.release();
    }

    private void pratoFazer(String Nome, int valorMax, int valorMin, int ID) {
        int tempoCozimento = (int) ((Math.random() * valorMax) + valorMin);
        int tempoFazendo = 0;
        double porcentagem = 0;

        System.err.println("Iniciou a cozinhar o Prato: " + Nome);
            while ( tempoFazendo < tempoCozimento) {
                try {
                    sleep(tempoCozimento);
                    tempoFazendo += 100;
                    porcentagem = ( (tempoFazendo / (float) tempoCozimento) * 100);
                    String porcentagemFormatada = String.format("%.1f", porcentagem);
                    System.err.println("Porcentagem de preparo: " + porcentagemFormatada+ "%" + 
                                       "\nPrato: " + Nome + "\nIDThread: " + ID);
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

    }



}
