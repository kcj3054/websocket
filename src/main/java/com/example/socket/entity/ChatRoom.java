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
public class ChatRoom {

    @GeneratedValue @Id
    private Long Id;

    @Column
    private String username;
}
