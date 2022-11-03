package com.seminario.store.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "sender")
    private String sender;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "to_user_email")
    private String toUserEmail;

    @Column(name = "notification_type")
    private String notificationType;
}
