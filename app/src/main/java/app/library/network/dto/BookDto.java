package app.library.network.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class BookDto {
    @SerializedName("image")
    private String image;
    @SerializedName("price")
    private String price;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("isbn13")
    private String isbn13;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;

    public BookDto() {
    }

    public BookDto(String image, String price, String subtitle, String isbn13, String title, String url) {
        this.image = image;
        this.price = price;
        this.subtitle = subtitle;
        this.isbn13 = isbn13;
        this.title = title;
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookDto bookDto = (BookDto) o;

        if (!Objects.equals(image, bookDto.image)) return false;
        if (!Objects.equals(price, bookDto.price)) return false;
        if (!Objects.equals(subtitle, bookDto.subtitle))
            return false;
        if (!Objects.equals(isbn13, bookDto.isbn13)) return false;
        if (!Objects.equals(title, bookDto.title)) return false;
        return Objects.equals(url, bookDto.url);
    }

    @Override
    public int hashCode() {
        int result = image != null ? image.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (subtitle != null ? subtitle.hashCode() : 0);
        result = 31 * result + (isbn13 != null ? isbn13.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
