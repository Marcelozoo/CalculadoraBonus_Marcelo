package Model;

public class QuantidadeOperacao implements IOperacao {

    @Override
    public void calcular(DadosPeso peso) {
        peso.addResultado(new Resultado("Quantidade de Pesos", peso.getPesos().size()));

    }
}
