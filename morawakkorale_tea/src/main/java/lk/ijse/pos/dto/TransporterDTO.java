package lk.ijse.pos.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class TransporterDTO {

    private Integer id;
    private String name;
    private String contact;
    private String route;
    private String address;

}
