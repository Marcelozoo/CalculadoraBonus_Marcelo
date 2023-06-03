package services.operacoes;

import models.DadosPeso;
import models.Resultado;
import models.interfaces.IOperacao;

public class QuantidadeOperacao implements IOperacao {

    @Override
    public void calcular(DadosPeso peso) {
        peso.addResultado(new Resultado("Quantidade de Pesos", peso.getPesos().size()));

    }
}
