package syso.syso.entity;

import lombok.Getter;
import lombok.Setter;
import syso.syso.constant.Grade;
import syso.syso.constant.Role;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Member extends Timestamped{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 20, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nicName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    private int point;

    private Role role;

    private Grade grade;
}
