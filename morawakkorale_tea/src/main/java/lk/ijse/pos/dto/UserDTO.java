package lk.ijse.pos.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class UserDTO {

    private String id;
    private String password;
    private  String contact;
    private String name;
    private String email;
    private String position;

}
