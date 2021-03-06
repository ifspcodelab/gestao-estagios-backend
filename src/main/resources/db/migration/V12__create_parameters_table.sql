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
    monthly_report_appraisal_deadline_days INT NOT NULL,
    initial_dispatch_html TEXT NOT NULL,
    final_dispatch_html TEXT NOT NULL
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
    monthly_report_appraisal_deadline_days,
    initial_dispatch_html,
    final_dispatch_html
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
    30,
    '<p style="text-align: justify;">
    <span style="font-size:12pt; font-family:&quot;Arial&quot;,sans-serif;">
        &Agrave; Coordenadoria de Integra&ccedil;&atilde;o Escola-Empresa - CEE-SPO
    </span>
</p>

<p style="text-align: justify;">&nbsp;</p>

<p style=" text-align: justify;">
<span style="color:#000000;font-family: Arial, Sans Serif; text-align: justify;">
    <span style="font-size:11pt">
        <strong>ASSUNTO:</strong>
        Est&aacute;gio Supervisonado
    </span>
</span>
</p>

<p style="text-align: justify;">
<span style="color:#000000;font-family: Arial, Sans Serif; text-align: justify;">
    <span style="font-size:11pt">
        <strong>INTERESSADO: </strong>
    </span>
</span>
<span style="color:null;">
    <span style="font-family: Arial, Sans Serif; text-align: justify;">
        <span data-th-text="${internship.advisorRequest.student.user.name}" style="font-size:11pt"></span>
    </span>
</span>
<span style="color:#000000;font-family: Arial, Sans Serif; text-align: justify;">
    <span style="font-size:11pt"></span>
</span>
<span style="color:null;">
    <span style="font-family: Arial, Sans Serif; text-align: justify;">
        (<span data-th-text="${internship.advisorRequest.student.user.registration}" style="font-size:11pt"></span>)
    </span>
</span>
<span style="color:#000000;font-family: Arial, Sans Serif; text-align: justify;">
    <span style="font-size:11pt"></span>
</span>
</p>

<p style=" text-align: justify;">
<span style="color:#000000;font-family: Arial, Sans Serif; text-align: justify;">
    <span style="font-size:11pt">
        <strong>OBJETO: </strong>
        Despacho sobre est&aacute;gio
    </span>
</span>
</p>

<p style="margin-left: 40px; text-align: justify;font-family: Arial, Sans Serif;">&nbsp;</p>

<p  style="margin-left: 40px; text-align: justify; font-family: Arial, Sans Serif;">
    Venho, por meio deste, apresentar &agrave; Vossa Senhoria o DESPACHO a seguir, por mim emitido, que aprova o Plano de Atividades de Est&aacute;gio do(a) estudante
    <span data-th-text="${internship.advisorRequest.student.user.name}"></span> (<span data-th-text="${internship.advisorRequest.student.user.registration}"></span>), regularmente matriculado(a) no Curso <span data-th-text="${internship.advisorRequest.curriculum.course.name}"></span> desta institui&ccedil;&atilde;o. O est&aacute;gio dever&aacute; ser registrado como <span data-th-text="${internship.internshipType.description}"></span>.
</p>

<p style="margin-left: 40px; text-align: justify; font-family: Arial, Sans Serif;">&nbsp;</p>

<div class="WordSection1">
<p align="center" class="MsoNormal" style="text-align:center">
    <span class="eopscxw231936548bcx0">
        <b>
            <span style="font-size:14.0pt;font-family:&quot;Arial&quot;,sans-serif">
                DESPACHO SOBRE EST&Aacute;GIO
            </span>
        </b>
    </span>
</p>

<p>&nbsp;</p>

<div align="center">
    <table border="1"
        cellpadding="0"
        cellspacing="0"
        class="MsoNormalTable"
        style="
            width:100.0%;
            border-collapse:collapse;
            border:none;
            mso-border-alt:solid windowtext .5pt;
            mso-yfti-tbllook:160;
            mso-padding-alt:0cm 3.5pt 0cm 3.5pt;
            mso-border-insideh:.5pt solid windowtext;
            mso-border-insidev:.5pt solid windowtext" width="100%">
        <tbody>
            <tr style="mso-yfti-irow:0;mso-yfti-firstrow:yes;height:21.4pt">
                <td colspan="2"
                    nowrap="nowrap"
                    style="
                        width:244.45pt;
                        border:solid windowtext 1.0pt;
                        border-right:none;
                        mso-border-top-alt:solid windowtext .5pt;
                        mso-border-left-alt:solid windowtext .5pt;
                        mso-border-bottom-alt:solid windowtext .5pt;
                        padding:0cm 3.5pt 0cm 3.5pt;
                        height:21.4pt"
                    width="326">
                    <p class="MsoNormal">
                        <b>
                            <span
                                style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">Aluno:
                            </span>
                        </b>
                        <span data-th-text="${internship.advisorRequest.student.user.name}" style="color:null;"></span>
                    </p>
                </td>
                <td colspan="6"
                    style="
                        width:244.5pt;
                        border:solid windowtext 1.0pt;
                        border-left:none;
                        mso-border-top-alt:solid windowtext .5pt;
                        mso-border-bottom-alt:solid windowtext .5pt;
                        mso-border-right-alt:solid windowtext .5pt;
                        padding:0cm 3.5pt 0cm 3.5pt;
                        height:21.4pt"
                    width="326">
                    <p align="right" class="MsoNormal" style="text-align:right">
                        <b>
                            <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">Matr&iacute;cula n&ordm;: </span>
                        </b>
                        <span style="color:null;">
                            <span data-th-text="${internship.advisorRequest.student.user.registration}" style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif"></span>
                        </span>
                    </p>
                </td>
            </tr>
            <tr style="mso-yfti-irow:1;height:21.4pt">
                <td colspan="8"
                    nowrap="nowrap"
                    style="
                        width:488.95pt;
                        border:solid windowtext 1.0pt;
                        border-top:none;
                        mso-border-top-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;
                        padding:0cm 3.5pt 0cm 3.5pt;
                        height:21.4pt"
                    width="652">
                    <p class="MsoNormal">
                        <b>
                            <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">Nome da Unidade Concedente: </span>
                        </b>
                        <span data-th-text="${activityPlan.companyName}"></span>
                    </p>
                </td>
            </tr>
            <tr style="mso-yfti-irow:2;height:23.75pt">
                <td nowrap="nowrap"
                    style="
                        width:140.05pt;
                        border:solid windowtext 1.0pt;
                        border-top:none;
                        mso-border-top-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;
                        background:#E7E6E6;
                        padding:0cm 3.5pt 0cm 3.5pt;
                        height:23.75pt"
                    width="187">
                    <p align="center" class="MsoNormal" style="text-align:center">
                        <b>
                            <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">DOCUMENTO DE EST&Aacute;GIO</span>
                        </b>
                    </p>
                </td>
                <td colspan="2"
                    nowrap="nowrap"
                    style="
                        width:107.25pt;
                        border-top:none;
                        border-left:none;
                        border-bottom:solid windowtext 1.0pt;
                        border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;
                        mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;
                        background:#E7E6E6;
                        padding:0cm 3.5pt 0cm 3.5pt;
                        height:23.75pt"
                    width="143">
                    <p align="center" class="MsoNormal" style="text-align:center">
                        <b>
                            <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">PER&Iacute;ODO DO DOCUMENTO</span>
                        </b>
                    </p>
                </td>
                <td colspan="2"
                    nowrap="nowrap"
                    style="
                        width:87.55pt;
                        border-top:none;
                        border-left:none;
                        border-bottom:solid windowtext 1.0pt;
                        border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;
                        mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;
                        background:#E7E6E6;
                        padding:0cm 3.5pt 0cm 3.5pt;
                        height:23.75pt"
                    width="117">
                    <p align="center" class="MsoNormal" style="text-align:center">
                        <b>
                            <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">VALIDADO?</span>
                        </b>
                    </p>
                </td>
                <td colspan="2"
                    style="
                        width:91.65pt;
                        border-top:none;
                        border-left:none;
                        border-bottom:solid windowtext 1.0pt;
                        border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;
                        mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;
                        background:#E7E6E6;
                        padding:0cm 3.5pt 0cm 3.5pt;
                        height:23.75pt"
                    width="122">
                    <p align="center" class="MsoNormal" style="text-align:center">
                        <b>
                            <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">EST&Aacute;GIO OBRIGAT&Oacute;RIO?</span>
                        </b>
                    </p>
                </td>
                <td style="
                        width:62.45pt;
                        border-top:none;
                        border-left:none;
                        border-bottom:solid windowtext 1.0pt;
                        border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;
                        mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;
                        background:#E7E6E6;
                        padding:0cm 3.5pt 0cm 3.5pt;
                        height:23.75pt"
                    width="83">
                    <p align="center" class="MsoNormal" style="text-align:center">
                        <b>
                            <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">TOTAL DE HORAS V&Aacute;LIDAS PARA REGISTRO</span>
                        </b>
                    </p>
                </td>
            </tr>
            <tr style="mso-yfti-irow:3;mso-yfti-lastrow:yes;height:14.25pt">
                <td nowrap="nowrap"
                    style="
                        width:140.05pt;
                        border:solid windowtext 1.0pt;
                        border-top:none;
                        mso-border-top-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;
                        padding:0cm 3.5pt 0cm 3.5pt;
                        height:14.25pt"
                    width="187">
                    <p align="center" class="MsoNormal" style="text-align:center">
                        <span style="font-size:9.0pt">
                            <span style="font-family:&quot;Arial&quot;,sans-serif">
                                <span style="color:black">Plano de Atividades de Est&aacute;gio</span>
                            </span>
                        </span>
                    </p>
                </td>
                <td colspan="2"
                    nowrap="nowrap"
                    style="
                        width:107.25pt;
                        border-top:none;
                        border-left:none;
                        border-bottom:solid windowtext 1.0pt;
                        border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;
                        mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;
                        padding:0cm 3.5pt 0cm 3.5pt;
                        height:14.25pt"
                    width="143">
                    <p class="MsoNormal" style="text-align: center;">
                        <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif">
                            <span data-th-text="${#temporals.format(activityPlan.internshipStartDate, ''dd/MM/yyyy'')}"></span> a <span data-th-text="${#temporals.format(activityPlan.internshipEndDate, ''dd/MM/yyyy'')}"></span>
                        </span>
                    </p>
                </td>
                <td nowrap="nowrap"
                    style="
                        width:44.85pt;
                        border-top:none;
                        border-left:none;
                        border-bottom:solid windowtext 1.0pt;
                        border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;
                        mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;
                        padding:0cm 3.5pt 0cm 3.5pt;
                        height:14.25pt"
                    width="60">
                    <p align="center" class="MsoNormal" style="text-align:center">
                        <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">(X) sim</span>
                    </p>
                </td>
                <td nowrap="nowrap"
                    style="
                        width:42.7pt;
                        border-top:none;
                        border-left:none;
                        border-bottom:solid windowtext 1.0pt;
                        border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;
                        mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;
                        padding:0cm 3.5pt 0cm 3.5pt;
                        height:14.25pt"
                    width="57">
                    <p align="center" class="MsoNormal" style="text-align:center">
                        <span class="GramE">
                            <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">(&nbsp;)</span>
                        </span>
                        <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">n&atilde;o</span>
                    </p>
                </td>
                <td nowrap="nowrap"
                    style="
                        width:43.95pt;
                        border-top:none;
                        border-left:none;
                        border-bottom:solid windowtext 1.0pt;
                        border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;
                        mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;
                        padding:0cm 3.5pt 0cm 3.5pt;
                        height:14.25pt"
                    width="59">
                    <p align="center" class="MsoNormal" style="text-align:center">
                        <span class="GramE">
                            <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">(<span data-th-text="${internship.internshipType.name == ''REQUIRED''} ? ''X'' : '' ''"></span>)</span>
                        </span>
                        <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">sim</span>
                    </p>
                </td>
                <td style="
                        width:47.7pt;
                        border-top:none;
                        border-left:none;border-bottom:solid windowtext 1.0pt;
                        border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;
                        mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;
                        padding:0cm 3.5pt 0cm 3.5pt;
                        height:14.25pt"
                    width="64">
                    <p align="center" class="MsoNormal" style="text-align:center">
                        <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">(<span data-th-text="${internship.internshipType.name == ''NOT_REQUIRED''} ? ''X'' : '' ''"></span>) n&atilde;o</span>
                    </p>
                </td>
                <td style="
                        width:62.45pt;
                        border-top:none;
                        border-left:none;
                        border-bottom:solid windowtext 1.0pt;
                        border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;
                        mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;
                        padding:0cm 3.5pt 0cm 3.5pt;
                        height:14.25pt"
                    width="83">
                    <p align="center" class="MsoNormal" style="text-align:center">-</p>
                </td>
            </tr>
            <tr height="0">
                <td style="border:none" width="203">&nbsp;</td>
                <td style="border:none" width="151">&nbsp;</td>
                <td style="border:none" width="4">&nbsp;</td>
                <td style="border:none" width="65">&nbsp;</td>
                <td style="border:none" width="62">&nbsp;</td>
                <td style="border:none" width="64">&nbsp;</td>
                <td style="border:none" width="69">&nbsp;</td>
                <td style="border:none" width="90">&nbsp;</td>
            </tr>
            <!--[endif]-->
        </tbody>
    </table>
</div>

<p align="center" class="MsoNormal" style="margin-left:1.0cm;text-align:center;text-indent:14.2pt">&nbsp;</p>

<p class="MsoNormal">
    <b>
        <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">Parecer:</span>
    </b>
</p>

<div style="mso-element:para-border-div;border:solid windowtext 1.0pt;mso-border-alt:solid windowtext .5pt;padding:1.0pt 4.0pt 1.0pt 4.0pt;background:#F2F2F2">
    <p class="MsoNormal"
        style="
            background:#F2F2F2;
            border:none;
            mso-border-alt:solid windowtext .5pt;
            padding:0cm;
            mso-padding-alt:1.0pt 4.0pt 1.0pt 4.0pt">
        O plano de atividades apresentado se encontra adequado&nbsp;para Est&aacute;gio Supervisionado
        <span data-th-text="${internship.internshipType.description}"></span>, no contexto do Curso <span data-th-text="${internship.advisorRequest.curriculum.course.name}"></span> desta institui&ccedil;&atilde;o, motivo pelo qual eu o aprovo enquanto professor orientador de est&aacute;gio do referido curso.
    </p>

</div>

<p align="right" class="paragraphscxw76620377bcx0" style="margin:0cm;text-align:right;vertical-align:baseline">&nbsp;</p>

<p align="right" class="paragraphscxw76620377bcx0" style="margin:0cm;text-align:right;vertical-align:baseline">&nbsp;</p>

<p align="right" class="paragraphscxw76620377bcx0" style="margin:0cm;text-align:right;vertical-align:baseline">
    <span class="normaltextrunscxw76620377bcx0">
        <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">S&atilde;o Paulo,&nbsp;</span>
    </span>
    <span class="normaltextrunscxw76620377bcx0">
        <span style="
            font-size:11.0pt;
            font-family:&quot;Arial&quot;,sans-serif;
            mso-fareast-font-family:&quot;Arial Unicode MS&quot;;
            color:black;
            background:#E1E3E6">
            <span data-th-text="${#calendars.format(#calendars.createNow(), ''dd'')}"></span>
        </span>
    </span>
    <span class="normaltextrunscxw76620377bcx0">
        <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">
            &nbsp;de&nbsp;
        </span>
    </span>
    <span class="normaltextrunscxw76620377bcx0">
        <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;mso-fareast-font-family:&quot;Arial Unicode MS&quot;;color:black;background:#E1E3E6">
            <span data-th-text="${#calendars.format(#calendars.createNow(), ''MMMM'')}"></span>
        </span>
    </span>
    <span class="normaltextrunscxw76620377bcx0">
        <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">
            &nbsp;de
        </span>
    </span>
    <span class="normaltextrunscxw76620377bcx0">
        <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;mso-fareast-font-family:&quot;Arial Unicode MS&quot;;color:black;background:#E1E3E6">
            &nbsp;<span data-th-text="${#calendars.format(#calendars.createNow(), ''yyyy'')}"></span>
        </span>
    </span>
    <span class="eopscxw76620377bcx0">
        <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">
            .
        </span>
    </span>
</p>

<p class="MsoNormal" style="text-align:justify">&nbsp;</p>

<div align="center">
    <table border="0"
        cellpadding="0"
        cellspacing="6"
        class="MsoTableGrid"
        style="
            width:100.0%;
            mso-cellspacing:4.2pt;
            border:none;
            mso-yfti-tbllook:1184;
            mso-padding-alt:0cm 0cm 0cm 0cm;
            mso-border-insideh:none;
            mso-border-insidev:none"
        width="100%">
        <tbody>
            <tr style="mso-yfti-irow:0;mso-yfti-firstrow:yes">
                <td style="width:6.0cm;padding:0cm 0cm 0cm 0cm" valign="top" width="227">
                    <p align="right" class="MsoNormal" style="text-align:right">
                        <span class="normaltextrunscxw246734469bcx0">
                            <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">
                                Professor Orientador dos Cursos:
                            </span>
                        </span>
                    </p>
                </td>
                <td style="width:11.0cm;padding:0cm 0cm 0cm 0cm" valign="top" width="416">
                    <p class="MsoNormal" style="text-align:justify">
                        <span style="font-size:11.0pt">
                            <span style="background:#e1e3e6">
                                <span style="font-family:&quot;Arial&quot;,sans-serif">
                                    <span style="color:black">
                                        <b>
                                             <span data-th-each="c, course : ${internship.advisorRequest.advisor.courses}" data-th-text="!${course.last} ? ${c.name} + '', '' : ${c.name}"></span>
                                        </b>
                                    </span>
                                </span>
                            </span>
                        </span>
                    </p>
                </td>
            </tr>
            <tr style="mso-yfti-irow:1">
                <td style="width:6.0cm;padding:0cm 0cm 0cm 0cm" valign="top" width="227">
                    <p align="right" class="MsoNormal" style="text-align:right">
                        <span class="normaltextrunscxw246734469bcx0">
                            <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">Nome:</span>
                        </span>
                    </p>
                </td>
                <td style="width:11.0cm;padding:0cm 0cm 0cm 0cm" valign="top" width="416">
                    <p class="MsoNormal" style="text-align:justify">
                        <span class="normaltextrunscxw246734469bcx0">
                            <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;mso-fareast-font-family:&quot;Arial Unicode MS&quot;;color:black;background:#E1E3E6">
                                <b data-th-text="${internship.advisorRequest.advisor.user.name}"></b>
                            </span>
                        </span>
                    </p>
                </td>
            </tr>
            <tr style="mso-yfti-irow:2;mso-yfti-lastrow:yes">
                <td style="width:6.0cm;padding:0cm 0cm 0cm 0cm" valign="top" width="227">
                    <p align="right" class="MsoNormal" style="text-align:right">
                        <span class="normaltextrunscxw246734469bcx0">
                            <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">
                                E-mail institucional:
                            </span>
                        </span>
                    </p>
                </td>
                <td style="width:11.0cm;padding:0cm 0cm 0cm 0cm" valign="top" width="416">
                    <p class="MsoNormal" style="text-align:justify">
                        <span class="normaltextrunscxw246734469bcx0">
                            <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;mso-fareast-font-family:&quot;Arial Unicode MS&quot;;color:black;background:#E1E3E6">
                                <b data-th-text="${internship.advisorRequest.advisor.user.email}"></b>
                            </span>
                        </span>
                    </p>
                </td>
            </tr>
        </tbody>
    </table>
</div>
</div>

<p style="text-align:center">&nbsp;</p>',
    ' <p style="text-align: justify;">
    <span style="font-size:12pt; font-family:&quot;Arial&quot;,sans-serif;">
        &Agrave; Coordenadoria de Integra&ccedil;&atilde;o Escola-Empresa - CEE-SPO
    </span>
  </p>

  <p style="text-align: justify;">&nbsp;</p>

  <p style=" text-align: justify;">
    <span style="color:#000000;font-family: Arial, Sans Serif; text-align: justify;">
        <span style="font-size:11pt">
            <strong>ASSUNTO:</strong>
            Est&aacute;gio Supervisonado
        </span>
    </span>
  </p>

  <p style="text-align: justify;">
    <span style="color:#000000;font-family: Arial, Sans Serif; text-align: justify;">
        <span style="font-size:11pt">
            <strong>INTERESSADO: </strong>
        </span>
    </span>
    <span style="color:null;">
        <span style="font-family: Arial, Sans Serif; text-align: justify;">
            <span data-th-text="${internship.advisorRequest.student.user.name}" style="font-size:11pt">$STUDENTNAME</span>
        </span>
    </span>
    <span style="color:#000000;font-family: Arial, Sans Serif; text-align: justify;">
        <span style="font-size:11pt"></span>
    </span>
    <span style="color:null;">
        <span style="font-family: Arial, Sans Serif; text-align: justify;">
            (<span data-th-text="${internship.advisorRequest.student.user.registration}" style="font-size:11pt">$REGISTRATION</span>)
        </span>
    </span>
    <span style="color:#000000;font-family: Arial, Sans Serif; text-align: justify;">
        <span style="font-size:11pt"></span>
    </span>
  </p>

  <p style=" text-align: justify;">
    <span style="color:#000000;font-family: Arial, Sans Serif; text-align: justify;">
        <span style="font-size:11pt">
            <strong>OBJETO: </strong>
            Despacho sobre est&aacute;gio
        </span>
    </span>
  </p>

  <p style="margin-left: 40px; text-align: justify;font-family: Arial, Sans Serif;">&nbsp;</p>

  <p style="margin-left: 40px; text-align: justify; font-family: Arial, Sans Serif;">
    Venho, por meio deste, apresentar &agrave; Vossa Senhoria o DESPACHO a seguir, por mim emitido, que aprova os documentos de finaliza&ccedil;&atilde;o do Est&aacute;gio Supervisionado <span data-th-text="${internship.internshipType.description}">$INTERNSHIPTYPE</span> do(a) estudante
    <span data-th-text="${internship.advisorRequest.student.user.name}">$STUDENTNAME</span> (<span data-th-text="${internship.advisorRequest.student.user.registration}">$REGISTRATION</span>), regularmente matriculado(a) no Curso <span data-th-text="${internship.advisorRequest.curriculum.course.name}">$COURSENAME</span> desta institui&ccedil;&atilde;o. O est&aacute;gio dever&aacute; ser registrado como <span data-th-text="${internship.internshipType.description}">$INTERNSHIPTYPE</span>.
  </p>


  <p style="margin-left: 40px; text-align: justify; font-family: Arial, Sans Serif;">&nbsp;</p>

  <div class="WordSection1">
    <p align="center" class="MsoNormal" style="text-align:center">
        <span class="eopscxw231936548bcx0">
            <b>
                <span style="font-size:14.0pt;font-family:&quot;Arial&quot;,sans-serif">
                    DESPACHO SOBRE EST&Aacute;GIO
                </span>
            </b>
        </span>
    </p>

    <p>&nbsp;</p>

    <div align="center">
        <table border="1"
            cellpadding="0"
            cellspacing="0"
            class="MsoNormalTable"
            style="
                width:100.0%;
                border-collapse:collapse;
                border:none;
                mso-border-alt:solid windowtext .5pt;
                mso-yfti-tbllook:160;
                mso-padding-alt:0cm 3.5pt 0cm 3.5pt;
                mso-border-insideh:.5pt solid windowtext;
                mso-border-insidev:.5pt solid windowtext" width="100%">
            <tbody>
                <tr style="mso-yfti-irow:0;mso-yfti-firstrow:yes;height:21.4pt">
                    <td colspan="2"
                        nowrap="nowrap"
                        style="
                            width:244.45pt;
                            border:solid windowtext 1.0pt;
                            border-right:none;
                            mso-border-top-alt:solid windowtext .5pt;
                            mso-border-left-alt:solid windowtext .5pt;
                            mso-border-bottom-alt:solid windowtext .5pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:21.4pt"
                        width="326">
                        <p class="MsoNormal">
                            <b>
                                <span
                                    style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">Aluno:
                                </span>
                            </b>
                            <span data-th-text="${internship.advisorRequest.student.user.name}" style="color:null;">$STUDENTNAME</span>
                        </p>
                    </td>
                    <td colspan="6"
                        style="
                            width:244.5pt;
                            border:solid windowtext 1.0pt;
                            border-left:none;
                            mso-border-top-alt:solid windowtext .5pt;
                            mso-border-bottom-alt:solid windowtext .5pt;
                            mso-border-right-alt:solid windowtext .5pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:21.4pt"
                        width="326">
                        <p align="right" class="MsoNormal" style="text-align:right">
                            <b>
                                <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">Matr&iacute;cula n&ordm;: </span>
                            </b>
                            <span style="color:null;">
                                <span data-th-text="${internship.advisorRequest.student.user.registration}" style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">$REGISTRATION</span>
                            </span>
                        </p>
                    </td>
                </tr>
                <tr style="mso-yfti-irow:1;height:21.4pt">
                    <td colspan="8"
                        nowrap="nowrap"
                        style="
                            width:488.95pt;
                            border:solid windowtext 1.0pt;
                            border-top:none;
                            mso-border-top-alt:solid windowtext .5pt;
                            mso-border-alt:solid windowtext .5pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:21.4pt"
                        width="652">
                        <p class="MsoNormal">
                            <b>
                                <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">Nome da Unidade Concedente: </span>
                            </b>
                            <span data-th-text="${activityPlans[0].companyName}"></span>
                        </p>
                    </td>
                </tr>
                <tr style="mso-yfti-irow:2;height:23.75pt">
                    <td nowrap="nowrap"
                        style="
                            width:140.05pt;
                            border:solid windowtext 1.0pt;
                            border-top:none;
                            mso-border-top-alt:solid windowtext .5pt;
                            mso-border-alt:solid windowtext .5pt;
                            background:#E7E6E6;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:23.75pt"
                        width="187">
                        <p align="center" class="MsoNormal" style="text-align:center">
                            <b>
                                <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">DOCUMENTO DE EST&Aacute;GIO</span>
                            </b>
                        </p>
                    </td>
                    <td colspan="2"
                        nowrap="nowrap"
                        style="
                            width:107.25pt;
                            border-top:none;
                            border-left:none;
                            border-bottom:solid windowtext 1.0pt;
                            border-right:solid windowtext 1.0pt;
                            mso-border-top-alt:solid windowtext .5pt;
                            mso-border-left-alt:solid windowtext .5pt;
                            mso-border-alt:solid windowtext .5pt;
                            background:#E7E6E6;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:23.75pt"
                        width="143">
                        <p align="center" class="MsoNormal" style="text-align:center">
                            <b>
                                <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">PER&Iacute;ODO DO DOCUMENTO</span>
                            </b>
                        </p>
                    </td>
                    <td colspan="2"
                        nowrap="nowrap"
                        style="
                            width:87.55pt;
                            border-top:none;
                            border-left:none;
                            border-bottom:solid windowtext 1.0pt;
                            border-right:solid windowtext 1.0pt;
                            mso-border-top-alt:solid windowtext .5pt;
                            mso-border-left-alt:solid windowtext .5pt;
                            mso-border-alt:solid windowtext .5pt;
                            background:#E7E6E6;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:23.75pt"
                        width="117">
                        <p align="center" class="MsoNormal" style="text-align:center">
                            <b>
                                <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">VALIDADO?</span>
                            </b>
                        </p>
                    </td>
                    <td colspan="2"
                        style="
                            width:91.65pt;
                            border-top:none;
                            border-left:none;
                            border-bottom:solid windowtext 1.0pt;
                            border-right:solid windowtext 1.0pt;
                            mso-border-top-alt:solid windowtext .5pt;
                            mso-border-left-alt:solid windowtext .5pt;
                            mso-border-alt:solid windowtext .5pt;
                            background:#E7E6E6;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:23.75pt"
                        width="122">
                        <p align="center" class="MsoNormal" style="text-align:center">
                            <b>
                                <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">EST&Aacute;GIO OBRIGAT&Oacute;RIO?</span>
                            </b>
                        </p>
                    </td>
                    <td style="
                            width:62.45pt;
                            border-top:none;
                            border-left:none;
                            border-bottom:solid windowtext 1.0pt;
                            border-right:solid windowtext 1.0pt;
                            mso-border-top-alt:solid windowtext .5pt;
                            mso-border-left-alt:solid windowtext .5pt;
                            mso-border-alt:solid windowtext .5pt;
                            background:#E7E6E6;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:23.75pt"
                        width="83">
                        <p align="center" class="MsoNormal" style="text-align:center">
                            <b>
                                <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">TOTAL DE HORAS V&Aacute;LIDAS PARA REGISTRO</span>
                            </b>
                        </p>
                    </td>
                </tr>
                <tr style="mso-yfti-irow:3;mso-yfti-lastrow:yes;height:14.25pt" data-th-each="activityPlan : ${activityPlans}">
                    <td nowrap="nowrap"
                        style="
                            width:140.05pt;
                            border:solid windowtext 1.0pt;
                            border-top:none;
                            mso-border-top-alt:solid windowtext .5pt;
                            mso-border-alt:solid windowtext .5pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt"
                        width="187">
                        <p align="center" class="MsoNormal" style="text-align:center">
                            <span style="font-size:9.0pt">
                                <span style="font-family:&quot;Arial&quot;,sans-serif">
                                    <span style="color:black">Plano de Atividades de Est&aacute;gio</span>
                                </span>
                            </span>
                        </p>
                    </td>
                    <td colspan="2"
                        nowrap="nowrap"
                        style="
                            width:107.25pt;
                            border-top:none;
                            border-left:none;
                            border-bottom:solid windowtext 1.0pt;
                            border-right:solid windowtext 1.0pt;
                            mso-border-top-alt:solid windowtext .5pt;
                            mso-border-left-alt:solid windowtext .5pt;
                            mso-border-alt:solid windowtext .5pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt"
                        width="143">
                        <p class="MsoNormal" style="text-align: center;">
                            <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif">
                                <span data-th-text="${#temporals.format(activityPlan.internshipStartDate, ''dd/MM/yyyy'')}"></span> a <span data-th-text="${#temporals.format(activityPlan.internshipEndDate, ''dd/MM/yyyy'')}"></span>
                            </span>
                        </p>
                    </td>
                    <td nowrap="nowrap"
                        style="
                            width:44.85pt;
                            border-top:none;
                            border-left:none;
                            border-bottom:solid windowtext 1.0pt;
                            border-right:solid windowtext 1.0pt;
                            mso-border-top-alt:solid windowtext .5pt;
                            mso-border-left-alt:solid windowtext .5pt;
                            mso-border-alt:solid windowtext .5pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt"
                        width="60">
                        <p align="center" class="MsoNormal" style="text-align:center">
                            <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">(X) sim</span>
                        </p>
                    </td>
                    <td nowrap="nowrap"
                        style="
                            width:42.7pt;
                            border-top:none;
                            border-left:none;
                            border-bottom:solid windowtext 1.0pt;
                            border-right:solid windowtext 1.0pt;
                            mso-border-top-alt:solid windowtext .5pt;
                            mso-border-left-alt:solid windowtext .5pt;
                            mso-border-alt:solid windowtext .5pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt"
                        width="57">
                        <p align="center" class="MsoNormal" style="text-align:center">
                            <span class="GramE">
                                <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">( )</span>
                            </span>
                            <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">n&atilde;o</span>
                        </p>
                    </td>
                    <td nowrap="nowrap"
                        style="
                            width:43.95pt;
                            border-top:none;
                            border-left:none;
                            border-bottom:solid windowtext 1.0pt;
                            border-right:solid windowtext 1.0pt;
                            mso-border-top-alt:solid windowtext .5pt;
                            mso-border-left-alt:solid windowtext .5pt;
                            mso-border-alt:solid windowtext .5pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt"
                        width="59">
                        <p align="center" class="MsoNormal" style="text-align:center">
                            <span class="GramE">
                                <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">(<span data-th-text="${internship.internshipType.name == ''REQUIRED''} ? ''X'' : '' ''"></span>)</span>
                            </span>
                            <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">sim</span>
                        </p>
                    </td>
                    <td style="
                            width:47.7pt;
                            border-top:none;
                            border-left:none;border-bottom:solid windowtext 1.0pt;
                            border-right:solid windowtext 1.0pt;
                            mso-border-top-alt:solid windowtext .5pt;
                            mso-border-left-alt:solid windowtext .5pt;
                            mso-border-alt:solid windowtext .5pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt"
                        width="64">
                        <p align="center" class="MsoNormal" style="text-align:center">
                            <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">(<span data-th-text="${internship.internshipType.name == ''NOT_REQUIRED''} ? ''X'' : '' ''"></span>) n&atilde;o</span>
                        </p>
                    </td>
                    <td style="
                            width:62.45pt;
                            border-top:none;
                            border-left:none;
                            border-bottom:solid windowtext 1.0pt;
                            border-right:solid windowtext 1.0pt;
                            mso-border-top-alt:solid windowtext .5pt;
                            mso-border-left-alt:solid windowtext .5pt;
                            mso-border-alt:solid windowtext .5pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt"
                        width="83">
                        <p align="center" class="MsoNormal" style="text-align:center">-</p>
                    </td>
                </tr>
                <!--[if !supportMisalignedColumns]-->
                <tr data-th-each="report : ${monthlyReports}">
                    <td nowrap="nowrap"
                        style="
                            border-color: currentcolor windowtext windowtext;
                            border-style: none solid solid;
                            border-width: medium 1pt 1pt;
                            border-image: none 100% / 1 / 0 stretch;
                            width: 140.05pt; padding: 0cm 3.5pt;
                            height: 14.25pt; text-align: center;"
                        width="187">
                        <span style="font-size:9.0pt">
                            <span style="font-family:&quot;Arial&quot;,sans-serif">
                                <span style="color:black">Relat&oacute;rio mensal</span>
                            </span>
                        </span>
                    </td>
                    <td colspan="2"
                        nowrap="nowrap"
                        style="
                            border-color: currentcolor windowtext windowtext currentcolor;
                            border-style: none solid solid none;
                            border-width: medium 1pt 1pt medium;
                            width: 107.25pt; padding: 0cm 3.5pt;
                            height: 14.25pt; text-align: center;"
                        width="143">
                        <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif"><span data-th-text="${#temporals.format(report.startDate, ''dd/MM/yyyy'')}"></span> a <span data-th-text="${#temporals.format(report.endDate, ''dd/MM/yyyy'')}"></span></span>
                    </td>
                    <td nowrap="nowrap"
                        style="
                            border-bottom:solid windowtext 1.0pt;
                            width:44.85pt;
                            border-top:none;
                            border-left:none;
                            border-right:solid windowtext 1.0pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt"
                        width="60">
                        <p align="center" style="text-align:center">
                            <span style="font-size:9.0pt">
                                <span style="font-family:&quot;Arial&quot;,sans-serif">
                                    <span style="color:black">(X) sim</span>
                                </span>
                            </span>
                        </p>
                    </td>
                    <td nowrap="nowrap"
                        style="
                            border-bottom:solid windowtext 1.0pt;
                            width:42.7pt;
                            border-top:none;
                            border-left:none;
                            border-right:solid windowtext 1.0pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt"
                        width="57">
                        <p align="center" style="text-align:center">
                            <span style="font-size:9.0pt">
                                <span style="font-family:&quot;Arial&quot;,sans-serif">
                                    <span style="color:black">( )</span>
                                </span>
                            </span>
                            <span style="font-size:9.0pt">
                                <span style="font-family:&quot;Arial&quot;,sans-serif">
                                    <span style="color:black"> n&atilde;o</span>
                                </span>
                            </span>
                        </p>
                    </td>
                    <td nowrap="nowrap"
                        style="
                            border-bottom:solid windowtext 1.0pt;
                            width:43.95pt; border-top:none;
                            border-left:none;
                            border-right:solid windowtext 1.0pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt"
                        width="59">
                        <p align="center" style="text-align:center">
                            <span style="font-size:9.0pt">
                                <span style="font-family:&quot;Arial&quot;,sans-serif">
                                    <span style="color:black">(<span data-th-text="${internship.internshipType.name == ''REQUIRED''} ? ''X'' : '' ''"></span>)</span>
                                </span>
                            </span>
                            <span style="font-size:9.0pt">
                                <span style="font-family:&quot;Arial&quot;,sans-serif">
                                    <span style="color:black"> sim</span>
                                </span>
                            </span>
                        </p>
                    </td>
                    <td style="border-bottom:solid windowtext 1.0pt; width:47.7pt; border-top:none; border-left:none; border-right:solid windowtext 1.0pt; padding:0cm 3.5pt 0cm 3.5pt; height:14.25pt"
                        width="64">
                        <p align="center" style="text-align:center">
                            <span style="font-size:9.0pt">
                                <span style="font-family:&quot;Arial&quot;,sans-serif">
                                    <span style="color:black">(<span data-th-text="${internship.internshipType.name == ''NOT_REQUIRED''} ? ''X'' : '' ''"></span>) n&atilde;o</span>
                                </span>
                            </span>
                        </p>
                    </td>
                    <td style="
                            border-bottom:solid windowtext 1.0pt;
                            width:62.45pt; border-top:none;
                            border-left:none;
                            border-right:solid windowtext 1.0pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt" width="83">
                        <p align="center" style="text-align:center"><span data-th-text="${report.numberOfApprovedHours}"></span></p>
                    </td>
                </tr>
                <tr>
                    <td nowrap="nowrap"
                        style="
                            border:solid windowtext 1.0pt;
                            width:140.05pt;
                            border-top:none;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            text-align: center;
                            height:14.25pt"
                        width="187">
                        <span style="font-size:9.0pt">
                            <span style="text-align: center;font-family:&quot;Arial&quot;,sans-serif">
                                <span style="color:black;">Termo de Realiza&ccedil;&atilde;o de Est&aacute;gio</span>
                            </span>
                        </span>
                    </td>
                    <td colspan="2"
                        nowrap="nowrap"
                        style="
                            border-color: currentcolor windowtext windowtext currentcolor;
                            border-style: none solid solid none;
                            border-width: medium 1pt 1pt medium;
                            width: 107.25pt;
                            padding: 0cm 3.5pt;
                            height: 14.25pt;
                            text-align: center;"
                        width="143">
                        <span style="font-size:9.0pt;font-family:&quot;Arial&quot;,sans-serif">
                            <span style="font-family:&quot;Arial&quot;,sans-serif" data-th-text="${#temporals.format(realizationTerm.internshipStartDate, ''dd/MM/yyyy'')}"></span> a <span style="font-family:&quot;Arial&quot;,sans-serif" data-th-text="${#temporals.format(realizationTerm.internshipEndDate, ''dd/MM/yyyy'')}"></span>
                        </span>
                    </td>
                    <td nowrap="nowrap"
                        style="
                            border-bottom:solid windowtext 1.0pt;
                            width:44.85pt;
                            border-top:none;
                            border-left:none;
                            border-right:solid windowtext 1.0pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt"
                        width="60">
                        <p align="center" style="text-align:center">
                            <span style="font-size:9.0pt">
                                <span style="font-family:&quot;Arial&quot;,sans-serif">
                                    <span style="color:black">(X) sim</span>
                                </span>
                            </span>
                        </p>
                    </td>
                    <td nowrap="nowrap"
                        style="
                            border-bottom:solid windowtext 1.0pt;
                            width:42.7pt;
                            border-top:none;
                            border-left:none;
                            border-right:solid windowtext 1.0pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt"
                        width="57">
                        <p align="center" style="text-align:center">
                            <span style="font-size:9.0pt">
                                <span style="font-family:&quot;Arial&quot;,sans-serif">
                                    <span style="color:black">(&nbsp;)</span>
                                </span>
                            </span>
                            <span style="font-size:9.0pt">
                                <span style="font-family:&quot;Arial&quot;,sans-serif">
                                    <span style="color:black">n&atilde;o</span>
                                </span>
                            </span>
                        </p>
                    </td>
                    <td nowrap="nowrap"
                        style="
                            border-bottom:solid windowtext 1.0pt;
                            width:43.95pt;
                            border-top:none;
                            border-left:none;
                            border-right:solid windowtext 1.0pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt"
                        width="59">
                        <p align="center" style="text-align:center">
                            <span style="font-size:9.0pt">
                                <span style="font-family:&quot;Arial&quot;,sans-serif">
                                    <span style="color:black">(<span data-th-text="${internship.internshipType.name == ''REQUIRED''} ? ''X'' : '' ''"></span>)</span>
                                </span>
                            </span>
                            <span style="font-size:9.0pt">
                                <span style="font-family:&quot;Arial&quot;,sans-serif">
                                    <span style="color:black"> sim</span>
                                </span>
                            </span>
                        </p>
                    </td>
                    <td style="
                            border-bottom:solid windowtext 1.0pt;
                            width:47.7pt;
                            border-top:none;
                            border-left:none;
                            border-right:solid windowtext 1.0pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt"
                        width="64">
                        <p align="center" style="text-align:center">
                            <span style="font-size:9.0pt">
                                <span style="font-family:&quot;Arial&quot;,sans-serif">
                                    <span style="color:black">(<span data-th-text="${internship.internshipType.name == ''NOT_REQUIRED''} ? ''X'' : '' ''"></span>) n&atilde;o</span>
                                </span>
                            </span>
                        </p>
                    </td>
                    <td style="
                            border-bottom:solid windowtext 1.0pt;
                            width:62.45pt; border-top:none;
                            border-left:none;
                            border-right:solid windowtext 1.0pt;
                            padding:0cm 3.5pt 0cm 3.5pt;
                            height:14.25pt"
                        width="83">
                        <p align="center" style="text-align:center" data-th-text="${totalNumberOfApprovedHours}"></p>
                    </td>
                </tr>
                <tr height="0">
                    <td style="border:none" width="203">&nbsp;</td>
                    <td style="border:none" width="151">&nbsp;</td>
                    <td style="border:none" width="4">&nbsp;</td>
                    <td style="border:none" width="65">&nbsp;</td>
                    <td style="border:none" width="62">&nbsp;</td>
                    <td style="border:none" width="64">&nbsp;</td>
                    <td style="border:none" width="69">&nbsp;</td>
                    <td style="border:none" width="90">&nbsp;</td>
                </tr>
                <!--[endif]-->
            </tbody>
        </table>
    </div>

    <p align="center" class="MsoNormal" style="margin-left:1.0cm;text-align:center;text-indent:14.2pt">&nbsp;</p>

    <p class="MsoNormal">
        <b>
            <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">Parecer:</span>
        </b>
    </p>

    <div style="mso-element:para-border-div;border:solid windowtext 1.0pt;mso-border-alt:solid windowtext .5pt;padding:1.0pt 4.0pt 1.0pt 4.0pt;background:#F2F2F2">
        <p class="MsoNormal"
            style="
                background:#F2F2F2;
                border:none;
                mso-border-alt:solid windowtext .5pt;
                padding:0cm;
                mso-padding-alt:1.0pt 4.0pt 1.0pt 4.0pt">
            Os documentos apresentados pelo(a) estudante estagi&aacute;rio(a) e pela empresa concedente se encontram adequados para a finaliza&ccedil;&atilde;o do Est&aacute;gio Supervisionado
            <span data-th-text="${internship.internshipType.description}"></span>, no contexto do Curso <span data-th-text="${internship.advisorRequest.curriculum.course.name}"></span> desta institui&ccedil;&atilde;o, motivo pelo qual eu o aprovo enquanto professor orientador de est&aacute;gio do referido curso.
        </p>
    </div>

    <p align="right" class="paragraphscxw76620377bcx0" style="margin:0cm;text-align:right;vertical-align:baseline">&nbsp;</p>

    <p align="right" class="paragraphscxw76620377bcx0" style="margin:0cm;text-align:right;vertical-align:baseline">&nbsp;</p>

    <p align="right" class="paragraphscxw76620377bcx0" style="margin:0cm;text-align:right;vertical-align:baseline">
        <span class="normaltextrunscxw76620377bcx0">
            <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">
                S&atilde;o Paulo,&nbsp;
            </span>
        </span>
        <span class="normaltextrunscxw76620377bcx0">
            <span style="
                font-size:11.0pt;
                font-family:&quot;Arial&quot;,sans-serif;
                mso-fareast-font-family:&quot;Arial Unicode MS&quot;;
                color:black;
                background:#E1E3E6">
                <span data-th-text="${#calendars.format(#calendars.createNow(), ''dd'')}"></span>
            </span>
        </span>
        <span class="normaltextrunscxw76620377bcx0">
            <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">
                &nbsp;de&nbsp;
            </span>
        </span>
        <span class="normaltextrunscxw76620377bcx0">
            <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;mso-fareast-font-family:&quot;Arial Unicode MS&quot;;color:black;background:#E1E3E6">
                <span data-th-text="${#calendars.format(#calendars.createNow(), ''MMMM'')}"></span>
            </span>
        </span>
        <span class="normaltextrunscxw76620377bcx0">
            <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">
                &nbsp;de
            </span>
        </span>
        <span class="normaltextrunscxw76620377bcx0">
            <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;mso-fareast-font-family:&quot;Arial Unicode MS&quot;;color:black;background:#E1E3E6">
                &nbsp;<span data-th-text="${#calendars.format(#calendars.createNow(), ''yyyy'')}"></span>
            </span>
        </span>
        <span class="eopscxw76620377bcx0">
            <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">
                .
            </span>
        </span>
    </p>

    <p class="MsoNormal" style="text-align:justify">&nbsp;</p>

    <div align="center">
        <table border="0"
            cellpadding="0"
            cellspacing="6"
            class="MsoTableGrid"
            style="
                width:100.0%;
                mso-cellspacing:4.2pt;
                border:none;
                mso-yfti-tbllook:1184;
                mso-padding-alt:0cm 0cm 0cm 0cm;
                mso-border-insideh:none;
                mso-border-insidev:none"
            width="100%">
            <tbody>
                <tr style="mso-yfti-irow:0;mso-yfti-firstrow:yes">
                    <td style="width:6.0cm;padding:0cm 0cm 0cm 0cm" valign="top" width="227">
                        <p align="right" class="MsoNormal" style="text-align:right">
                            <span class="normaltextrunscxw246734469bcx0">
                                <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">
                                    Professor Orientador dos Cursos:
                                </span>
                            </span>
                        </p>
                    </td>
                    <td style="width:11.0cm;padding:0cm 0cm 0cm 0cm" valign="top" width="416">
                        <p class="MsoNormal" style="text-align:justify">
                            <span style="font-size:11.0pt">
                                <span style="background:#e1e3e6">
                                    <span style="font-family:&quot;Arial&quot;,sans-serif">
                                        <span style="color:black">
                                            <b>
                                                <span data-th-each="c, course : ${internship.advisorRequest.advisor.courses}" data-th-text="!${course.last} ? ${c.name} + '', '' : ${c.name}"></span>
                                            </b>
                                        </span>
                                    </span>
                                </span>
                            </span>
                        </p>
                    </td>
                </tr>
                <tr style="mso-yfti-irow:1">
                    <td style="width:6.0cm;padding:0cm 0cm 0cm 0cm" valign="top" width="227">
                        <p align="right" class="MsoNormal" style="text-align:right">
                            <span class="normaltextrunscxw246734469bcx0">
                                <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">Nome:</span>
                            </span>
                        </p>
                    </td>
                    <td style="width:11.0cm;padding:0cm 0cm 0cm 0cm" valign="top" width="416">
                        <p class="MsoNormal" style="text-align:justify">
                            <span class="normaltextrunscxw246734469bcx0">
                                <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;mso-fareast-font-family:&quot;Arial Unicode MS&quot;;color:black;background:#E1E3E6">
                                    <b data-th-text="${internship.advisorRequest.advisor.user.name}"></b>
                                </span>
                            </span>
                        </p>
                    </td>
                </tr>
                <tr style="mso-yfti-irow:2;mso-yfti-lastrow:yes">
                    <td style="width:6.0cm;padding:0cm 0cm 0cm 0cm" valign="top" width="227">
                        <p align="right" class="MsoNormal" style="text-align:right">
                            <span class="normaltextrunscxw246734469bcx0">
                                <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif">
                                    E-mail institucional:
                                </span>
                            </span>
                        </p>
                    </td>
                    <td style="width:11.0cm;padding:0cm 0cm 0cm 0cm" valign="top" width="416">
                        <p class="MsoNormal" style="text-align:justify">
                            <span class="normaltextrunscxw246734469bcx0">
                                <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;mso-fareast-font-family:&quot;Arial Unicode MS&quot;;color:black;background:#E1E3E6">
                                    <b data-th-text="${internship.advisorRequest.advisor.user.email}"></b>
                                </span>
                            </span>
                        </p>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
  </div>

  <p style="text-align:center">&nbsp;</p>'
);