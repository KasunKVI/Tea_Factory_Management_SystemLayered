package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Stock {

    private String stock_id;
    private LocalDate date;
    private Integer value;
    private Integer transporter_id;
    private String status;

}
