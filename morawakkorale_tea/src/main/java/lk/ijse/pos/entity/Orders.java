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

public class Orders {

    private String order_id;
    private LocalDate date;
    private Integer total;
    private String customer_id;
    private Integer payment_id;

}
