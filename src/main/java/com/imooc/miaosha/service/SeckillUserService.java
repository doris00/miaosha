package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.SeckillUserDao;
import com.imooc.miaosha.domain.SeckillUser;
import com.imooc.miaosha.exception.GlobleException;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.redis.SeckillUserKey;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.util.MD5Util;
import com.imooc.miaosha.util.UUIDUtil;
import com.imooc.miaosha.vo.LoginVo;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class SeckillUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Resource
    RedisService redisService;

    @Resource
    SeckillUserDao seckillUserDao;

    public SeckillUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        SeckillUser user = redisService.get(SeckillUserKey.token, token, SeckillUser.class);
        //延长一下有效期
        if (user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    public SeckillUser getById(long id) {

        //取缓存
        SeckillUser user =  redisService.get(SeckillUserKey.getById, "" + id, SeckillUser.class);
        if (user != null) {
            return user;
        }
        //去数据库
        user = seckillUserDao.getById(id);
        if (user != null) {
            redisService.set(SeckillUserKey.getById, "" + id, user);
        }
        return user;
//        return seckillUserDao.getById(id);
    }

    // http://blog.csdn.net/tTU1EvLDeLFq5btqiK/article/details/78693323
    public boolean updatePassword(String token, long id, String formPass) {
        //取user
        SeckillUser user = getById(id);
        if(user == null) {
            throw new GlobleException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //更新数据库
        SeckillUser toBeUpdate = new SeckillUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()));
        seckillUserDao.update(toBeUpdate);
        //处理缓存
        redisService.delete(SeckillUserKey.getById, ""+id);
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(SeckillUserKey.token, token, user);
        return true;
    }


    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobleException(CodeMsg.SEVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        SeckillUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobleException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass)) {
            throw new GlobleException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return true;
    }

    public void addCookie(HttpServletResponse response, String token, SeckillUser user) {
        //生成cookie
        redisService.set(SeckillUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(SeckillUserKey.token.expiredSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
