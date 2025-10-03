package jwt.lap_trinh_web_09_01.dto;

import jwt.lap_trinh_web_09_01.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private BigDecimal price;
    private Integer stock;
    private String description;
    private String imageUrl;
    private String isbn;
    private Integer publicationYear;
    private String categoryName;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.price = book.getPrice();
        this.stock = book.getStock();
        this.description = book.getDescription();
        this.imageUrl = book.getImageUrl();
        this.isbn = book.getIsbn();
        this.publicationYear = book.getPublicationYear();
        this.categoryName = book.getCategory() != null ? book.getCategory().getName() : null;
    }
}


