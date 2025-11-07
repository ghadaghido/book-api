package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Book;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import repository.BookRepository;

import java.util.List;

public class BookController extends Controller {

    private final BookRepository bookRepository = new BookRepository();

    // ---------------------------
    // CREATE (POST /books)
    // ---------------------------
    public Result addBook(Http.Request request) {
        try {
            JsonNode json = request.body().asJson();
            if (json == null) {
                return badRequest("Invalid JSON");
            }

            Book book = Json.fromJson(json, Book.class);

            // Validate required fields
            if (book.getIsbn() == null || book.getTitle() == null ||
                    book.getCopyrightYear() == 0 || book.getStatus() == null) {
                return badRequest("Missing mandatory fields");
            }

            bookRepository.addBook(book);
            return created(Json.toJson(book));

        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError("Error while adding book: " + e.getMessage());
        }
    }

    // ---------------------------
    // READ ALL (GET /books)
    // ---------------------------
    public Result getBooks() {
        try {
            List<Book> books = bookRepository.getAllBooks();
            return ok(Json.toJson(books));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError("Error while fetching books: " + e.getMessage());
        }
    }

    // ---------------------------
    // READ BY ID (GET /books/:id)
    // ---------------------------
    public Result getBookById(Long id) {
        try {
            Book book = bookRepository.getBookById(id);
            if (book == null) {
                return notFound("Book not found with ID: " + id);
            }
            return ok(Json.toJson(book));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError("Error while fetching book: " + e.getMessage());
        }
    }
    // ---------------------------
// UPDATE (PUT /books/:id)
// ---------------------------
    public Result updateBook(Http.Request request, Long id) {
        try {
            // ✅ Log the raw body text (to debug Postman)
            String rawBody = request.body().asText();
            System.out.println("📦 Raw request body: " + rawBody);

            JsonNode json = request.body().asJson();

            // ✅ Also log the JSON node
            System.out.println("📘 Parsed JSON: " + json);

            if (json == null) {
                return badRequest("Invalid JSON or missing Content-Type: application/json");
            }

            Book updatedBook = Json.fromJson(json, Book.class);
            boolean updated = bookRepository.updateBook(id, updatedBook);

            if (!updated) {
                return notFound("Book not found for update with ID: " + id);
            }

            return ok("Book updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError("Error while updating book: " + e.getMessage());
        }
    }

    // ---------------------------
    // DELETE (DELETE /books/:id)
    // ---------------------------
    public Result deleteBook(Long id) {
        try {
            boolean deleted = bookRepository.deleteBook(id);

            if (!deleted) {
                return notFound("Book not found for deletion with ID: " + id);
            }

            return ok("Book deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError("Error while deleting book: " + e.getMessage());
        }
    }
}
