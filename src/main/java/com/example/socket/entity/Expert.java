package com.example.socket.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class Expert {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long userId;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String location;

    @Column
    private String paymentMethod;

    @Column
    private String bankAccount;

    @Column
    private Integer career;

    @Column
    private String detail;
}