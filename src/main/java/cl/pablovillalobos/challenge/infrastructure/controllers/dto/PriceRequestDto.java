package cl.pablovillalobos.challenge.infrastructure.controllers.dto;

import cl.pablovillalobos.challenge.infrastructure.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceRequestDto {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = Constants.LOCAL_DATE_TIME_DTO_FORMAT)
    private LocalDateTime date;
    @NotNull(message = "The brandId must have a value")
    @Min(value = 1, message = "The brandId must be greater than one")
    private Long productId;
    @NotNull(message = "The productId must hav a value")
    @Min(value = 1, message = "The productId must be greater than one")
    private Long brandId;
}
