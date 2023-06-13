package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Product {

    private String product_id;
    private LocalDate made_date;
    private Integer qty_on_hand;
    private String type;
    private Double unit_price;

}
