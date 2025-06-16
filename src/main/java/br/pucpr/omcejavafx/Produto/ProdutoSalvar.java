package br.pucpr.omcejavafx.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoSalvar {

    public static void salvarProduto(Produto novoProduto, String caminhoArquivo) throws IOException {
        List<Produto> produtos = carregarProdutos(caminhoArquivo);

        produtos.add(novoProduto);

        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(produtos);
            System.out.println("Produto salvo com sucesso!");
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Produto> carregarProdutos(String caminhoArquivo) {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fis = new FileInputStream(caminhoArquivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<Produto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar produtos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void atualizarProduto(Produto produtoAtualizado, String caminhoArquivo) throws IOException {
        List<Produto> produtos = carregarProdutos(caminhoArquivo);

        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == produtoAtualizado.getId()) {
                produtos.set(i, produtoAtualizado);
                break;
            }
        }

        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(produtos);
            System.out.println("Produto atualizado com sucesso!");
        }
    }

}
