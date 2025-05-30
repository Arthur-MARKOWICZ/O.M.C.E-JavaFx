package br.pucpr.omcejavafx;

import java.io.Serializable;

public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String nomeProduto;
    private double preco;
    private String detalhes;
    private boolean vendido;
    private byte[] imagem;
    private String imagemTipo;
    private String condicao;
    private String categoria;

    public Produto(long id, String nomeProduto, double preco, String Detalhes, boolean vendido,
                   byte[] imagem, String imageTipo, String condicao, String categoria) {
        this.nomeProduto = nomeProduto;
        this.id = id;
        this.preco = preco;
        this.detalhes = Detalhes;
        this.vendido = vendido;
        this.imagem = imagem;
        this.imagemTipo = imageTipo;
        this.condicao = condicao;
        this.categoria = categoria;
    }

    public String getNomeProduto() {return nomeProduto;}
    public long getId() {return id;}
    public double getPreco() {return preco;}
    public String getDetalhes() {return detalhes;}
    public boolean isVendido() {return vendido;}
    public byte[] getImagem() {return imagem;}
    public String getImageTipo() {return imagemTipo;}
    public String getCondicao() {return condicao;}
    public String getCategoria() {return categoria;}
}