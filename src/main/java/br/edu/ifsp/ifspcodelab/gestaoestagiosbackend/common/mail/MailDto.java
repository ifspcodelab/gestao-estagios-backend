package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail;

import lombok.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {

    private String msgHTML;
    private String title;
    private String replyTo;
    private List<String> recipientsTO;
    private List<String> recipientsCC;
    private List<String> recipientsBCC;
    private Date dateSent;
    private List<File> files;

    public MailDto setRecipientTo(String... destinary) {

        recipientsTO = new ArrayList<>();

        for(String s : destinary) {
            recipientsTO.add(s);
        }

        return this;
    }

    public MailDto setReplyTo(String replyTo) {
        this.replyTo = replyTo;
        return this;
    }

    public boolean isValid() {
        return (!msgHTML.isBlank() && Objects.nonNull(msgHTML) && !title.isBlank() && Objects.nonNull(title) && Objects.nonNull(this.recipientsTO) && !this.recipientsTO.isEmpty());
    }
}
