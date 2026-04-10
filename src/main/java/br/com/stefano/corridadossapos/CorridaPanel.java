package br.com.stefano.corridadossapos;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

public class CorridaPanel extends JPanel {

    private final Corrida corrida;

    public CorridaPanel(Corrida corrida) {
        this.corrida = corrida;
        setBackground(new Color(245, 250, 245));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        List<Sapo> sapos = corrida.getSapos();

        int largura = getWidth();
        int altura = getHeight();

        int margemEsquerda = 140;
        int margemDireita = 80;
        int topo = 40;

        int quantidadeSapos = sapos.size();
        if (quantidadeSapos == 0) {
            return;
        }

        int alturaPista = Math.max(60, (altura - topo - 20) / quantidadeSapos);
        int linhaChegadaX = largura - margemDireita;

        g2.setColor(new Color(180, 180, 180));
        g2.drawLine(linhaChegadaX, 20, linhaChegadaX, altura - 20);

        g2.setFont(new Font("SansSerif", Font.BOLD, 14));
        g2.drawString("Chegada", linhaChegadaX - 25, 15);

        for (int i = 0; i < sapos.size(); i++) {
            Sapo sapo = sapos.get(i);

            int y = topo + (i * alturaPista);
            int centroY = y + (alturaPista / 2);

            g2.setColor(new Color(210, 210, 210));
            g2.drawLine(margemEsquerda, centroY + 10, linhaChegadaX, centroY + 10);

            g2.setColor(Color.BLACK);
            g2.setFont(new Font("SansSerif", Font.BOLD, 16));
            g2.drawString(sapo.getName(), 20, centroY);

            g2.setFont(new Font("SansSerif", Font.PLAIN, 13));
            g2.drawString("Distância: "
                    + Math.min(sapo.getDistanciaPercorrida(), corrida.getDistanciaTotal())
                    + "m", 20, centroY + 20);

            double progresso = (double) Math.min(sapo.getDistanciaPercorrida(), corrida.getDistanciaTotal())
                    / corrida.getDistanciaTotal();

            int faixaUtil = linhaChegadaX - margemEsquerda - 30;
            int xSapo = margemEsquerda + (int) (progresso * faixaUtil);

            g2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
            g2.drawString("🐸", xSapo, centroY + 20);
        }
    }
}