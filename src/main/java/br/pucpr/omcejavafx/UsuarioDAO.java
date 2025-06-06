package br.pucpr.omcejavafx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
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
                String[] partes = linha.split(";", -1); // -1 para manter campos vazios
                if (partes.length == 12) {
                    long id = Long.parseLong(partes[0]);
                    String nome = partes[1];
                    String nomeUsuario = partes[2];
                    String cpf = partes[3];
                    String senha = partes[4];
                    String sexo = partes[5];
                    String dataNascimento = partes[6];
                    String telefone = partes[7];
                    String email = partes[8];
                    String endereco = partes[9];
                    String cep = partes[10];
                    boolean ativo = Boolean.parseBoolean(partes[11]);

                    Usuario usuario = new Usuario(id, nome, nomeUsuario, cpf, senha,
                            sexo, dataNascimento, telefone, email, endereco, cep, ativo);
                    usuarios.add(usuario);
                }
            }
        }

        return usuarios;
    }

    public static boolean atualizar(Usuario novoUsuario) throws IOException {
        List<Usuario> usuarios = carregarTodos();
        boolean atualizado = false;

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == novoUsuario.getId()) {
                usuarios.set(i, novoUsuario);
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
                bw.write(String.join(";",
                        String.valueOf(u.getId()),
                        u.getNome(),
                        u.getNomeusuario(),
                        u.getCpf(),
                        u.getSenha(),
                        u.getSexo(),
                        u.getDatadenascimento(),
                        u.getTelefone(),
                        u.getEmail(),
                        u.getEndereco(),
                        u.getCep(),
                        String.valueOf(u.getAtivo())
                ));
                bw.newLine();
            }
        }
    }
}

