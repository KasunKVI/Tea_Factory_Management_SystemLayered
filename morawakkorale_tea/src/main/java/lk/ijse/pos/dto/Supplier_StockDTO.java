package lk.ijse.pos.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Supplier_StockDTO {

    private Integer sup_id;
    private String stock_id;
    private Integer value;
    private Integer bag_count;
    private String pay_id;
    private LocalDate date;


}
