package models;

import java.util.ArrayList;

public class DadosPeso {

    private ArrayList<Double> pesos;
    private ArrayList<Resultado> resultados;

    public DadosPeso(ArrayList<Double> peso) {
        this.pesos = peso;
        this.resultados = new ArrayList<>();
    }

    public void setPesos(ArrayList<Double> peso) {
        this.pesos = peso;
    }

    public void add(double peso) {
        this.pesos.add(peso);
    }

    public ArrayList<Double> getPesos() {
        return this.pesos;
    }

    public void addResultado(Resultado resultado) {
        this.resultados.add(resultado);
    }

    public ArrayList<Resultado> getResultados() {
        return this.resultados;
    }

    @Override
    public String toString() {
        String output = "Pesos: " + pesos.toString() + "\nResultados: \n";
        for (Resultado r : resultados) {
            output += r.toString() + "\n";
        }
        return output;
    }

}
