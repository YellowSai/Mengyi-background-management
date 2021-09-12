package com.jianzheng.server.web;

import cn.hutool.json.JSONUtil;
import com.jianzheng.server.config.AppConfig;
import com.jianzheng.server.consts.AuthConsts;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.constant.Consts;
import net.scode.commons.core.R;
import net.scode.commons.util.JWTUtil;
import net.scode.commons.util.StringUtil;
import net.scode.commons.web.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * 管理后台业务相关拦截器
 *
 * @author tanghuang 2020年02月25日
 */
@Slf4j
public class WebServiceInterceptor implements HandlerInterceptor {
    private static final String JSON_CONTENT_TYPE = "application/json;charset=UTF-8";


    @Autowired
    private AppConfig appConfig;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     *
     * @param request
     * @param response
     * @param handler
     * @return true，如果false，停止流程，api被拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        log.debug("request=>{}", request.getRequestURI());
        log.debug("response=>{}", response.getOutputStream());
        // 填充公共参数
        String bearToken = request.getHeader(AuthConsts.AUTHORIZATION_HEADER);
        if (StringUtils.isEmpty(bearToken)) {
            bearToken = request.getParameter("token");
            log.info("header 中token 为空，尝试从request中获取:{}", bearToken);
        }
        String ip = RequestUtil.getRemoteAddr(request);
        WebContext.PublicParameter publicParameter = WebContext.getPublicParameter();
        publicParameter.setToken(bearToken);

        if (StringUtil.isEmpty(bearToken)) {
            setResponse(response, R.error(Consts.NO_LOGIN_CODE, "未登录"));
            return false;
        }
        // 检查token
        Claims claims = JWTUtil.parseJWT(appConfig.getJwtSecret(), bearToken);
        if (JWTUtil.isExpired(claims, appConfig.getJwtSubject())) {
            setResponse(response, R.error(Consts.NO_LOGIN_CODE, "未登录"));
            return false;
        }
        //TODO 自动续约token
        int userId = Integer.parseInt(String.valueOf(claims.get("userId")));
        publicParameter.setUserId(userId);
        // 检查token
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // TODO
        }

        return true;
    }

    private void setResponse(ServletResponse response, R result) {
        try {
            OutputStream os = response.getOutputStream();
            String jsonStr = JSONUtil.toJsonStr(result);
            os.write(jsonStr.getBytes(StandardCharsets.UTF_8));
            response.setContentType(JSON_CONTENT_TYPE);
        } catch (Exception ex) {
            log.warn("exception=" + ex);
        }
    }
}
