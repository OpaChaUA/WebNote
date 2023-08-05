package webnotes.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long id;

    @Column
    private String title;

    @Column

    private String content;
    @Column
    private String access;

    @Column(name = "user_id")
    private int userId;

    @Generated
    @Column(name = "created_at")
    private Timestamp createdAt;
}
