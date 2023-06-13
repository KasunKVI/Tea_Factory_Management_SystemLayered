package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class OrderDTO {

    private String order_id;
    private LocalDate date;
    private Integer total;
    private String customer_id;
    private Integer payment_id;
}
