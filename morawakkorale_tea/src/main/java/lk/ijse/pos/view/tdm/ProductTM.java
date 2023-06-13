package lk.ijse.pos.view.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ProductTM {

    private String id;
    private String made_date;
    private String stock_id;
    private Integer leaf_value;
    private String type;
    private Integer quantity;

}
