package Presenter;

import Model.CsvDados;
import View.CalculadoraEstatisticaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CalculadoraEstatisticaPresenter {

    final private CalculadoraEstatisticaView tela = new CalculadoraEstatisticaView();
    private CsvDados dadosArquivo;
    private boolean status = false;
    private boolean statusCalculo = false;
    private boolean primeiro = true;
    private ArrayList<Integer> dados;
    private ArrayList<Integer> dadosCalculos;
    private ResultadosCalculosPresenter resultadosCalculos;

    public CalculadoraEstatisticaPresenter() {

        this.dadosArquivo = new CsvDados();
        this.resultadosCalculos = new ResultadosCalculosPresenter();

        importacaoDados();
        calcularEstatisticas();
        mostrarEstatistica();
    }

    private void importacaoDados() {
        tela.getjMenuItem2().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                boolean valor = dadosArquivo.verificaArquivo(fileChooser, result);
                if (valor) {
                    dados = dadosArquivo.importarDados(fileChooser.getSelectedFile());
                    preencheTabela(dados);
                } else if (fileChooser.getSelectedFile() != null && valor == false) {
                    JOptionPane.showMessageDialog(null, "Arquivo inválido. Por favor, selecione um arquivo CSV.");

                }
            }
        });
    }

    private void preencheTabela(ArrayList<Integer> dados) {

        DefaultTableModel model = (DefaultTableModel) tela.getJTable1().getModel();
        model.setRowCount(0);
        this.dadosCalculos = new ArrayList();

        for (int i = 0; i < dados.size(); i++) {
            this.dadosCalculos.add(dados.get(i));
            model.addRow(new Object[]{dados.get(i)});
        }

        JLabel label = tela.getjLabel1();
        label.setText("Quantidade de dados: " + dados.size());
        this.status = true;

    }

    private void calcularEstatisticas() {

        tela.getjMenuItem1().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!status) {
                    JOptionPane.showMessageDialog(null, "Nenhum arquivo foi importado!");

                } else {
                    resultadosCalculos.realizarCalculos(dadosCalculos);
                    statusCalculo = true;

                }

            }
        });
    }

    private void mostrarEstatistica() {
        tela.getjMenuItem3().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!statusCalculo) {
                    JOptionPane.showMessageDialog(null, "Nenhum calculo foi realizado!");

                } else {

                    ArrayList<Double> doubleList = new ArrayList<>();
                    
                    for (Integer intValue : dadosCalculos) {
                        double doubleValue = intValue.doubleValue(); 
                        doubleList.add(doubleValue);
                    }
                    resultadosCalculos.visualizarCalculos(doubleList);

                }

            }
        });
    }

}
