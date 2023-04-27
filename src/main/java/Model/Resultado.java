
package Model;
import java.time.LocalDate;

public class Resultado {
    
    private String nome;
    private Double valor;
    private LocalDate data;
    
    public Resultado(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
        this.data = LocalDate.now();
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }
    
    @Override
    public String toString(){
        return "Nome: " + this.nome + ", Valor: " + this.valor;
    }
     
}
