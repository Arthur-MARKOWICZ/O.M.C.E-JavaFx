package br.pucpr.omcejavafx.Usuario;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String nome;
    private String nomeUsuario;
    private String cpf;
    private String senha;
    private String sexo;
    private String datadenascimento;
    private String telefone;
    private String email;
    private String endereco;
    private String cep;
    private Boolean ativo;

    public Usuario(long id, String nome, String nomeUsuario, String cpf, String senha, String sexo, String dataNascimento, String telefone, String email, String endereco, String cep, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.cpf = cpf;
        this.senha = senha;
        this.sexo = sexo;
        this.datadenascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.cep = cep;
        this.ativo = ativo;
    }

    public Usuario() {

    }


    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDatadenascimento() {
        return datadenascimento;
    }

    public void setDatadenascimento(String datadenascimento) {
        this.datadenascimento = datadenascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeusuario() {
        return nomeUsuario;
    }

    public void setNomeusuario(String nomeusuario) {
        this.nomeUsuario = nomeusuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
