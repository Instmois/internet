package com.example.springboot;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Entity
@Table(name = "users", schema = "public")
@Component
public class AutoEntity {
        @Id
        //@SequenceGenerator(name = "user_seq", sequenceName = "user_user_id_seq")
       // @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "id_tech")
        private Integer id;
        @Column(name = "model")
        private String model;
        @Column(name = "brand")
        private String brand;
}
