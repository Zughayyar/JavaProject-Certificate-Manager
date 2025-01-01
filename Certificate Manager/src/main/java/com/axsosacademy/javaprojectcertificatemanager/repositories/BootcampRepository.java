package com.axsosacademy.javaprojectcertificatemanager.repositories;

import com.axsosacademy.javaprojectcertificatemanager.models.Bootcamp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BootcampRepository extends CrudRepository<Bootcamp, Long> {
    List<Bootcamp> findAll();
}
