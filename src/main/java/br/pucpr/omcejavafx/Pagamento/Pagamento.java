package br.pucpr.omcejavafx.Pagamento;

import java.io.Serializable;

public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String metodoPagamento;
    private String data;

    public Pagamento(int id, String metodoPagamento, String data) {
        this.id = id;
        this.metodoPagamento = metodoPagamento;
        this.data = data;
    }

    public Pagamento() {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }


    public String getMetodoPagamento() { return metodoPagamento; }
    public void setMetodoPagamento(String metodoPagamento) { this.metodoPagamento = metodoPagamento; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    @Override
    public String toString() {
        return "Pagamento{id=" + id + ", metodoPagamento='" + metodoPagamento + "', data='" + data + "'}";
    }
}
