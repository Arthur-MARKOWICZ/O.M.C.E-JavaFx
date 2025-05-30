package br.pucpr.omcejavafx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private static final String ARQUIVO = "usuarios.txt";

    public static void salvar(Usuario usuario) throws IOException {
        List<Usuario> usuarios = carregarTodos();
        usuarios.add(usuario);
        salvarTodos(usuarios);
    }

    public static List<Usuario> carregarTodos() throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        File file = new File(ARQUIVO);

        if (!file.exists()) return usuarios;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    long id = Long.parseLong(partes[0]);
                    String nome = partes[1];
                    String email = partes[2];
                    usuarios.add(new Usuario(id, nome, email));
                }
            }
        }

        return usuarios;
    }

    public static boolean atualizar(long id, String novoNome, String novoEmail) throws IOException {
        List<Usuario> usuarios = carregarTodos();
        boolean atualizado = false;

        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                u.setNome(novoNome);
                u.setEmail(novoEmail);
                atualizado = true;
                break;
            }
        }

        if (atualizado) {
            salvarTodos(usuarios);
        }

        return atualizado;
    }

    public static boolean excluir(long id) throws IOException {
        List<Usuario> usuarios = carregarTodos();
        boolean removido = usuarios.removeIf(u -> u.getId() == id);
        if (removido) {
            salvarTodos(usuarios);
        }
        return removido;
    }

    private static void salvarTodos(List<Usuario> usuarios) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Usuario u : usuarios) {
                bw.write(u.getId() + ";" + u.getNome() + ";" + u.getEmail());
                bw.newLine();
            }
        }
    }
}
