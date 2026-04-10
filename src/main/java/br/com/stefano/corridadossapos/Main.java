package br.com.stefano.corridadossapos;

public class Main {

    public static void main(String[] args) {
        Corrida corrida = new Corrida(50);

        corrida.adicionarSapo(new Sapo("Sapo 1", corrida));
        corrida.adicionarSapo(new Sapo("Sapo 2", corrida));
        corrida.adicionarSapo(new Sapo("Sapo 3", corrida));
        corrida.adicionarSapo(new Sapo("Sapo 4", corrida));
        corrida.adicionarSapo(new Sapo("Sapo 5", corrida));

        corrida.iniciarCorrida();
    }
}