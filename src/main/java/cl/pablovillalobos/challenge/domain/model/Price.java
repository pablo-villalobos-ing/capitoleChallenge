package cl.pablovillalobos.challenge.domain.model;

import cl.pablovillalobos.challenge.infrastructure.util.Currency;
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
public class Price {
    private Long id;
    private BigDecimal value;
    private Currency currency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Long productId;
    private Long brandId;
    private Short priority;
}
