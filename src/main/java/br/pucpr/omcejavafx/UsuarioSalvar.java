package br.pucpr.omcejavafx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioSalvar {


    public static void salvarUsuario(Usuario novoUsuario, String caminhoArquivo) throws IOException {
        List<Usuario> usuarios = carregarUsuarios(caminhoArquivo);
        usuarios.add(novoUsuario);

        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(usuarios);
            System.out.println("Usuário salvo com sucesso!");
        }
    }


    @SuppressWarnings("unchecked")
    public static List<Usuario> carregarUsuarios(String caminhoArquivo) {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fis = new FileInputStream(caminhoArquivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    public static void atualizarUsuario(Usuario usuarioAtualizado, String caminhoArquivo) throws IOException {
        List<Usuario> usuarios = carregarUsuarios(caminhoArquivo);

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == usuarioAtualizado.getId()) {
                usuarios.set(i, usuarioAtualizado);
                break;
            }
        }

        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(usuarios);
            System.out.println("Usuário atualizado com sucesso!");
        }
    }
}
