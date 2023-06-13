package lk.ijse.pos.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Order_PaymentDTO {

    private String order_id;
    private LocalDate date;
    private Integer total;
    private String customer;
}
