package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by alantaylor on 2/26/17.
 */
@Entity
public class Albums {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String AlbumName;
    private String ArtistName;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {

        return id;
    }

    public String getAlbumName() {
        return AlbumName;
    }

    public void setAlbumName(String albumName) {
        this.AlbumName = albumName;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public void setArtistName(String artistName) {
        this.ArtistName = artistName;
    }
}
