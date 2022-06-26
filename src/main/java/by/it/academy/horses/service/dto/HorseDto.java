package by.it.academy.horses.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorseDto implements Serializable {
    private Long id;
    private String type;
    private Integer age;
    private Double price;
}
