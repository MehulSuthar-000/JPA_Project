package com.mehulsuthar.JPA_Project.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@Table(
//        name = "AUTHOR_TBL"
//)
public class Author {

    @Id
    @GeneratedValue
//            (
//            strategy = GenerationType.TABLE,
//            generator = "author_id_gen"
//    )
//    @SequenceGenerator(
//            name = "author_sequence",
//            sequenceName = "author_sequence",
//            allocationSize = 22,
//            initialValue = 60
//    )
//    @TableGenerator(
//            name = "author_id_gen",
//            table = "id_generator",
//            pkColumnName = "id_name",
//            valueColumnName = "id_value",
//            allocationSize = 22
//    )
    private Integer id;

//    @Column(
//            name = "f_name",
//            length = 35
//    )
    private String firstName;

    private String lastName;

    @Column(
            unique = true,
            nullable = false
    )
    private String email;

    private int age;

    @ManyToMany(mappedBy = "authors")
    private List<Course> courses;
}
