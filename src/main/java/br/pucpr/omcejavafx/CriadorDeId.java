package br.pucpr.omcejavafx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CriadorDeId {
    public static int pegarUltimoId(String caminhoArquivo) {
        int ultimoId = -1;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("id:")) {
                    String[] partes = linha.split(":");
                    if (partes.length > 1) {
                        try {
                            ultimoId = Integer.parseInt(partes[1].trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Formato de ID inválido na linha: " + linha);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ultimoId;
    }

}
