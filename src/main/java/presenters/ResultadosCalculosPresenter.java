package presenters;

import models.DadosPeso;
import models.Resultado;
import views.ResultadoCalculosView;
import services.CalculadoraEstatisticaService;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class ResultadosCalculosPresenter {

    final private ResultadoCalculosView resultadosCalculos;
    final private CalculadoraEstatisticaService calculadoraService = new CalculadoraEstatisticaService();
    private ArrayList<Resultado> resultadosNovos;
    private DadosPeso peso;
    final private ArrayList<Double> resultadosTotais;
    final private ArrayList<String> datasTotais;
    final private JComboBox<String> comboBox;
    final private DefaultTableModel model;

    public ResultadosCalculosPresenter() {

        this.resultadosCalculos = new ResultadoCalculosView();
        this.resultadosTotais = new ArrayList<>();
        this.datasTotais = new ArrayList<>();
        this.comboBox = this.resultadosCalculos.getjComboBox();
        this.model = (DefaultTableModel) resultadosCalculos.getjTable1().getModel();

        fechar();

    }

    public void realizarCalculos(ArrayList<Double> dados) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss");


        this.peso = new DadosPeso(dados);
        this.calculadoraService.calcular(this.peso);

        resultadosNovos = this.peso.getResultados();
        LocalDateTime dataFormatada = resultadosNovos.get(0).getData();

        this.comboBox.addItem(dataFormatada.format(formatter));
        this.datasTotais.add(dataFormatada.format(formatter));

        armazenaResultados(peso);
        visualizarCalculos(dados);
        atualizarTabela();

    }
    public void visualizarCalculos(ArrayList<Double> resultados) {
        int i;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss");
        this.comboBox.setSelectedItem(resultadosNovos.get(0).getData().format(formatter));
        
        this.mostraTelaResultadosCalculos();
        this.model.setRowCount(0);

        for (i = 0; i < this.resultadosNovos.size(); i++) {
            this.model.addRow(new Object[]{resultadosNovos.get(i).getNome(), resultadosNovos.get(i).getValor()});
        }

    }

    private void atualizarTabela() {

        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int i;

                String itemSelecionado = (String) comboBox.getSelectedItem();
                for (i = 0; i < comboBox.getItemCount(); i++) {
                    if (datasTotais.get(i).equals(itemSelecionado)) {
                        break;
                    }
                }
                model.setRowCount(0);
                int count = 0;

                for (int j = i * 7; j < resultadosTotais.size(); j++) {

                    if (count == 7) {
                        break;
                    }
                    model.addRow(new Object[]{resultadosNovos.get(count).getNome(), resultadosTotais.get(j)});
                    count++;
                }

            }
        };

        comboBox.addActionListener(listener);
    }

    private void armazenaResultados(DadosPeso peso) {
        ArrayList<Resultado> resultado = peso.getResultados();

        for (int i = 0; i < resultado.size(); i++) {
            this.resultadosTotais.add(resultado.get(i).getValor());
        }

    }
    
    private void mostraTelaResultadosCalculos() {
        this.resultadosCalculos.setVisible(true);
        this.resultadosCalculos.setLocationRelativeTo(this.resultadosCalculos.getParent());
        this.resultadosCalculos.setLocationRelativeTo(this.resultadosCalculos.getParent());
    }

    private void fechar() {

        this.resultadosCalculos.getJButton1().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resultadosCalculos.dispose();

            }
        });
    }

}
