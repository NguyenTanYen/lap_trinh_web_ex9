package jwt.lap_trinh_web_09_01.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 200)
    private String title;
    
    @NotBlank
    @Size(max = 100)
    private String author;
    
    @Size(max = 100)
    private String publisher;
    
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
    
    @NotNull
    private Integer stock;
    
    @Size(max = 1000)
    private String description;
    
    @Size(max = 500)
    private String imageUrl;
    
    @Column(name = "isbn", length = 20)
    private String isbn;
    
    @Column(name = "publication_year")
    private Integer publicationYear;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderItem> orderItems;
}


