package kz.kasya.cheena.AttendMe.models.entities;

import kz.kasya.cheena.AttendMe.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "dresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dress extends AuditModel {

    private String name;

    private String description;

    private Integer price;
}
