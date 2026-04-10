package br.com.stefano.corridadossapos;

import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.List;

public class Corrida {

    private final int distanciaTotal;
    private final List<Sapo> sapos;
    private boolean corridaEncerrada;
    private Sapo vencedor;
    private CorridaListener listener;

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

    public List<Sapo> getSapos() {
        return sapos;
    }

    public synchronized boolean isCorridaEncerrada() {
        return corridaEncerrada;
    }

    public void setListener(CorridaListener listener) {
        this.listener = listener;
    }

    public void notificarAtualizacao() {
        if (listener != null) {
            SwingUtilities.invokeLater(listener::aoAtualizarCorrida);
        }
    }

    public synchronized void registrarVencedor(Sapo sapo) {
        if (!corridaEncerrada) {
            corridaEncerrada = true;
            vencedor = sapo;

            if (listener != null) {
                SwingUtilities.invokeLater(() -> listener.aoFinalizarCorrida(sapo));
            }
        }
    }

    public void iniciarCorrida() {
        for (Sapo sapo : sapos) {
            sapo.start();
        }
    }
}