CREATE TABLE parameters(
     id UUID NOT NULL,
     requiredOrNot VARCHAR NOT NULL,
     projectEquivalence VARCHAR NOT NULL,
     professionalEnjoyment VARCHAR NOT NULL,
     advisorRequestDeadline INT NOT NULL,
)
INSERT INTO parameters (
     requiredOrNot,
     projectEquivalence,
     professionalEnjoyment,
     advisorRequestDeadline
)

VALUES (
    'mensagem para estágio obrigatório ou não obrigatório',
    'mensagem para equiparação de projeto institucional',
    'mensagem para aproveitamento profissional',
    10
);