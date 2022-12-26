package schedule.demo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "calendar",uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20)", name = "id")
    private Long id;
    @Column(columnDefinition = "Time")
    private String starttime;//checked
    @Column(columnDefinition = "Time")
    private String endtime;//checked
    @Column(columnDefinition = "varchar(255)",name = "location")
    private String location;//checked
    @Column(columnDefinition = "varchar(255)",name = "owner")
    private String owner;//checked
    @Column(columnDefinition = "varchar(255)", name = "title")
    private String title;//checked
    @Column(columnDefinition = "tinytext")
    private String shortmsg;//checked
    @Column(columnDefinition = "text")
    private String fullmsg;//checked
    @Column(columnDefinition = "varchar(255)")
    private String receiver;//checked
    @Column(columnDefinition = "varchar(50)", name = "type")
    private String type;//checked
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createddate;//checked
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateddate;//checked
}