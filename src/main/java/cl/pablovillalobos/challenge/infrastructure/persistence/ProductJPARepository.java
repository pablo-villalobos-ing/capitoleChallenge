package cl.pablovillalobos.challenge.infrastructure.persistence;

import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJPARepository extends JpaRepository<ProductEntity, Long> {
}
