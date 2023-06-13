package lk.ijse.pos.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class TransporterBillDTO {

    private Integer tp_id;
    private String route;
    private Integer value;
    private Double payment;

}
