package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Supplier {

    private Integer sup_id;
    private String name;
    private String contact;
    private Date reg_date;
    private String address;
    private String status;

}
