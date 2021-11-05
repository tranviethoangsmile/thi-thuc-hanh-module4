package com.codegym.controller;

import com.codegym.entity.Province;
import com.codegym.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CountryApiController {


    @Autowired
    IProvinceService provinceService;

    @PostMapping ("/province/create")
    public Province create (@RequestBody Province province) {
        return provinceService.save(province);
    }

    @GetMapping("/province/getall")
        public Iterable<Province> getAll() {
            return provinceService.findAll();
        }

    @GetMapping ("/province/delete/${id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        provinceService.remove(id);

        Optional<Province> province = provinceService.findById(id);

        if (province.isPresent()) {
            return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

}
