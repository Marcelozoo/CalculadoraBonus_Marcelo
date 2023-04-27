
package Model;

import Services.IOperacao;


public class MediaOperacao implements IOperacao {
    
    @Override
    public void calcular(DadosPeso peso){
        double total = 0;
        
        
        for (int i = 0; i < peso.getPesos().size(); i++) {
            total += peso.getPesos().get(i);
        }
    
        peso.addResultado(new Resultado("Media de Pesos", total / peso.getPesos().size()));
       
    }
    
}
