package kz.kasya.cheena.AttendMe.models.dtos;

import kz.kasya.cheena.AttendMe.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DressDto extends BaseDto {

    private String name;

    private String description;

    private Integer price;

}
