package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import java.util.List;

public class ProblemDetail {
    private String title;
    private List<Violation> violations;

    public ProblemDetail(String title, List<Violation> violations) {
        this.title = title;
        this.violations = violations;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }
}