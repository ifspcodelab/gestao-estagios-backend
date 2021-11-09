CREATE TABLE parameters(
     id UUID NOT NULL,
     internship_required_or_not_message TEXT NOT NULL,
     project_equivalence_message TEXT NOT NULL,
     professional_validation_message TEXT NOT NULL,
     advisor_request_deadline INT NOT NULL,
     deadline_evaluation_activity_plan INT NOT NULL,
     activity_plan_link TEXT NOT NULL,
     deadline_submission_monthly_draft INT NOT NULL,
     deadline_evaluation_monthly_draft INT NOT NULL,
     evaluation_period_monthly_report INT NOT NULL
);
INSERT INTO parameters (
     id,
     internship_required_or_not_message,
     project_equivalence_message,
     professional_validation_message,
     advisor_request_deadline,
     deadline_evaluation_activity_plan,
     activity_plan_link,
     deadline_submission_monthly_draft,
     deadline_evaluation_monthly_draft,
     evaluation_period_monthly_report
)
VALUES (
    '82d41fb0-b896-4b44-b6e6-3ac9d1726f83',
    'mensagem para estágio obrigatório ou não obrigatório',
    'mensagem para equiparação de projeto institucional',
    'mensagem para aproveitamento profissional',
     10,
     7,
     'https://spo.ifsp.edu.br/menu/196-in%C3%ADcio,-aditamento-e-acompanhamento-do-est%C3%A1gio-obrigat%C3%B3rio-ou-n%C3%A3o-obrigat%C3%B3rio',
     1,
     30,
     30
);