package lk.ijse.pos.dto;

import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class ProductDTO {

    private String id;
    private LocalDate made_date;
    private Integer qty_on_hand;
    private String type;
    private Double unit_price;

}
