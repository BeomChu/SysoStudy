package syso.syso.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Timestamped {

    @CreatedDate
    private LocalDate localDate;

    @LastModifiedDate
    private LocalDate modifiedAt;
}
