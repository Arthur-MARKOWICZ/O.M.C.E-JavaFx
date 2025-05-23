package br.pucpr.omcejavafx;

import java.util.List;

public interface Crud {
    void Inserir(String texto);
    void Atualizar(String texto);
    List<String> Consulta();
    void Exclusao();
}
