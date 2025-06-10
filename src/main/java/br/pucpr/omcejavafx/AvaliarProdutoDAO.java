package br.pucpr.omcejavafx;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvaliarProdutoDAO {
    private static final String ARQUIVO = "AvaliarPedido.dat";

    public static void salvar(AvaliarProduto avaliacao) throws IOException {
        List<AvaliarProduto> avaliacoes = carregarTodos();
        avaliacoes.add(avaliacao);
        salvarTodos(avaliacoes);
    }

    public static List<AvaliarProduto> carregarTodos() throws IOException {
        List<AvaliarProduto> avaliacoes = new ArrayList<>();
        File file = new File(ARQUIVO);

        if (!file.exists()) return avaliacoes;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    long id = Long.parseLong(partes[0]);
                    double nota = Double.parseDouble(partes[1]);
                    String comentario = partes[2];
                    LocalDate data = LocalDate.parse(partes[3]);
                    AvaliarProduto ap = new AvaliarProduto(id, nota, comentario);

                    avaliacoes.add(ap);
                }
            }
        }

        return avaliacoes;
    }

    public static void salvarTodos(List<AvaliarProduto> avaliacoes) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (AvaliarProduto ap : avaliacoes) {
                bw.write(ap.getId() + ";" + ap.getNota() + ";" + ap.getComentario() + ";" + ap.getDataAtual());
                bw.newLine();
            }
        }
    }
}
