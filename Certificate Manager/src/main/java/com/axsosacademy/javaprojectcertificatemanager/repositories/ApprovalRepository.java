package com.axsosacademy.javaprojectcertificatemanager.repositories;

import com.axsosacademy.javaprojectcertificatemanager.models.Approval;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApprovalRepository extends CrudRepository<Approval, Long> {
    List<Approval> findAll();
}
