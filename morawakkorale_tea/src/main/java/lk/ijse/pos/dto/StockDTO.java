package lk.ijse.pos.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class StockDTO {

    private String stock_id;
    private LocalDate date;
    private Integer value;
    private Integer transporter_id;

}
