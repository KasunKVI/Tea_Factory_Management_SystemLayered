package lk.ijse.pos.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class CartDTO {

    private String id;
    private String type;
    private Integer qty;
}
