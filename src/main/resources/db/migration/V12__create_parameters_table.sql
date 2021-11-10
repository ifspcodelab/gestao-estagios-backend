CREATE TABLE parameters(
     id UUID NOT NULL,
     internship_required_or_not_message TEXT NOT NULL,
     project_equivalence_message TEXT NOT NULL,
     professional_validation_message TEXT NOT NULL,
     advisor_request_deadline INT NOT NULL,
     activity_plan_appraisal_deadline INT NOT NULL,
     activity_plan_link TEXT NOT NULL,
     activity_plan_file_size_megabytes INT NOT NULL,
     monthly_report_file_size_megabytes INT NOT NULL,
     monthly_report_draft_submission_deadline_months INT NOT NULL,
     monthly_report_draft_appraisal_deadline_days INT NOT NULL,
     monthly_report_appraisal_deadline_days INT NOT NULL
);
INSERT INTO parameters (
     id,
     internship_required_or_not_message,
     project_equivalence_message,
     professional_validation_message,
     advisor_request_deadline,
     activity_plan_appraisal_deadline,
     activity_plan_link,
     activity_plan_file_size_megabytes,
     monthly_report_file_size_megabytes,
     monthly_report_draft_submission_deadline_months,
     monthly_report_draft_appraisal_deadline_days,
     monthly_report_appraisal_deadline_days
)
VALUES (
    '82d41fb0-b896-4b44-b6e6-3ac9d1726f83',
    'mensagem para estágio obrigatório ou não obrigatório',
    'mensagem para equiparação de projeto institucional',
    'mensagem para aproveitamento profissional',
     10,
     7,
     'https://spo.ifsp.edu.br/images/phocadownload/DOCUMENTOS_ESTAGIOS/INICIO_ESTAGIO/2020/ANEXO_G_PLANO_DE_ATIVIDADES_vers%C3%A3o_agos.2020.01.doc',
     1,
     1,
     1,
     30,
     30
);