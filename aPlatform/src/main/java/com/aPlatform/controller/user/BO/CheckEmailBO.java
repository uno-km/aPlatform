package com.aPlatform.controller.user.BO;

import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.common.model.CommonOutVO;
import com.aPlatform.controller.common.model.ResultDTO;
import com.aPlatform.mappers.OperEmailMapper;
@Service
public class CheckEmailBO
{
	@Autowired
	OperEmailMapper operEmailMapper;
	public CommonOutVO gmailSend(Map<String, String> inMap)
	{
		CommonOutVO commonoutVO = new CommonOutVO();
		ResultDTO result = new ResultDTO();
		commonoutVO.setResultDTO(result);
		Map<String, String> resMap = operEmailMapper.getOperationMail(inMap);
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.naver.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.naver.com");
		// prop.put("mail.smtp.host", "smtp.gmail.com");
		// prop.put("mail.smtp.port", 465);
		// prop.put("mail.smtp.auth", "true");
		// prop.put("mail.smtp.ssl.enable", "true");
		// prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		int random = (int) (Math.random() * 89999999) + 10000000;
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(resMap.get("OPER_MAIL"), resMap.get("OPER_PASSWORD"));
			}
		});
		try
		{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(resMap.get("OPER_MAIL")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(inMap.get("user_email")));
			message.setSubject(resMap.get("MAIL_TITLE")); // ?????? ????????? ??????
			message.setText(resMap.get("MAIL_TXT") + " \n " + random); // ?????? ????????? ??????
			/* 22.06.12 ?????? ?????????, ?????? ???????????? ????????? */
			// Transport.send(message); //// ??????
			result.setCode("200");
			result.setMessage("??????????????? ????????? ??????????????????. ????????? ????????? ??????????????????");
			commonoutVO.setReturnResultDTO(random);
		}
		catch (AddressException e)
		{
			commonoutVO.setError(e.getMessage());
			result.setMessage("?????? ?????? ????????? ??????????????????. ??????????????? ???????????????");
			result.setCode("404");
		}
		catch (MessagingException e)
		{
			commonoutVO.setError(e.getMessage());
			result.setMessage("?????? ?????? ????????? ??????????????????. ??????????????? ???????????????");
			result.setCode("400");
		}
		return commonoutVO;
	}
}
