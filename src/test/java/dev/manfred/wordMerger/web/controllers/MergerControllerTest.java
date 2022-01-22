package dev.manfred.wordMerger.web.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MergerControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    void whenValidInput_thenReturn200() throws Exception {
        var ip = getClass().getClassLoader().getResourceAsStream("input.txt");
        MockMultipartFile firstFile = new MockMultipartFile("file", "input.txt", "text/plain", Objects.requireNonNull(ip).readAllBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/file")
                        .file(firstFile)
                        .param("some-random", "4"))
                .andExpect(status().is(200));
    }

    @Test
    void withInvalidInput_thenReturn400() throws Exception {
        MockMultipartFile firstFile = new MockMultipartFile("file", "invalid.txt", "text/plain", new byte[1024 * 1024 * 30]);

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/file")
                        .file(firstFile)
                        .param("some-random", "4"))
                .andExpect(status().is(400));
    }

}
