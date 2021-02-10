package com.myfilm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rateId;

    @ManyToOne
    @JoinColumn(name = "movieID")
    private Movie movie;
    private int rate;
    private String description;


}
