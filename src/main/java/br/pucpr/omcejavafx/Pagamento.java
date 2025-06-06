package br.pucpr.omcejavafx;

import java.io.Serializable;

public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String metodoPagamento;
    private String data;

    public Pagamento(long id, String metodoPagamento, String data) {
        this.id = id;
        this.metodoPagamento = metodoPagamento;
        this.data = data;
    }

    public Pagamento() {
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }


    public String getMetodoPagamento() { return metodoPagamento; }
    public void setMetodoPagamento(String metodoPagamento) { this.metodoPagamento = metodoPagamento; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    @Override
    public String toString() {
        return "Pagamento{id=" + id + ", metodoPagamento='" + metodoPagamento + "', data='" + data + "'}";
    }
}
