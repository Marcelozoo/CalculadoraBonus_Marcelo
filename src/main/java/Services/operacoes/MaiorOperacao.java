package services.operacoes;

import models.DadosPeso;
import models.Resultado;
import models.interfaces.IOperacao;
import java.util.Collections;



public class MaiorOperacao implements IOperacao {
    
    @Override
    public void calcular(DadosPeso peso){
        peso.addResultado( new Resultado("Maior Peso", Collections.max(peso.getPesos())));  
    }
    
}
