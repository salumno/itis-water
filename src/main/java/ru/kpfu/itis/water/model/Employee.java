package ru.kpfu.itis.water.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surname;

    private String name;

    private String patro;

    private String email;

    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String inn;

    private Integer salary;

    private String comment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_data_id")
    private UserData userData;
}
