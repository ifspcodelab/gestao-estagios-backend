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

    private String stageMessageRequiredOrNot;
    private String projectEquivalenceMessage;
    private String professionalValidationMessage;
    private Integer advisorRequestDeadline;

    public Parameter(String stageMessageRequiredOrNot, String projectEquivalenceMessage, String professionalValidationMessage, Integer advisorRequestDeadline) {
        this.id = UUID.randomUUID();
        this.stageMessageRequiredOrNot = stageMessageRequiredOrNot;
        this.projectEquivalenceMessage = projectEquivalenceMessage;
        this.professionalValidationMessage = professionalValidationMessage;
        this.advisorRequestDeadline = advisorRequestDeadline;
    }
}