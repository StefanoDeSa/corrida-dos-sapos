package br.com.stefano.corridadossapos;

public interface CorridaListener {
    void aoAtualizarCorrida();
    void aoFinalizarCorrida(Sapo vencedor);
}