package services.operacoes;

import models.DadosPeso;
import models.Resultado;
import models.interfaces.IOperacao;

public class SomatorioOperacao implements IOperacao {

    @Override
    public void calcular(DadosPeso peso) {
        double soma = 0;

        for (int counter = 0; counter < peso.getPesos().size(); counter++) {
            soma += peso.getPesos().get(counter);
        }
        peso.addResultado(new Resultado("Somatorio dos Pesos", soma));

    }
}
