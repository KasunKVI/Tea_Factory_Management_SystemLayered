package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Order_ProductDTO {

    private String id_product;
    private String id_order;
    private String type;
    private Integer qty;
}
