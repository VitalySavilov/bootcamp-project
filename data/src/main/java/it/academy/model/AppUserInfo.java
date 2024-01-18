package it.academy.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user_info")
public class AppUserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INFO_ID")
    private Long id;
    @Column(name = "INFO_U_FIRSTNAME")
    private String firstname;
    @Column(name = "INFO_U_LASTNAME")
    private String lastname;
    @Column(name = "INFO_U_PATRONYMIC")
    private String patronymic;
    @Column(name = "INFO_U_EMAIL")
    private String email;
}
