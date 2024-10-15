package cl.pablovillalobos.challenge.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "brand")
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long brandEntityId;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "brandEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PriceEntity> prices;
}
