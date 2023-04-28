package Model;



public class DesvioPadraoOperacao implements IOperacao {

    @Override
    public void calcular(DadosPeso peso) {
        double soma = 0;
        {
            double p1 = 1 / Double.valueOf(peso.getPesos().size() - 1);
            double somaAoQuadrado = 0;
            
            for (int counter = 0; counter < peso.getPesos().size(); counter++) {
                somaAoQuadrado += Math.pow(peso.getPesos().get(counter), 2);
            }
            for (int counter = 0; counter < peso.getPesos().size(); counter++) {
                soma += peso.getPesos().get(counter);
            }
            double p2 = somaAoQuadrado - (Math.pow(soma, 2) / Double.valueOf(peso.getPesos().size()));
            peso.addResultado( new Resultado("Desvio Padrão dos pesos", Math.sqrt(p1 * p2)));    

        }
    }

}
