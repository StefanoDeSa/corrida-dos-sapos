package br.com.stefano.corridadossapos;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;

public class CorridaFrame extends JFrame implements CorridaListener {

    private Corrida corrida;
    private CorridaPanel corridaPanel;
    private JLabel statusLabel;
    private JButton iniciarButton;

    public CorridaFrame() {
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Corrida dos Sapos");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        statusLabel = new JLabel("Clique em 'Iniciar corrida'");
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        iniciarButton = new JButton("Iniciar corrida");
        iniciarButton.addActionListener(e -> iniciarNovaCorrida());

        JPanel topo = new JPanel(new BorderLayout());
        topo.add(statusLabel, BorderLayout.CENTER);
        topo.add(iniciarButton, BorderLayout.EAST);

        add(topo, BorderLayout.NORTH);
    }

    private void iniciarNovaCorrida() {
        iniciarButton.setEnabled(false);

        corrida = new Corrida(50);
        corrida.setListener(this);

        statusLabel.setText("Corrida em andamento... Distância total: "+corrida.getDistanciaTotal()+"m");


        corrida.adicionarSapo(new Sapo("Sapo 1", corrida));
        corrida.adicionarSapo(new Sapo("Sapo 2", corrida));
        corrida.adicionarSapo(new Sapo("Sapo 3", corrida));
        corrida.adicionarSapo(new Sapo("Sapo 4", corrida));
        corrida.adicionarSapo(new Sapo("Sapo 5", corrida));

        if (corridaPanel != null) {
            remove(corridaPanel);
        }

        corridaPanel = new CorridaPanel(corrida);
        add(corridaPanel, BorderLayout.CENTER);

        revalidate();
        repaint();

        corrida.iniciarCorrida();
    }

    @Override
    public void aoAtualizarCorrida() {
        if (corridaPanel != null) {
            corridaPanel.repaint();
        }
    }

    @Override
    public void aoFinalizarCorrida(Sapo vencedor) {
        statusLabel.setText("Vencedor: " + vencedor.getName());
        if (corridaPanel != null) {
            corridaPanel.repaint();
        }
        iniciarButton.setEnabled(true);
    }
}