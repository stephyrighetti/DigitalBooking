package com.example.proyectoIntegrador.email;

import com.example.proyectoIntegrador.auth.model.User;
import com.example.proyectoIntegrador.auth.payload.response.JwtResponse;
import com.example.proyectoIntegrador.auth.repository.RepositoryUser;
import com.example.proyectoIntegrador.exception.RecordNotFoundException;
import com.example.proyectoIntegrador.service.AuthService;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MailService {

    @Value("${from.email.address}")
    private String fromEmailAddress;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    AuthService authService;


    @Autowired
    Configuration fmConfiguration;

    @Autowired
    RepositoryUser repositoryUser;

    @Autowired
    PasswordEncoder encoder;

    @Async
    public void sendEmail(EmailDto emailDto) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromEmailAddress, "BookingDH");
        helper.setTo(emailDto.getToEmail());
        helper.setSubject(emailDto.getSubject());
        helper.setText(emailDto.getBody(), true);

        mailSender.send(message);
    }

    @Async
    public void sendTemplate(EmailDto emailDto, String template) {
        MimeMessage mimeMessage =mailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(emailDto.getSubject());
            mimeMessageHelper.setFrom(fromEmailAddress, "BookingDH");
            mimeMessageHelper.setTo(emailDto.getToEmail());
            emailDto.setBody(geContentFromTemplate(emailDto.getModel(), template));
            mimeMessageHelper.setText(emailDto.getBody(), true);

            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String geContentFromTemplate(Map < String, Object >model, String template)     {
        StringBuffer content = new StringBuffer();

        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmConfiguration.getTemplate(template), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public void sendEmailUser(String host, User user){

        String url = host+"/activate/"+user.getHashActive();

        EmailDto emailDto = new EmailDto();
        emailDto.setToEmail(user.getEmail());
        emailDto.setSubject("Activa tu cuenta BookingDH");

        Map<String, Object> mapBody = new HashMap<String, Object>();
        mapBody.put("name", user.getName());
        mapBody.put("surname", user.getSurname());
        mapBody.put("link", url);

        emailDto.setModel(mapBody);

        sendTemplate(emailDto, "activate-link.html");
    }


    public JwtResponse activeAccount(String hash){
        Optional<User> user = repositoryUser.findByhashActive(hash);
        if (user.isPresent()){
            user.get().setActive(true);
            user.get().setHashActive(user.get().generarHashCode());

            repositoryUser.save(user.get());
            return authService.loginSinPassword(user.get());


        }else{
            throw new RecordNotFoundException("No se puede activar su cuenta, use el enlace de recuperar contraseña!");
        }

    }


    public String recoveryPassword(String email, String host){
        Optional<User> user = repositoryUser.findByEmail(email);
        if (user.isPresent()){

            user.get().setHashActive(user.get().generarHashCode());
            repositoryUser.save(user.get());

            String url = host+"/recovery-confirm/"+user.get().getHashActive();

            EmailDto emailDto = new EmailDto();
            emailDto.setToEmail(user.get().getEmail());
            emailDto.setSubject("Cambiar contraseña de BookingDH");

            Map<String, Object> mapBody = new HashMap<String, Object>();
            mapBody.put("name", user.get().getName());
            mapBody.put("surname", user.get().getSurname());
            mapBody.put("link", url);

            emailDto.setModel(mapBody);

            sendTemplate(emailDto, "recuperar-link.html");


            return "Revise el buzón de su correo, se envió un enlace para cambiar la contraseña";
        }else{
            throw new RecordNotFoundException("Usuario con el email '" + email + "' no existe");
        }

    }

    public String recoveryPasswordConfirm(String hash, String password, String host){
        Optional<User> user = repositoryUser.findByhashActive(hash);
        if (user.isPresent()){

            user.get().setHashActive(user.get().generarHashCode());
            user.get().setPassword(encoder.encode(password));
            repositoryUser.save(user.get());

            EmailDto emailDto = new EmailDto();
            emailDto.setToEmail(user.get().getEmail());
            emailDto.setSubject("Confirmación cambio contraseña de BookingDH");

            Map<String, Object> mapBody = new HashMap<String, Object>();
            mapBody.put("name", user.get().getName());
            mapBody.put("surname", user.get().getSurname());
            mapBody.put("link", host);

            emailDto.setModel(mapBody);

            sendTemplate(emailDto, "success-password-link.html");


            return "Se ha cambiado la contraseña de BookingDH";
        }else{
            throw new RecordNotFoundException("Solicite recuperar contraseña nuevamente!");
        }

    }


}