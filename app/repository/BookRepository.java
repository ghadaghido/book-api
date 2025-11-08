package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Book;

import java.util.List;

/**
 * Repository layer responsible for CRUD operations on Book entities.
 *
 * ⚙️ Uses JPA (Jakarta Persistence API) to interact with the database.
 * 🟢 No authentication or security logic here (fully open access).
 *    Security is intentionally handled at the controller level (if ever needed).
 */
public class BookRepository {

    // Create one EntityManagerFactory for the whole application (connected to persistence.xml config)
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("bookPersistenceUnit");

    // ===========================================
    // CREATE: Add a new book to the database
    // ===========================================
    public void addBook(Book book) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book);  // Insert record
            em.getTransaction().commit();
        } catch (Exception e) {
            // Roll back transaction on error
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close(); // Always close connection
        }
    }

    // ===========================================
    // READ ALL: Fetch all books
    // ===========================================
    public List<Book> getAllBooks() {
        EntityManager em = emf.createEntityManager();
        try {
            // Simple JPQL query to get all records
            return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        } finally {
            em.close();
        }
    }

    // ===========================================
    // READ BY ID: Fetch single book by ID
    // ===========================================
    public Book getBookById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Book.class, id);
        } finally {
            em.close();
        }
    }

    // ===========================================
    // UPDATE: Update existing book by ID
    // ===========================================
    public boolean updateBook(Long id, Book updatedBook) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Fetch the existing record
            Book existingBook = em.find(Book.class, id);
            if (existingBook == null) {
                // Nothing to update → rollback
                em.getTransaction().rollback();
                return false;
            }

            // Update fields
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setSubtitle(updatedBook.getSubtitle());
            existingBook.setCopyrightYear(updatedBook.getCopyrightYear());
            existingBook.setStatus(updatedBook.getStatus());

            // Persist changes
            em.merge(existingBook);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // ===========================================
    // DELETE: Remove book by ID
    // ===========================================
    public boolean deleteBook(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Find record
            Book book = em.find(Book.class, id);
            if (book == null) {
                // Nothing to delete → rollback
                em.getTransaction().rollback();
                return false;
            }

            // Remove entity
            em.remove(book);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
