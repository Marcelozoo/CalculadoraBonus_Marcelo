package Model;

import Services.IOperacao;

public class VarianciaOperacao implements IOperacao {

    @Override
    public void calcular(DadosPeso peso) {
        
        double p1 = 1 / Double.valueOf(peso.getPesos().size() - 1);
        double somaAoQuadrado = 0;
        
        for (int counter = 0; counter < peso.getPesos().size(); counter++) {
            somaAoQuadrado += Math.pow(peso.getPesos().get(counter), 2);
        }
        double soma = 0;
        for (int counter = 0; counter < peso.getPesos().size(); counter++) {
            soma += peso.getPesos().get(counter);
        }
        double p2 = somaAoQuadrado - (Math.pow(soma, 2) / Double.valueOf(peso.getPesos().size()));
        peso.addResultado( new Resultado("Variancia dos pesos", p1 * p2));    
    }
}
