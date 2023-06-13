package lk.ijse.pos.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SupplierDTO {

    private Integer id;
    private String name;
    private String contact;
    private Date reg_date;
    private String address;
    private String status;

}
