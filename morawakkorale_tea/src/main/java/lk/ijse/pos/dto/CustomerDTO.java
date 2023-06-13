package lk.ijse.pos.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CustomerDTO {

    private String id;
    private String name;
    private String origin;
    private String contact;
    private String employee_id;

}
