package jwt.lap_trinh_web_09_01.service;

import jwt.lap_trinh_web_09_01.dto.BookRequest;
import jwt.lap_trinh_web_09_01.dto.BookResponse;
import jwt.lap_trinh_web_09_01.entity.Book;
import jwt.lap_trinh_web_09_01.entity.Category;
import jwt.lap_trinh_web_09_01.repository.BookRepository;
import jwt.lap_trinh_web_09_01.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public Page<BookResponse> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(BookResponse::new);
    }

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return new BookResponse(book);
    }

    public Page<BookResponse> searchBooks(String title, String author, Long categoryId, 
                                         Double minPrice, Double maxPrice, Pageable pageable) {
        return bookRepository.searchBooks(title, author, categoryId, minPrice, maxPrice, pageable)
                .map(BookResponse::new);
    }

    public Page<BookResponse> getBooksByCategory(Long categoryId, Pageable pageable) {
        return bookRepository.findByCategoryId(categoryId, pageable)
                .map(BookResponse::new);
    }

    public List<BookResponse> getAvailableBooks() {
        return bookRepository.findByStockGreaterThan(0)
                .stream()
                .map(BookResponse::new)
                .collect(Collectors.toList());
    }

    public BookResponse createBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublisher(bookRequest.getPublisher());
        book.setPrice(bookRequest.getPrice());
        book.setStock(bookRequest.getStock());
        book.setDescription(bookRequest.getDescription());
        book.setImageUrl(bookRequest.getImageUrl());
        book.setIsbn(bookRequest.getIsbn());
        book.setPublicationYear(bookRequest.getPublicationYear());

        if (bookRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(bookRequest.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + bookRequest.getCategoryId()));
            book.setCategory(category);
        }

        Book savedBook = bookRepository.save(book);
        return new BookResponse(savedBook);
    }

    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublisher(bookRequest.getPublisher());
        book.setPrice(bookRequest.getPrice());
        book.setStock(bookRequest.getStock());
        book.setDescription(bookRequest.getDescription());
        book.setImageUrl(bookRequest.getImageUrl());
        book.setIsbn(bookRequest.getIsbn());
        book.setPublicationYear(bookRequest.getPublicationYear());

        if (bookRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(bookRequest.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + bookRequest.getCategoryId()));
            book.setCategory(category);
        }

        Book updatedBook = bookRepository.save(book);
        return new BookResponse(updatedBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public void updateStock(Long bookId, Integer quantity) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        
        if (book.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock for book: " + book.getTitle());
        }
        
        book.setStock(book.getStock() - quantity);
        bookRepository.save(book);
    }
}

