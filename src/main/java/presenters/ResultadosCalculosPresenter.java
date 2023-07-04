package presenters;

import models.Resultado;
import views.ResultadoCalculosView;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import services.ResultadoIgualService;

public class ResultadosCalculosPresenter {

    final private ResultadoCalculosView resultadosCalculos;
    final private JComboBox<String> comboBox;
    final private DefaultTableModel model;
    final private ArrayList<ArrayList<Resultado>> todosResultados;
    final private ResultadoIgualService resultadosIguaisService;
    private DecimalFormat formatadorDecimal;

    public ResultadosCalculosPresenter() {

        this.resultadosCalculos = new ResultadoCalculosView();
        this.todosResultados = new ArrayList<>();
        this.resultadosIguaisService = new ResultadoIgualService();
        this.comboBox = this.resultadosCalculos.getjComboBox();
        this.model = (DefaultTableModel) resultadosCalculos.getjTable1().getModel();
        this.formatadorDecimal = new DecimalFormat("0.00");

        configuraView();

    }

    private void configuraView() {

        this.resultadosCalculos.getJButton1().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resultadosCalculos.dispose();

            }
        });

        comboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                atualizaTabela(todosResultados.get(comboBox.getSelectedIndex()));
            }
        });

    }
    
    public void atualizaTabela(ArrayList<Resultado> result){
        limpaTabela();
        Integer tamanho = result.size();
        for (int i = 0; i < tamanho; i++) {
            this.model.addRow(new Object[] {result.get(i).getNome(),formatadorDecimal.format(result.get(i).getValor())});
        }
        
    }
    public void mostrarCalculos(ArrayList<Resultado> resultados) {

        if ((!resultadosIguaisService.comparaArrayResultados(todosResultados, resultados))
            || todosResultados.isEmpty()) {
            adicionarResultado(resultados);
            adicionaDataComboBox(resultados.get(0));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss");
            comboBox.setSelectedItem(resultados.get(0).getData().format(formatter));

        }
        limpaTabela();

        Integer tamanho = this.todosResultados.get(this.todosResultados.size() - 1).size();
        for (int i = 0; i < tamanho; i++) {
            this.model.addRow(new Object[] { todosResultados.get(this.todosResultados.size() - 1).get(i).getNome(),
                    formatadorDecimal.format(todosResultados.get(this.todosResultados.size() - 1).get(i).getValor())});

        }
        this.mostraTelaResultadosCalculos();

    }

    private void limpaTabela() {
        this.model.setRowCount(0);
    }

    private void adicionarResultado(ArrayList<Resultado> resultados) {
        todosResultados.add(resultados);

    }

    private void adicionaDataComboBox(Resultado result) {
        LocalDateTime dataFormatada = result.getData();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss");
        this.comboBox.addItem(dataFormatada.format(formatter));
    }

    private void mostraTelaResultadosCalculos() {
        this.resultadosCalculos.setVisible(true);
        this.resultadosCalculos.setLocationRelativeTo(this.resultadosCalculos.getParent());
        this.resultadosCalculos.setLocationRelativeTo(this.resultadosCalculos.getParent());
    }

}

