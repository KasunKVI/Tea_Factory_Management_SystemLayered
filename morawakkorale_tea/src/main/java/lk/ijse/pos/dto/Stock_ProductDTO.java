package lk.ijse.pos.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Stock_ProductDTO {

    private String product_id;
    private String stock_id;
    private Integer leaf_value;

}
