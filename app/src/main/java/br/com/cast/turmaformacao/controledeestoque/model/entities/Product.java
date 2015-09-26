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

    public Product() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null)
            return false;
        if (stock != null ? !stock.equals(product.stock) : product.stock != null)
            return false;
        if (minStock != null ? !minStock.equals(product.minStock) : product.minStock != null)
            return false;
        if (unitPrice != null ? !unitPrice.equals(product.unitPrice) : product.unitPrice != null)
            return false;
        return !(image != null ? !image.equals(product.image) : product.image != null);

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeValue(this.stock);
        dest.writeValue(this.minStock);
        dest.writeValue(this.unitPrice);
        dest.writeString(this.image);
    }

    protected Product(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.description = in.readString();
        this.stock = (Integer) in.readValue(Integer.class.getClassLoader());
        this.minStock = (Integer) in.readValue(Integer.class.getClassLoader());
        this.unitPrice = (Double) in.readValue(Double.class.getClassLoader());
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
