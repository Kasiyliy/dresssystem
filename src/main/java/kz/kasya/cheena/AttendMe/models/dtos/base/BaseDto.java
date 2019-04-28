package kz.kasya.cheena.AttendMe.models.dtos.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {

    private Long id;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

}
