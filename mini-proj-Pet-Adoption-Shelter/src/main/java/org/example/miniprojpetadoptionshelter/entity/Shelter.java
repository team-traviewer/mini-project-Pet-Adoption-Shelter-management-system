package org.example.miniprojpetadoptionshelter.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shelters")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shelter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;


}
