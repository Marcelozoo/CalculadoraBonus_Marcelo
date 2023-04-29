package Model;

import java.time.LocalDateTime;


public class Resultado {

    private String nome;
    private Double valor;
    private LocalDateTime data;

    public Resultado(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
        this.data = LocalDateTime.now();

    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + ", Valor: " + this.valor;
    }

}
