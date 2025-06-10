package br.pucpr.omcejavafx;
import java.time.LocalDate;

public class AvaliarProduto {
    private long id;
    private double nota;
    private String comentario;
    private LocalDate dataAtual = LocalDate.now();


    public AvaliarProduto(long id, double nota, String comentario) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
    }

    public long getId() {
        return id;
    }

    public double getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getDataAtual() {
        return dataAtual;
    }

    public void setNota(double nota) {
        if (nota >= 0 && nota <= 10){
            this.nota = nota;
        }else{
            throw new IllegalArgumentException("Nota deve estar entre 0 e 10.");
        }
    }

    public void setComentario(String comentario){
        this.comentario = comentario;
    }
}






