package by.it.academy.horses.repository;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "horses")
public class Horse {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "typ")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String type;
    @Column(name = "age")
    @Min(value = 0, message = "Age should be greater than 0")
    private Integer age;
    @Column(name = "price")
    @Min(value = 0, message = "Age should be greater than 0")
    private Double price;
}
