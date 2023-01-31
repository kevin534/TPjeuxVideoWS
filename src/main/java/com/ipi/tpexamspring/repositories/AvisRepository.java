package com.ipi.tpexamspring.repositories;

import com.ipi.tpexamspring.models.Avis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisRepository extends CrudRepository<Avis,Long> {
    @Override
    List<Avis> findAll();
    List<Avis> findByJeuId(Long jeuId);
}
