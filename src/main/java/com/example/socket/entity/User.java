package com.example.socket.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class User {

    @GeneratedValue
    @Id
    private Long Id;


    @Column
    private String username;

    @Column
    private String password;

}
