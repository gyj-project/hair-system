package cn.bzu.hair.service;

import cn.bzu.hair.domain.Msg;
import cn.bzu.hair.domain.User;
import cn.bzu.hair.dto.AuthTokenDto;
import cn.bzu.hair.dto.AuthTokenParamCO;
import cn.bzu.hair.dto.UserRoleDto;
import cn.bzu.hair.message.config.AppConfig;
import cn.bzu.hair.message.lib.MessageSend;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.bzu.hair.domain.Captcha;
import cn.bzu.hair.persistence.CaptchaMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * (Captcha)表服务接口
 *
 * @author 高玉津
 * @since 2020-04-16 13:27:41
 */
@Service
public class CaptchaService extends ServiceImpl<CaptchaMapper,Captcha> {
    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;


    public Msg sendCode(String phone) {

        // 验证手机号是否存在
        User user = userService.getOne(new QueryWrapper<User>()
              //  .eq("user_name", dto.getUserName())
                .eq("phone", phone), false);
        if (null == user) {
            return Msg.fail();
        }
        // 删除原来的验证码
        this.remove(new QueryWrapper<Captcha>().eq("phone", phone));
        // 发送短信
        AppConfig config = new AppConfig();
        MessageSend submail = new MessageSend(config);
        String code = String.valueOf((int)((Math.random()*9+1)*100000));
        submail.addTo(phone);
        submail.addContent("【短信测试】您的验证码是：" + code + "，请在3分钟内输入！");
        submail.send();
        // 插入到表中
        Captcha captcha = new Captcha();
        captcha.setCode(code);
        captcha.setUserName(user.getUserName());
        captcha.setDate(new Date());
        captcha.setType("2");
        captcha.setPhone(phone);
        this.save(captcha);
        return Msg.success();
    }

    public Msg codeLogin(Captcha captcha) {
        Captcha oldCaptcha = this.getOne(
                new QueryWrapper<Captcha>().eq("phone", captcha.getPhone()), false);
        if (null == oldCaptcha){
            return Msg.fail().add("msg","手机号错误");
        }

        if (!oldCaptcha.getCode().equals(captcha.getCode())) {
            return Msg.fail().add("msg","验证码错误");
        }

        Date oldDate = oldCaptcha.getDate();
        Date newDate = captcha.getDate();

        int time = (int)(newDate.getTime() - oldDate.getTime()) / 1000;
//        if (time > 180){
//            return Msg.fail().add("msg","验证码失效");
//        }
        List<UserRoleDto> dto
                = userService.queryUser(null, oldCaptcha.getUserName(), null, null, null);
        Msg msg = new Msg();
        msg.setCode(500);
        if (dto.size() == 1) {
            msg.add("user",dto.get(0));

            // 获取access_token
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.put("client_id", Arrays.asList("c1"));
            param.put("client_secret", Arrays.asList("secret"));
            param.put("grant_type", Arrays.asList("password"));
            param.put("username", Arrays.asList(dto.get(0).getUserName()));
            param.put("password", Arrays.asList(dto.get(0).getUserPassword()));

            HttpEntity formEntity = new HttpEntity<>(param, headers);


            AuthTokenDto tokeDto = restTemplate.postForEntity("http://localhost:8084/oauth/token", formEntity, AuthTokenDto.class).getBody();

            msg.add("token", tokeDto.getAccess_token());
        }






        return msg;
    }
}