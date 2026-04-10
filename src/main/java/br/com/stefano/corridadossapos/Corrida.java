package br.com.stefano.corridadossapos;

import java.util.ArrayList;
import java.util.List;

public class Corrida {

    private final int distanciaTotal;
    private final List<Sapo> sapos;
    private boolean corridaEncerrada;
    private Sapo vencedor;

    public Corrida(int distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
        this.sapos = new ArrayList<>();
        this.corridaEncerrada = false;
        this.vencedor = null;
    }

    public void adicionarSapo(Sapo sapo) {
        sapos.add(sapo);
    }

    public int getDistanciaTotal() {
        return distanciaTotal;
    }

    public synchronized boolean isCorridaEncerrada() {
        return corridaEncerrada;
    }

    public synchronized void registrarVencedor(Sapo sapo) {
        if (!corridaEncerrada) {
            corridaEncerrada = true;
            vencedor = sapo;
            System.out.println("\n🏆 O vencedor foi: " + sapo.getName() + "!\n");
        }
    }

    public void iniciarCorrida() {
        System.out.println("=== CORRIDA DOS SAPOS ===");
        System.out.println("Distância total: " + distanciaTotal + " metros");
        System.out.println("Quantidade de sapos: " + sapos.size());
        System.out.println();

        for (Sapo sapo : sapos) {
            sapo.start();
        }

        for (Sapo sapo : sapos) {
            try {
                sapo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("A thread principal foi interrompida.");
            }
        }

        System.out.println("=== FIM DA CORRIDA ===");
        if (vencedor != null) {
            System.out.println("Vencedor: " + vencedor.getName());
        } else {
            System.out.println("Nenhum vencedor foi definido.");
        }
    }
}