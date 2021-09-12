package com.jianzheng.server.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * 微信公众号对接，提供给微信调用的接口
 *
 * @author tanghuang 2020年02月25日
 */
@Slf4j
@Api(tags = {"微信公众号对接"})
@RestController
@RequestMapping("/api/wx/mp")
public class WechatMpController {

    /**
     * 微信公众号对接数据token验证（GET请求）
     *
     * @param echostr   来自微信服务器用于验证的随机字符串
     * @param signature 加密签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @return
     */
    @ApiOperation("微信公众号对接数据token验证（GET请求）")
    @GetMapping
    public String doGet(@RequestParam("echostr") String echostr, @RequestParam("signature") String signature,
                        @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce) {
        // TODO 对签名进行校验
        return echostr;
    }

    /**
     * 接收微信业务消息并处理（POST请求）
     *
     * @param request
     * @return
     */
    @ApiOperation("接收微信业务消息并处理（POST请求）")
    @PostMapping(produces = "application/text;charset=UTF-8")
    public String doPost(HttpServletRequest request) {
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            // TODO 读取xml，进行处理
            return null;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        } finally {
            IOUtil.close(inputStream);
        }
        return null;
    }

}
