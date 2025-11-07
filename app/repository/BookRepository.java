package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Book;

import java.util.List;

public class BookRepository {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("bookPersistenceUnit");

    // ---------------------------
    // CREATE (POST)
    // ---------------------------
    public void addBook(Book book) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // ---------------------------
    // READ ALL (GET)
    // ---------------------------
    public List<Book> getAllBooks() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        } finally {
            em.close();
        }
    }

    // ---------------------------
    // READ BY ID (GET /:id)
    // ---------------------------
    public Book getBookById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Book.class, id);
        } finally {
            em.close();
        }
    }

    // ---------------------------
    // UPDATE (PUT /:id)
    // ---------------------------
    public boolean updateBook(Long id, Book updatedBook) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Book existingBook = em.find(Book.class, id);
            if (existingBook == null) {
                em.getTransaction().rollback();
                return false;
            }

            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setSubtitle(updatedBook.getSubtitle());
            existingBook.setCopyrightYear(updatedBook.getCopyrightYear());
            existingBook.setStatus(updatedBook.getStatus());

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

    // ---------------------------
    // DELETE (DELETE /:id)
    // ---------------------------
    public boolean deleteBook(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Book book = em.find(Book.class, id);
            if (book == null) {
                em.getTransaction().rollback();
                return false;
            }
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
