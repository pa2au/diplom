package com.example.tametable.service;

import com.example.tametable.entity.User;
import com.example.tametable.entity.VerifyUser;
import com.example.tametable.repository.VerifyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerifyUserServiceImpl implements VerifyUserService {
    private final VerifyUserRepository verifyUserRepository;
    private final JavaMailSender javaMailSender;

    @Override
    public void save(User user) {
        VerifyUser verifyUser = VerifyUser.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .build();
        verifyUserRepository.save(verifyUser);
        sendVerificationEmail(verifyUser);
    }

    @Override
    public void save(VerifyUser verifyUser) {
        verifyUserRepository.save(verifyUser);
    }

    @Override
    public Optional<VerifyUser> findVerifyUserById(int id) {
        return verifyUserRepository.findById(id);
    }

    @Override
    public Optional<VerifyUser> findVerifyUserByToken(String token) {
        return verifyUserRepository.findByToken(token);
    }

    @Override
    public void resetPassword(User user) {
        String toAddress = user.getEmail();
        String fromAddress = "noreply.kstu.ivt@gmail.com";
        String senderName = "Your company name";
        String subject = "Ссылка для сброса пароля";
        String content = "Дорогой(ая) [[lastName]] [[firstName]] [[patronymic]], <br>"
                + "Пожайлуста, нажмите на ссылку ниже, чтобы восстановить пароль:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>";

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);

            content = content.replace("[[firstName]]", user.getFirstName());
            content = content.replace("[[lastName]]", user.getLastName());
            content = content.replace("[[patronymic]]", user.getPatronymic());

            String verifyURL = "https://lessons-api-university.herokuapp.com/user/resetPassword?token=" + user.getVerifyUser().getToken();

            content = content.replace("[[URL]]", verifyURL);

            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateToken(User user) {
        VerifyUser verifyUser = verifyUserRepository.findById(user.getVerifyUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("user with id %s doesn't exists", user.getId())));
        verifyUser.setToken(UUID.randomUUID().toString());
        verifyUserRepository.save(verifyUser);
    }

    private void sendVerificationEmail(VerifyUser verifyUser) {
        String toAddress = verifyUser.getUser().getEmail();
        String fromAddress = "jumabekovradik@gmail.com";
        String senderName = "Your company name";
        String subject = "Пожайлуста подтвердите свою регистрацию";
        String content = "Дорогой(ая) [[lastName]] [[firstName]] [[patronymic]], <br>"
                + "Пожайлуста нажмите на ссылку ниже, чтобы подтвердить регистрацию:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>";

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);

            content = content.replace("[[firstName]]", verifyUser.getUser().getFirstName());
            content = content.replace("[[lastName]]", verifyUser.getUser().getLastName());
            content = content.replace("[[patronymic]]", verifyUser.getUser().getPatronymic());

            String verifyURL = "https://lessons-api-university.herokuapp.com/verify?id=" + verifyUser.getUser().getId() + "&code=" + verifyUser.getToken();

            content = content.replace("[[URL]]", verifyURL);

            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
