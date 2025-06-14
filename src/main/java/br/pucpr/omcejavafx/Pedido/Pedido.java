package br.pucpr.omcejavafx.Pedido;

import java.io.Serializable;

public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private double valor;
    private String enderecoEntrega;

    public Pedido(long id, double valor, String enderecoEntrega) {
        this.id = id;
        this.valor = valor;
        this.enderecoEntrega = enderecoEntrega;
    }

    public long getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", valor=" + valor +
                ", enderecoEntrega='" + enderecoEntrega + '\'' +
                '}';
    }
}
