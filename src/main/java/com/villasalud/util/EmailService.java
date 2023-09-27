package com.villasalud.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;


@Component
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	public void sendEmail(Mail mail) {
        // Validación de campos
        //validarCamposDeCorreo(mail);
		try {
			MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            
           /* Context context = new Context();
            context.setVariables(mail.getModel());*/
            Context context = new Context();
            if (mail.getModel() != null) {
                mail.getModel().forEach(context::setVariable);
            }

            String html = templateEngine.process("email/email-template", context);

            helper.setFrom(mail.getFrom());
            helper.setTo(mail.getTo());

            helper.setSubject(mail.getSubject());
            helper.setText(html, true);
            
            emailSender.send(message);
		}catch(Exception e) {
            throw new RuntimeException(e);
		}
	}
    /**
     * Valida que los campos necesarios del objeto Mail no estén nulos o vacíos.
     *
     * @param mail el objeto Mail a validar.
     */
    private void validarCamposDeCorreo(Mail mail) {
        if (mail == null) {
            throw new IllegalArgumentException("El objeto Mail no puede ser nulo");
        }
        if (mail.getTo() == null || mail.getTo().isEmpty()) {
            throw new IllegalArgumentException("La dirección de correo destinatario no puede ser nula o estar vacía");
        }
        if (mail.getFrom() == null || mail.getFrom().isEmpty()) {
            throw new IllegalArgumentException("La dirección de correo remitente no puede ser nula o estar vacía");
        }
        if (mail.getSubject() == null || mail.getSubject().isEmpty()) {
            throw new IllegalArgumentException("El asunto del correo no puede ser nulo o estar vacío");
        }
        // Puedes continuar validando otros campos si es necesario
    }
}
