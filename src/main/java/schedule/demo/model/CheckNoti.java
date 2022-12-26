package schedule.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "check_noti",uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CheckNoti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean noti_status;
}
