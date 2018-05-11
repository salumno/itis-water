package ru.kpfu.itis.water.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeUpdateForm {
    private String surname;
    private String name;
    private String patro;
    private String email;
    private Long departmentId;
    private String inn;
    private Integer salary;
    private String comment;
    private String role;
    private String status;
}
