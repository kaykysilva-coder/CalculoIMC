package org.calculoimc.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.calculoimc.Pessoa;
import org.calculoimc.utils.ArquivoUtil;

import java.io.IOException;
import java.util.List;

public class MainController {

    @FXML private TextField txtNome;
    @FXML private TextField txtAltura;
    @FXML private TextField txtPeso;
    @FXML private Label lblResultadoIMC;

    @FXML private TableView<Pessoa> tablePessoas;
    @FXML private TableColumn<Pessoa, Integer> colId;
    @FXML private TableColumn<Pessoa, String> colNome;
    @FXML private TableColumn<Pessoa, Double> colAltura;
    @FXML private TableColumn<Pessoa, Double> colImc;

    private ObservableList<Pessoa> listaPessoas = FXCollections.observableArrayList();
    private int proximoId = 1;

    @FXML
    public void initialize() {
        // Configura as colunas para pegar os dados da classe Pessoa
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
        colImc.setCellValueFactory(new PropertyValueFactory<>("imc"));

        tablePessoas.setItems(listaPessoas);
    }

    @FXML
    private void onCalcularClick() {
        try {
            String nome = txtNome.getText();
            double altura = Double.parseDouble(txtAltura.getText().replace(",", "."));
            double peso = Double.parseDouble(txtPeso.getText().replace(",", "."));

            if (nome.isEmpty()) {
                mostrarAlerta("Aviso", "Por favor, digite o nome.");
                return;
            }

            Pessoa p = new Pessoa(proximoId++, nome, altura, peso);

            // Exibe o resultado formatado com 2 casas decimais
            lblResultadoIMC.setText(String.format("%.2f", p.getImc()));

            // Adiciona na tabela
            listaPessoas.add(p);

            // Limpa os campos de texto
            txtNome.clear();
            txtAltura.clear();
            txtPeso.clear();

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Digite valores válidos para peso e altura!");
        }
    }

    @FXML
    private void onSalvarClick() {
        try {
            ArquivoUtil.salvarDados(listaPessoas);
            mostrarAlerta("Sucesso", "Dados salvos no arquivo com sucesso!");
        } catch (IOException e) {
            mostrarAlerta("Erro", "Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    @FXML
    private void onCarregarClick() {
        try {
            List<Pessoa> carregadas = ArquivoUtil.carregarDados();
            listaPessoas.clear();
            listaPessoas.addAll(carregadas);

            for (Pessoa p : carregadas) {
                if (p.getId() >= proximoId) {
                    proximoId = p.getId() + 1;
                }
            }

            mostrarAlerta("Sucesso", "Dados carregados do arquivo!");
        } catch (IOException e) {
            mostrarAlerta("Erro", "Erro ao carregar o arquivo: " + e.getMessage());
        }
    }
    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}