package services;

import java.util.ArrayList;

import models.Resultado;

public class ResultadoIgualService {

    public boolean comparaArrayResultados(ArrayList<ArrayList<Resultado>> listaResult, ArrayList<Resultado> resultados) {
        
        Integer qtdResultados = resultados.size();
        Integer tamanhoListaResultados = listaResult.size();
        Integer qtdResultIguais = 0;

        for(int i = 0; i < tamanhoListaResultados; i++){
            for(int j = 0; j < qtdResultados; j++){
                if(compararResultado(listaResult.get(i).get(j), resultados.get(j))){
                    qtdResultIguais++;
                }
            }
        }
        return qtdResultados == qtdResultIguais;
    }

    private boolean compararResultado(Resultado result, Resultado result2) {
        return (result.getNome() == result2.getNome() &&
                result.getValor() == result2.getValor());
    }

}
