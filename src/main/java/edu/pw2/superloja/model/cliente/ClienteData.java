package edu.pw2.superloja.model.cliente;

public record ClienteData(String nome, Integer pontuacao, Double avaliacao) {
    public ClienteData(Cliente cliente){
        this(cliente.getNome(), cliente.getPontuacao(), cliente.getAvaliacao());
    }
}
