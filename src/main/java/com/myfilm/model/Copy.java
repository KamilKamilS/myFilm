package com.myfilm.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long copyId;

    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;

    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

}
