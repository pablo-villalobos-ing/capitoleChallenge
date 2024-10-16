package cl.pablovillalobos.challenge.infrastructure.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceRequestDto {
    private LocalDateTime date;
    private Long productId;
    private Long brandId;
}
