
package Services;

   

import Model.IOperacao;
import Model.*;
import java.util.ArrayList;

public class CalculadoraEstatisticaService {
        
    private ArrayList<IOperacao> operacoes;
        
    public CalculadoraEstatisticaService(){
        operacoes = new ArrayList<>();
        operacoes.add(new MaiorOperacao());
        operacoes.add(new MediaOperacao());
        operacoes.add(new SomatorioOperacao());
        operacoes.add(new MenorOperacao());
        operacoes.add(new VarianciaOperacao());
        operacoes.add(new DesvioPadraoOperacao());
        operacoes.add(new QuantidadeOperacao());
           
    }
        
    public void calcular(DadosPeso peso){
        for (IOperacao operacao : operacoes){
            operacao.calcular(peso);
        }

    }

}
