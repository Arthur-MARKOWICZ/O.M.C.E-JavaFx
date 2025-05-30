package br.pucpr.omcejavafx;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ProdutoSalvar {

    /**
     * Salva um objeto Produto em um arquivo binário (.dat).
     *
     * @param produto O objeto Produto a ser salvo.
     * @param caminhoArquivo O caminho do arquivo onde será salvo.
     * @throws IOException Se ocorrer um erro ao gravar o arquivo.
     */
    public static void salvarProduto(Produto produto, String caminhoArquivo) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(produto); // Serializa o objeto Produto
            System.out.println("Produto salvo com sucesso!");
        }
    }
}