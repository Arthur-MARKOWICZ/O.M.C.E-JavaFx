package br.pucpr.omcejavafx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public static void salvarPedido(Pedido novoPedido, String caminhoArquivo) throws IOException {
        List<Pedido> pedidos = carregarPedidos(caminhoArquivo);
        pedidos.add(novoPedido);

        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(pedidos);
            System.out.println("Pedido salvo com sucesso!");
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Pedido> carregarPedidos(String caminhoArquivo) {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fis = new FileInputStream(caminhoArquivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<Pedido>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar pedidos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static boolean excluirPedido(long id, String caminhoArquivo) throws IOException {
        List<Pedido> pedidos = carregarPedidos(caminhoArquivo);
        boolean removido = pedidos.removeIf(p -> p.getId() == id);

        if (removido) {
            try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(pedidos);
            }
            System.out.println("Pedido excluído com sucesso!");
        }

        return removido;
    }

    public static void atualizarPedido(Pedido pedidoAtulizado, String caminhoArquivo) throws IOException {
        List<Pedido> pedidos = carregarPedidos(caminhoArquivo);

        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId() == pedidoAtulizado.getId()) {
                pedidos.set(i, pedidoAtulizado);
                break;
            }
        }

        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(pedidos);
            System.out.println("Pedido atualizado com sucesso!");
        }
    }
}