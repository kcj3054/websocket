package com.example.socket.entity;


//<!--    채팅을 메시지를 보낸 회원ID, 메시지, 날짜 ASC-->
//<!--    채팅 했던 내역을 뿌려준다.-->

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class ChatRecord {

    @GeneratedValue
    @javax.persistence.Id
    private Long Id;

    @Column
    private String username;

    @Column
    private String message;

    @Column
    private Date date;

}
