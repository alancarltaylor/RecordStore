package com.example;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by alantaylor on 2/26/17.
 */
public interface AlbumsRepo extends CrudRepository <Albums, Long>{
    Albums findByArtistName(String ArtistName);
}
