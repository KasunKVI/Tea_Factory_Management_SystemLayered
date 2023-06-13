package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Transporter {

    private Integer tp_id;
    private String name;
    private String contact;
    private String route;
    private String address;

}
