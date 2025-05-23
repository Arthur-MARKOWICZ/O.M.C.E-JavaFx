package br.pucpr.omcejavafx.Cruds;

import br.pucpr.omcejavafx.Crud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrudDoPagamento implements Crud {
    private String caminhoArquivo;
    @Override
    public void Inserir(String texto) {

    }

    @Override
    public void Atualizar(String texto) {

    }

    @Override
    public List<String> Consulta() {
        List<String> linhas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return linhas;
    }


    @Override
    public void Exclusao() {

    }
}
