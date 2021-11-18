package syso.syso.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.IdentityHashMap;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Column(nullable = false)
    private String context;

}
