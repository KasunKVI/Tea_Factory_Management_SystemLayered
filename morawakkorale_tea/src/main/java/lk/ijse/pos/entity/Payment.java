package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Payment {

    private Integer pay_id;
    private Integer rate;
    private String type;
    private Integer value;
    private String description;
    private Integer supp_id;
    private Integer trp_id;

}
