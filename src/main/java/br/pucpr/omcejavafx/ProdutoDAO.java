package br.pucpr.omcejavafx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    /**
     * Adiciona um único produto à lista existente no arquivo e salva.
     *
     * @param produto O produto a ser adicionado.
     * @param caminhoArquivo O caminho do arquivo onde a lista será salva.
     * @throws IOException Se houver problema com o arquivo.
     * @throws ClassNotFoundException Se houver problema ao carregar a lista existente.
     */
    public static void adicionarProduto(Produto produto, String caminhoArquivo) throws IOException, ClassNotFoundException {
        List<Produto> produtos;

        // Tenta carregar a lista existente
        try {
            produtos = carregarProdutos(caminhoArquivo);
        } catch (IOException | ClassNotFoundException e) {
            produtos = new ArrayList<>(); // Se o arquivo não existe ou está vazio, cria nova lista
        }

        // Adiciona o novo produto à lista
        produtos.add(produto);

        // Salva a lista atualizada
        salvarProdutos(produtos, caminhoArquivo);
    }

    /**
     * Salva uma lista de produtos no arquivo (sobrescreve o conteúdo do arquivo).
     *
     * @param produtos A lista de produtos a ser salva.
     * @param caminhoArquivo O caminho do arquivo onde será salva.
     * @throws IOException Caso haja erro ao salvar.
     */
    public static void salvarProdutos(List<Produto> produtos, String caminhoArquivo) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(produtos); // Salva (serializa) a lista de produtos
        }
    }

    /**
     * Carrega uma lista de produtos do arquivo binário.
     *
     * @param caminhoArquivo O caminho do arquivo a ser lido.
     * @return A lista de produtos carregada.
     * @throws IOException Caso o arquivo não exista ou esteja corrompido.
     * @throws ClassNotFoundException Caso não seja possível desserializar.
     */
    public static List<Produto> carregarProdutos(String caminhoArquivo) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(caminhoArquivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (List<Produto>) ois.readObject(); // Carrega e retorna a lista
        }
    }
}