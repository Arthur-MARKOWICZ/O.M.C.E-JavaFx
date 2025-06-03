package br.pucpr.omcejavafx;

import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ProdutoSalvar {
    private static final String ARQUIVO_DAT = System.getProperty("user.home") + "/produtos_omce.dat";

    /**
     * Salva uma lista de produtos em um arquivo binário (.dat).
     *
     * @param produtos A lista de objetos Produto a ser salva.
     * @param caminhoArquivo O caminho do arquivo onde será salvo.
     * @throws IOException Se ocorrer um erro ao gravar o arquivo.
     */
    public static void salvarProdutos(List<Produto> produtos, String caminhoArquivo) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(produtos); // Serializa uma lista de produtos
            System.out.println("Lista de produtos salva com sucesso!");

        }
    }

    public static List<Produto> carregarProdutos() {
        File arquivo = new File(ARQUIVO_DAT);

        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado. Criando nova lista.");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DAT))) {
            return (List<Produto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar produtos: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static String getCaminhoArquivo() {
        return ARQUIVO_DAT;
    }
}