package com.example;

import org.springframework.web.bind.annotation.*;

/**
 * Created by alantaylor on 2/26/17.
 */
@RestController
@RequestMapping("/albums")
public class AlbumsController {
    private final AlbumsRepo repository;

    public AlbumsController(AlbumsRepo repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public Iterable<Albums> getAllRecords(){
        return this.repository.findAll();
    }

    @PostMapping("/add")
    public Albums create(@RequestBody Albums album){
        this.repository.save(album);
        return album;
    }
}
