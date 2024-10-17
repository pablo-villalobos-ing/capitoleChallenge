package cl.pablovillalobos.challenge.infrastructure.controllers;

import cl.pablovillalobos.challenge.application.services.PriceService;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
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
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        log.info("dto received on PriceController");
        var response = priceService.foundPrice(dto);
        if (response.isPresent()) {
            return ResponseEntity.ok().body(response.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
}
