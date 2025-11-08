package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Result;

import static org.junit.Assert.*;
import static play.test.Helpers.*;
import static org.hamcrest.CoreMatchers.*;

public class BookControllerTest {

    private Application app = new GuiceApplicationBuilder().build();

    @Test
    public void testListBooks() {
        Result result = route(app, fakeRequest(GET, "/books"));
        assertEquals(OK, result.status());
    }

    @Test
    public void testGetBookByIsbn() {
        Result result = route(app, fakeRequest(GET, "/books/1"));
        assertThat(result.status(), is(OK));
    }
    @Test
    public void testAddBook() {
        String isbn = "ISBN-" + System.currentTimeMillis();

        String json = String.format("""
        {
            "isbn": "%s",
            "title": "Test Book",
            "subtitle": "Testing Play Framework",
            "copyrightYear": 2025,
            "status": "AVAILABLE"
        }
        """, isbn);

        Result result = route(app, fakeRequest(POST, "/books")
                .bodyJson(play.libs.Json.parse(json))
                .header("Content-Type", "application/json"));

        assertEquals(CREATED, result.status());
    }


    @Test
    public void testDeleteBook() {
        Result result = route(app, fakeRequest(DELETE, "/books/123456"));
        assertTrue(result.status() == OK || result.status() == NOT_FOUND);
    }
}
