package services.operacoes;

import models.DadosPeso;
import models.Resultado;
import models.interfaces.IOperacao;
import java.util.Collections;


public class MenorOperacao implements IOperacao {
    
    @Override
    public void calcular(DadosPeso peso){
        peso.addResultado( new Resultado("Menor Peso", Collections.min(peso.getPesos())));  
    }
    
}
