package cn.bzu.hair.web;

import cn.bzu.hair.domain.Captcha;
import cn.bzu.hair.domain.Msg;
import cn.bzu.hair.dto.UserRoleDto;
import cn.bzu.hair.service.CaptchaService;
import cn.bzu.hair.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * (Captcha)表控制层
 *
 * @author 高玉津
 * @since 2020-04-16 13:27:42
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/getCaptcha")
    public Msg sendCode(String phone) {
        return captchaService.sendCode(phone);
    }

    @PostMapping("/codeLogin")
    public Msg codeLogin(@RequestBody Captcha captcha, HttpServletRequest request) {

        Msg msg = captchaService.codeLogin(captcha);
//        if (msg.getCode() == 100) {
//            String ip = IpUtil.getIpAddr(request);
//            UserRoleDto user = (UserRoleDto)msg.getExtend().get("user");
//            redisTemplate.opsForValue().set(ip, user.getUserId());
//        }
        return msg;
    }


}