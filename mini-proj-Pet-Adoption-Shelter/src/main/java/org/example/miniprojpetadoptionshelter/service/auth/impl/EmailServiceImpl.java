package org.example.miniprojpetadoptionshelter.service.auth.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.miniprojpetadoptionshelter.config.MailProperties;
import org.example.miniprojpetadoptionshelter.service.auth.EmailService;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final MailProperties mailProperties;

    @Override
    public void sendTextEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

            helper.setTo(to);
            helper.setFrom(mailProperties.getFrom());
            helper.setSubject(subject);
            helper.setText(text, false);

            mailSender.send(message);
            log.info("📩 Text email sent to {}", to);
        } catch (MessagingException | MailException e) {
            log.error("🚫 Failed to send text email to {}: {}", to, e.getMessage());
            throw new RuntimeException("메일 전송 실패");
        }
    }

    @Override
    public void sendHtmlEmail(String to, String subject, String html) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setFrom(mailProperties.getFrom());
            helper.setSubject(subject);
            helper.setText(html, true);

            mailSender.send(message);
            log.info("📩 HTML email send to {}", to);
        } catch (MessagingException | MailException e) {
            log.error("🚫 Failed to send HTML email to {}: {}", to, e.getMessage());
            throw new RuntimeException("HTML 메일 전송 실패");
        }
    }

    @Override
    public void sendVerifyCode(String to, String verifyCode) {
        String html =
                """
                    <div style="padding:20px; font-size:16px;">
                        <p>안녕하세요</p>
                        <p>아래 인증 코드를 입력하여 인증을 완료해주세요.</p>
                        <h2 style="color:#2a5dff;">%s</h2>
                        <p>인증 코드는 10분 동안만 유효합니다. </p>
                    </div>
                """.formatted(verifyCode);
        sendHtmlEmail(to, "[Pet-Adoption] 이메일 인증 코드", html);
    }

    @Override
    public void sendPasswordReset(String to, String url) {
        String html =
                """
                    <div style="padding:20px; font-size:16px;">
                        <p>비밀번호 재설정을 요청하셨습니다.</p>
                        <p>아래 링크를 클릭하여 새 비밀번호를 설정해주세요.<p>
                        <a href="%s"
                            style="display:inline-block; padding:10px 20px; background:#2a5dff;
                            color:white; text-decoration:none; border-radius:8px; margin-top:10px;">
                            비밀번호 재설정하기
                        </a>
                        <p style="margin-top:20px; color:#999;">
                            * 이 링크는 30분간 유효합니다.
                        </p>
                    </div>
                """.formatted(url);

                sendHtmlEmail(to, "[Pet-Adoption] 비밀번호 재설정 안내", html);
    }
}
