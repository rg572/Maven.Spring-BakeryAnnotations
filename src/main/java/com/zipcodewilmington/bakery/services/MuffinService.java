package com.zipcodewilmington.bakery.services;

import com.zipcodewilmington.bakery.models.Muffin;
import com.zipcodewilmington.bakery.repositories.MuffinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class MuffinService {
    private MuffinRepository repository;

    @Autowired
    public MuffinService(MuffinRepository repository) {
        this.repository = repository;
    }


    public Iterable<Muffin> index() {
        return repository.findAll();
    }


    public Muffin show(Long id) {
        return repository.findById(id).get();
    }

    public Muffin create(Muffin baker) {
        return repository.save(baker);
    }

    @ResponseBody @PutMapping("/muffins/{id}")
    public Muffin update(@PathVariable Long id, @RequestBody Muffin newMuffinData) {
        Muffin originalMuffin = repository.findById(id).get();
        originalMuffin.setFlavor(newMuffinData.getFlavor());
        return repository.save(originalMuffin);
    }

    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }
}
