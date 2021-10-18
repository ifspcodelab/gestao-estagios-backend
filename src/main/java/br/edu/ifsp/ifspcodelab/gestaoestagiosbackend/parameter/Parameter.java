package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "parameters")
@NoArgsConstructor
@Getter
@Setter
public class Parameter {
    @Id
    private UUID id;

    private String requiredOrNot;
    private String projectEquivalence;
    private String professionalEnjoyment;
    private Integer advisorRequestDeadline;

    public Parameter(String requiredOrNot, String projectEquivalence, String professionalEnjoyment, Integer advisorRequestDeadline) {
        this.id = UUID.randomUUID();
        this.requiredOrNot = requiredOrNot;
        this.projectEquivalence = projectEquivalence;
        this.professionalEnjoyment = professionalEnjoyment;
        this.advisorRequestDeadline = advisorRequestDeadline;
    }
}
