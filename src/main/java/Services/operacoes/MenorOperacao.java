package services.operacoes;

import java.util.Collections;
import models.interfaces.IOperacao;
import models.DadosPeso;
import models.Resultado;

public class MenorOperacao implements IOperacao {

    @Override
    public void calcular(DadosPeso peso) {
        peso.addResultado(new Resultado("Menor Peso", Collections.min(peso.getPesos())));
    }
}
