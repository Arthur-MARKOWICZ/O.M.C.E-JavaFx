package br.pucpr.omcejavafx;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class UsuarioSalvar {



    public static void salvarUsuario(Usuario usuario, String caminhoArquivo) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(usuario); // Serializa o objeto Produto
            System.out.println("Usuario salvo com sucesso!");
        }
    }
}
