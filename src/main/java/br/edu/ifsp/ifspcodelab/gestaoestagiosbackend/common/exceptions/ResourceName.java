package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResourceName {
    USER("User"),
    ADVISOR("Advisor"),
    STUDENT("Student"),
    CITY("City"),
    CAMPUS("Campus"),
    DEPARTMENT("Department"),
    COURSE("Course"),
    CURRICULUM("Curriculum"),
    ADVISOR_REQUEST("Advisor Request"),
    INTERNSHIP("Internship"),
    ACTIVITY_PLAN("Activity Plan"),
    MONTHLY_REPORT("Monthly Report"),
    DRAFT_MONTHLY_REPORT_SUBMISSION("Draft Monthly Report Submission"),
    FINAL_MONTHLY_REPORT_SUBMISSION("Final Monthly Report Submission");

    private String name;
}
