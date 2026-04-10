package br.com.stefano.corridadossapos;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CorridaFrame frame = new CorridaFrame();
            frame.setVisible(true);
        });
    }
}