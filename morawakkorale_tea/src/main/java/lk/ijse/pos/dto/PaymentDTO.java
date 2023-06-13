package lk.ijse.pos.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class PaymentDTO {

    private Integer id;
    private Integer rate;
    private String type;
    private Double value;
    private String description;
    private Integer supp_id;
    private Integer trp_id;

}
