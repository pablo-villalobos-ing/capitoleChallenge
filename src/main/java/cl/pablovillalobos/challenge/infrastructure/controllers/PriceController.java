package cl.pablovillalobos.challenge.infrastructure.controllers;

import cl.pablovillalobos.challenge.application.services.PriceService;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;
import cl.pablovillalobos.challenge.infrastructure.exceptions.BrandNotFoundException;
import cl.pablovillalobos.challenge.infrastructure.exceptions.DataAccessException;
import cl.pablovillalobos.challenge.infrastructure.exceptions.PriceNotFoundException;
import cl.pablovillalobos.challenge.infrastructure.exceptions.ProductNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("ConstantConditions")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/public/price")
@Slf4j
public class PriceController {
    private final PriceService priceService;

    @PostMapping("/request")
    public ResponseEntity<?> requestPrice(@Valid @RequestBody PriceRequestDto dto, BindingResult result) {
        log.info("validating PriceRequestDto");
        if (result.hasErrors()) {
            log.warn("PriceRequestDto has invalid field");
            List<String> errors = result.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }

        log.info("dto received on PriceController");
        try {
            return ResponseEntity.ok().body(priceService.foundPrice(dto));
        } catch (DataAccessException | BrandNotFoundException | ProductNotFoundException d) {
            return ResponseEntity.badRequest().body(d.getMessage());
        } catch (PriceNotFoundException e) {
            return ResponseEntity.noContent().build();
        }

    }
}
