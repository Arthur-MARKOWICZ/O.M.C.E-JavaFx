package br.pucpr.omcejavafx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoSalvar {
    private static final String ARQUIVO_DAT = System.getProperty("user.home") + "/produtos_omce.dat";

    public static void salvarProduto(Produto produto) {
        List<Produto> produtos = carregarProdutos();
        produtos.add(produto);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DAT))) {
            oos.writeObject(produtos);
            System.out.println("Produto salvo com sucesso em: " + ARQUIVO_DAT);
        } catch (IOException e) {
            System.err.println("Erro ao salvar produto: " + e.getMessage());
            e.printStackTrace();
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