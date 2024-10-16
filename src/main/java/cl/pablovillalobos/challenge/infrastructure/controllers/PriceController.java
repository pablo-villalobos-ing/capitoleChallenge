package cl.pablovillalobos.challenge.infrastructure.controllers;

import cl.pablovillalobos.challenge.application.services.PriceService;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/v1//public/price")
@Slf4j
public class PriceController {
    private final PriceService priceService;

    @PostMapping("/request")
    public PriceResponseDto requestPrice(@RequestBody PriceRequestDto dto) {
        return priceService.foundPriceResponse(dto);

    }
}
