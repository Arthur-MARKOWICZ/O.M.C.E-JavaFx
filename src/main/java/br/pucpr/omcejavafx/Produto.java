package br.pucpr.omcejavafx;

public class Produto {
    private long id;
    private String nomeProduto;
    private double preco;
    private String detalhes;
    private boolean vendido;
    private byte[] imagem;
    private String imageTipo;
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
        this.imageTipo = imageTipo;
        this.condicao = condicao;
        this.categoria = categoria;
    }

    public String getNomeProduto() {return nomeProduto;}
    public long getId() {return id;}
    public double getPreco() {return preco;}
    public String getDetalhes() {return detalhes;}
    public boolean isVendido() {return vendido;}
    public byte[] getImagem() {return imagem;}
    public String getImageTipo() {return imageTipo;}
    public String getCondicao() {return condicao;}
    public String getCategoria() {return categoria;}
}
