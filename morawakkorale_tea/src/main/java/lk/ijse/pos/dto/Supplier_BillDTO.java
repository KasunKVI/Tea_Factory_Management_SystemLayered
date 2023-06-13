package lk.ijse.pos.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Supplier_BillDTO {

    private Integer sup_id;
    private Integer value;
    private Integer bag_count;
    private Double pay_id;
}
