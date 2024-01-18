package it.academy.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_user")
public class AppUser {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "INFO_ID")
    private AppUserInfo appUserInfo;
    @ManyToOne
    @JoinColumn(name = "R_ID")
    private AppUserRole role;
}
