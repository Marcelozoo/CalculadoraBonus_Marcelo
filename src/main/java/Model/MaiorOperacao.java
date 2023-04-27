
package Model;

import Services.IOperacao;
import java.util.Collections;



public class MaiorOperacao implements IOperacao {
    
    @Override
    public void calcular(DadosPeso peso){
        peso.addResultado( new Resultado("Maior Peso", Collections.max(peso.getPesos())));  
    }
    
}
