package br.pucpr.omcejavafx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private static final String ARQUIVO = "pedidos.txt";

    public static void salvar(Pedido pedido) throws IOException {
        List<Pedido> pedidos = carregarTodos();
        pedidos.add(pedido);
        salvarTodos(pedidos);
    }

    public static List<Pedido> carregarTodos() throws IOException {
        List<Pedido> pedidos = new ArrayList<>();
        File file = new File(ARQUIVO);

        if (!file.exists()) return pedidos;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    long id = Long.parseLong(partes[0]);
                    double valor = Double.parseDouble(partes[1]);
                    String endereco = partes[2];
                    pedidos.add(new Pedido(id, valor, endereco));
                }
            }
        }

        return pedidos;
    }

    public static boolean atualizar(long id, double novoValor, String novoEndereco) throws IOException {
        List<Pedido> pedidos = carregarTodos();
        boolean atualizado = false;

        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                p.setValor(novoValor);
                p.setEnderecoEntrega(novoEndereco);
                atualizado = true;
                break;
            }
        }

        if (atualizado) {
            salvarTodos(pedidos);
        }

        return atualizado;
    }

    public static boolean excluir(long id) throws IOException {
        List<Pedido> pedidos = carregarTodos();
        boolean removido = pedidos.removeIf(p -> p.getId() == id);
        if (removido) {
            salvarTodos(pedidos);
        }
        return removido;
    }

    private static void salvarTodos(List<Pedido> pedidos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Pedido p : pedidos) {
                bw.write(p.getId() + ";" + p.getValor() + ";" + p.getEnderecoEntrega());
                bw.newLine();
            }
        }
    }
}