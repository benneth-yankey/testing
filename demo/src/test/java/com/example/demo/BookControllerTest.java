package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private WebApplicationContext context;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }

    @Test
    void getBooks() throws Exception {
        // given
        List<BookEntity> books =  List.of(
                new BookEntity(1, "The gods are not to be blamed", "Ola Rotimi"),
                new BookEntity(2, "Anthills of savannah", "Chenue Achebe"),
                new BookEntity(3, "Romeo and Juliet", "Shakespear")
        );

        // when
        when(bookService.getBooks()).thenReturn(books);

       // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("The gods are not to be blamed")));
    }

    @Test
    void saveBook() throws Exception {
        BookEntity book = new BookEntity(1, "The gods are not to be blamed", "Ola Rotimi");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/books")
                        .content(new ObjectMapper().writeValueAsString(book))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("The gods are not be blamed")));

    }
}



















