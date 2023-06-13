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

public class Supplier_Stock {

    private Integer sup_id;
    private String stock_id;
    private Integer value;
    private Integer bag_count;
    private LocalDate date;
    private String status;

}
