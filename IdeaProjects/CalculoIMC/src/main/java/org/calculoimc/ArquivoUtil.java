package org.calculoimc.utils;

import org.calculoimc.Pessoa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    private static final String NOME_ARQUIVO = "dados_pessoas.txt";

    public static void salvarDados(List<Pessoa> pessoas) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO));

        for (Pessoa p : pessoas) {
            String linha = p.getId() + "," + p.getNome() + "," + p.getAltura() + "," + p.getPeso() + "," + p.getImc();
            writer.write(linha);
            writer.newLine();
        }

        writer.close();
    }

    public static List<Pessoa> carregarDados() throws IOException {
        List<Pessoa> pessoas = new ArrayList<>();
        File file = new File(NOME_ARQUIVO);

        // Se o arquivo ainda nao existir, retorna a lista vazia
        if (!file.exists()) {
            return pessoas;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String linha;

        while ((linha = reader.readLine()) != null) {
            if (linha.trim().isEmpty()) {
                continue;
            }

            String[] dados = linha.split(",");
            if (dados.length >= 5) {
                int id = Integer.parseInt(dados[0].trim());
                String nome = dados[1].trim();
                double altura = Double.parseDouble(dados[2].trim().replace(",", "."));
                double peso = Double.parseDouble(dados[3].trim().replace(",", "."));
                double imc = Double.parseDouble(dados[4].trim().replace(",", "."));

                Pessoa p = new Pessoa(id, nome, altura, peso, imc);
                pessoas.add(p);
            }
        }

        reader.close();
        return pessoas;
    }
}