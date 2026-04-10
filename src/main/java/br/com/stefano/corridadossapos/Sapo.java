package br.com.stefano.corridadossapos;

import java.util.Random;

public class Sapo extends Thread {

    private final Corrida corrida;
    private int distanciaPercorrida;
    private final Random random;

    public Sapo(String nome, Corrida corrida) {
        super(nome);
        this.corrida = corrida;
        this.distanciaPercorrida = 0;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (!corrida.isCorridaEncerrada()) {
            int salto = random.nextInt(5) + 1;
            distanciaPercorrida += salto;

            int distanciaExibida = Math.min(distanciaPercorrida, corrida.getDistanciaTotal());

            System.out.println(getName() + " avançou " + salto + "m e está em " + distanciaExibida + "m");

            if (distanciaPercorrida >= corrida.getDistanciaTotal()) {
                corrida.registrarVencedor(this);
                break;
            }

            try {
                int tempoEspera = random.nextInt(401) + 100; // 100 a 500 ms
                Thread.sleep(tempoEspera);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(getName() + " foi interrompido.");
                break;
            }
        }
    }
}