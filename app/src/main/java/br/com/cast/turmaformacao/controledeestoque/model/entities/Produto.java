package br.com.cast.turmaformacao.controledeestoque.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 25/09/2015.
 */
public class Produto implements Parcelable {

    private Long id;
    private String nome;
    private String descricao;
    private Integer quantidade;
    private Integer quantidadeMinima;
    private Double valorUnitario;
    private String foto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        if (id != null ? !id.equals(produto.id) : produto.id != null) return false;
        if (nome != null ? !nome.equals(produto.nome) : produto.nome != null) return false;
        if (descricao != null ? !descricao.equals(produto.descricao) : produto.descricao != null)
            return false;
        if (quantidade != null ? !quantidade.equals(produto.quantidade) : produto.quantidade != null)
            return false;
        if (quantidadeMinima != null ? !quantidadeMinima.equals(produto.quantidadeMinima) : produto.quantidadeMinima != null)
            return false;
        if (valorUnitario != null ? !valorUnitario.equals(produto.valorUnitario) : produto.valorUnitario != null)
            return false;
        return !(foto != null ? !foto.equals(produto.foto) : produto.foto != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (quantidade != null ? quantidade.hashCode() : 0);
        result = 31 * result + (quantidadeMinima != null ? quantidadeMinima.hashCode() : 0);
        result = 31 * result + (valorUnitario != null ? valorUnitario.hashCode() : 0);
        result = 31 * result + (foto != null ? foto.hashCode() : 0);
        return result;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public static Creator<Produto> getCREATOR() {
        return CREATOR;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.nome);
        dest.writeString(this.descricao);
        dest.writeInt(this.quantidade);
        dest.writeInt(this.quantidadeMinima);
        dest.writeDouble(this.valorUnitario);
        dest.writeString(this.foto);
    }

    public Produto() {
    }

    protected Produto(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.nome = in.readString();
        this.descricao = in.readString();
        this.quantidade = in.readInt();
        this.quantidadeMinima = in.readInt();
        this.valorUnitario = in.readDouble();
        this.foto = in.readString();
    }

    public static final Parcelable.Creator<Produto> CREATOR = new Parcelable.Creator<Produto>() {
        public Produto createFromParcel(Parcel source) {
            return new Produto(source);
        }

        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };
}
