package cl.pablovillalobos.challenge.infrastructure.entities;

import cl.pablovillalobos.challenge.util.Currency;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "price")
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_seq")
    @SequenceGenerator(name = "price_seq", sequenceName = "price_seq", initialValue = 1, allocationSize = 1)

    private Long id;
    @Column(name = "\"value\"")
    private BigDecimal value;
    private Currency currency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productEntityId")
    private ProductEntity productEntity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brandProductId")
    private BrandEntity brandEntity;
    private Short priority;
}
