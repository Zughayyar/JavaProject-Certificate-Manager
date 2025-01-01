package com.axsosacademy.javaprojectcertificatemanager.repositories;

import com.axsosacademy.javaprojectcertificatemanager.models.Certificate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CertificateRepository extends CrudRepository<Certificate, Long> {
    List<Certificate> findAll();



}
