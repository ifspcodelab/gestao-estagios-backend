package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReportStatus {
    DRAFT_PENDING("DRAFT_PENDING", "Rascunho do relatório mensal pendente"),
    DRAFT_SENT("DRAFT_SENT", "Rascunho do mensal enviado"),
    FINAL_PENDING("FINAL_PENDING", "Relatório mensal pendente"),
    FINAL_SENT("FINAL_SENT", "Relatório mensal enviado"),
    FINAL_ACCEPTED("FINAL_ACCEPTED", "Relatório mensal aceito");

    private String name;
    private String description;
}
