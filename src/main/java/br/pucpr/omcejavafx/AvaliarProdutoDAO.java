package br.pucpr.omcejavafx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AvaliarProdutoDAO {

    public static void salvarAvaliacao(AvaliarProduto novaAvaliacao, String caminhoArquivo) throws IOException {
        List<AvaliarProduto> avaliacao = carregarAvaliacao(caminhoArquivo);
        avaliacao.add(novaAvaliacao);

        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(avaliacao);
            System.out.println("Avaliação salva com sucesso!");
        }
    }

    @SuppressWarnings("unchecked")
    public static List<AvaliarProduto> carregarAvaliacao(String caminhoArquivo) {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fis = new FileInputStream(caminhoArquivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<AvaliarProduto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar avaliações: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static boolean excluirAvaliacao(long id, String caminhoArquivo) throws IOException {
        List<AvaliarProduto> avaliacao = carregarAvaliacao(caminhoArquivo);
        boolean removido = avaliacao.removeIf(p -> p.getId() == id);

        if (removido) {
            try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(avaliacao);
            }
            System.out.println("Avaliação excluída com sucesso!");
        }

        return removido;
    }

    public static void atualizarAvaliacao(AvaliarProduto avaliacaoAtulizada, String caminhoArquivo) throws IOException {
        List<AvaliarProduto> avaliacao = carregarAvaliacao(caminhoArquivo);

        for (int i = 0; i < avaliacao.size(); i++) {
            if (avaliacao.get(i).getId() == avaliacaoAtulizada.getId()) {
                avaliacao.set(i, avaliacaoAtulizada);
                break;
            }
        }

        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(avaliacao);
            System.out.println("Avaliação atualizada com sucesso!");
        }
    }
}