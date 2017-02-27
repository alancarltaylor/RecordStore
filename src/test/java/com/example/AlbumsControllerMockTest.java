package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Collections;
import java.util.Random;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by alantaylor on 2/26/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AlbumsController.class)
public class AlbumsControllerMockTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    AlbumsRepo repository;

    @Test
    public void testCreate() throws Exception {
        MockHttpServletRequestBuilder request = post("/albums/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"albumName\": \"Pat Garrett and Billy the Kid\", \"artistName\": \"Bob Dylan\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.albumName", equalTo("Pat Garrett and Billy the Kid")))
                .andExpect(jsonPath("$.artistName", equalTo("Bob Dylan")));
        verify(this.repository).save(any(Albums.class));

    }

    @Test
    public void testGetAllAlbums() throws Exception {
        Long id = new Random().nextLong();
        Albums album = new Albums();
        album.setAlbumName("Self Portrait");
        album.setArtistName("Bob Dylan");
        album.setId(id);

        when(this.repository.findAll()).thenReturn(Collections.singletonList(album));

        MockHttpServletRequestBuilder request  = get("/albums/all")
                .accept(MediaType.ALL);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].albumName", equalTo("Self Portrait")))
                .andExpect(jsonPath("$[0].artistName", equalTo("Bob Dylan")));

    }


}
