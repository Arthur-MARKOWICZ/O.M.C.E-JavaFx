package br.pucpr.omcejavafx.Pagamento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class PagamentoDAO {
    private static final String ARQUIVO = "pagamentos.dat";


    public static void salvar(Pagamento pagamento) throws IOException, ClassNotFoundException {
        List<Pagamento> pagamentos = listar();
        pagamentos.add(pagamento);
        salvarTodos(pagamentos);
    }

    public boolean atualizar(Pagamento pagamentoAtualizado) throws IOException, ClassNotFoundException {
        List<Pagamento> pagamentos = listar();
        boolean encontrado = false;

        for (int i = 0; i < pagamentos.size(); i++) {
            Pagamento p = pagamentos.get(i);
            if (p.getId() == pagamentoAtualizado.getId()) {
                pagamentos.set(i, pagamentoAtualizado);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            salvarTodos(pagamentos);
        }

        return encontrado;
    }
    public static boolean excluir(int id) throws IOException, ClassNotFoundException {
        List<Pagamento> pagamentos = listar();
        boolean removido = pagamentos.removeIf(p -> p.getId() == id);

        if (removido) {
            salvarTodos(pagamentos);
        }

        return removido;
    }


    public static List<Pagamento> listar() throws IOException, ClassNotFoundException {
        File file = new File(ARQUIVO);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ObjectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Pagamento>) ObjectInputStream.readObject();
        }
    }

    private static void salvarTodos(List<Pagamento> pagamentos) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            objectOutputStream.writeObject(pagamentos);
        }
    }
}
