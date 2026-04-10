package br.com.stefano.corridadossapos;

import java.util.Random;

public class Sapo extends Thread {

    private final Corrida corrida;
    private volatile int distanciaPercorrida;
    private final Random random;

    public Sapo(String nome, Corrida corrida) {
        super(nome);
        this.corrida = corrida;
        this.distanciaPercorrida = 0;
        this.random = new Random();
    }

    public int getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    @Override
    public void run() {
        while (!corrida.isCorridaEncerrada()) {
            int salto = random.nextInt(5) + 1;
            distanciaPercorrida += salto;

            corrida.notificarAtualizacao();

            if (distanciaPercorrida >= corrida.getDistanciaTotal()) {
                corrida.registrarVencedor(this);
                corrida.notificarAtualizacao();
                break;
            }

            try {
                int tempoEspera = random.nextInt(401) + 100;
                Thread.sleep(tempoEspera);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}