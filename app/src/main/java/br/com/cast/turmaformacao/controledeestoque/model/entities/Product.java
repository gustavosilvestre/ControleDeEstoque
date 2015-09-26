package br.com.cast.turmaformacao.controledeestoque.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 25/09/2015.
 */
public class Product implements Parcelable {

    private Long id;
    private String name;
    private String description;
    private Integer stock;
    private Integer minStock;
    private Double unitPrice;
    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product produto = (Product) o;

        if (id != null ? !id.equals(produto.id) : produto.id != null) return false;
        if (name != null ? !name.equals(produto.name) : produto.name != null) return false;
        if (description != null ? !description.equals(produto.description) : produto.description != null)
            return false;
        if (stock != null ? !stock.equals(produto.stock) : produto.stock != null)
            return false;
        if (minStock != null ? !minStock.equals(produto.minStock) : produto.minStock != null)
            return false;
        if (unitPrice != null ? !unitPrice.equals(produto.unitPrice) : produto.unitPrice != null)
            return false;
        return !(image != null ? !image.equals(produto.image) : produto.image != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        result = 31 * result + (minStock != null ? minStock.hashCode() : 0);
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getMinStock() {
        return minStock;
    }

    public void setMinStock(Integer minStock) {
        this.minStock = minStock;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public static Creator<Product> getCREATOR() {
        return CREATOR;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.stock);
        dest.writeInt(this.minStock);
        dest.writeDouble(this.unitPrice);
        dest.writeString(this.image);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.description = in.readString();
        this.stock = in.readInt();
        this.minStock = in.readInt();
        this.unitPrice = in.readDouble();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
