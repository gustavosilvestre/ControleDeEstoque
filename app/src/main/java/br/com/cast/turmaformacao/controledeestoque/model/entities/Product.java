package br.com.cast.turmaformacao.controledeestoque.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrador on 25/09/2015.
 */
public class Product implements Parcelable {

    @JsonIgnore
    private Long id;

    @JsonProperty("id")
    private Long id_web;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("stock")
    private Long stock;

    @JsonProperty("minimunStock")
    private Long minStock;

    @JsonProperty("unitaryValue")
    private Double unitPrice;

    @JsonProperty("image")
    private String image;

    @JsonProperty("date")
    private Long data;

    @JsonIgnore
    private Boolean flag;

    public Product() {
        flag = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (id_web != null ? !id_web.equals(product.id_web) : product.id_web != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null)
            return false;
        if (stock != null ? !stock.equals(product.stock) : product.stock != null) return false;
        if (minStock != null ? !minStock.equals(product.minStock) : product.minStock != null)
            return false;
        if (unitPrice != null ? !unitPrice.equals(product.unitPrice) : product.unitPrice != null)
            return false;
        return !(image != null ? !image.equals(product.image) : product.image != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (id_web != null ? id_web.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        result = 31 * result + (minStock != null ? minStock.hashCode() : 0);
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }


    public Boolean isFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }

    public Long getId_web() {
        return id_web;
    }

    public void setId_web(Long id_web) {
        this.id_web = id_web;
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

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getMinStock() {
        return minStock;
    }

    public void setMinStock(Long minStock) {
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
        dest.writeValue(this.id_web);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeValue(this.stock);
        dest.writeValue(this.minStock);
        dest.writeValue(this.unitPrice);
        dest.writeString(this.image);
        dest.writeValue(this.data);
        dest.writeValue(this.flag);
    }

    protected Product(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.id_web = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.description = in.readString();
        this.stock = (Long) in.readValue(Integer.class.getClassLoader());
        this.minStock = (Long) in.readValue(Integer.class.getClassLoader());
        this.unitPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.image = in.readString();
        this.data = (Long) in.readValue(Long.class.getClassLoader());
        this.flag = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
