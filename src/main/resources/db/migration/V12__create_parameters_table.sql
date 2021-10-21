CREATE TABLE parameters(
     id UUID NOT NULL,
     internship_required_or_not_message BYTEA NOT NULL,
     project_equivalence_message BYTEA NOT NULL,
     professional_validation_message BYTEA NOT NULL,
     advisor_request_deadline INT NOT NULL
);
INSERT INTO parameters (
     id,
     internship_required_or_not_message,
     project_equivalence_message,
     professional_validation_message,
     advisor_request_deadline
)
VALUES (
    '82d41fb0-b896-4b44-b6e6-3ac9d1726f83',
    'mensagem para estágio obrigatório ou não obrigatório',
    'mensagem para equiparação de projeto institucional',
    'mensagem para aproveitamento profissional',
     10
);