package syso.syso.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int count;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;

    public static Center createCenter(String title, String content, Member member){
        Center center = new Center();
        center.member = member;
        center.title = title;
        center.content = content;
        return center;
    }
}
