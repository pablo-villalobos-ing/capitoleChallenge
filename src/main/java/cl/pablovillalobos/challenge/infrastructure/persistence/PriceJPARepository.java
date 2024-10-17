package cl.pablovillalobos.challenge.infrastructure.persistence;

import cl.pablovillalobos.challenge.infrastructure.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceJPARepository extends JpaRepository<PriceEntity, Long> {
    @Query("SELECT p from PriceEntity p " +
            "LEFT JOIN p.brandEntity b " +
            "LEFT JOIN p.productEntity pr " +
            "WHERE " +
            "b.brandEntityId = :brandId " +
            "AND :date BETWEEN p.startDate and p.endDate " +
            "AND pr.productEntityId = :productId " +
            "ORDER BY p.priceList DESC")
    List<PriceEntity> findByBrandIdAndDateAndProductId(Long brandId, LocalDateTime date, Long productId);
}
