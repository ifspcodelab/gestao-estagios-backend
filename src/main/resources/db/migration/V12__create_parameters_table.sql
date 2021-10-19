CREATE TABLE parameters(
     id UUID NOT NULL,
     required_or_not VARCHAR NOT NULL,
     project_equivalence VARCHAR NOT NULL,
     professional_enjoyment VARCHAR NOT NULL,
     advisor_request_deadline INT NOT NULL
);
INSERT INTO parameters (
     id,
     required_or_not,
     project_equivalence,
     professional_enjoyment,
     advisor_request_deadline
)
VALUES (
    '82d41fb0-b896-4b44-b6e6-3ac9d1726f83',
    'mensagem para estágio obrigatório ou não obrigatório',
    'mensagem para equiparação de projeto institucional',
    'mensagem para aproveitamento profissional',
     10
);