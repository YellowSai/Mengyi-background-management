package com.jianzheng.server.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信支付对接，提供给微信调用的接口
 *
 * @author tanghuang 2020年02月25日
 */
@Slf4j
@Api(tags = {"微信支付回调"})
@RestController
@RequestMapping("/api/wx/pay")
public class WechatPayController {

    /**
     * 微信支付的回调通知处理
     *
     * @param request
     * @return
     */
    @ApiOperation("微信支付的回调通知处理")
    @PostMapping(produces = "application/xml;charset=UTF-8")
    public String notify(HttpServletRequest request) {
        log.info("微信支付异步回调通知开始");
        String result = responseXml("FAIL", "PAY ERROR");
        // TODO
        return result;
    }

    /**
     * 微信退款的回调通知处理
     *
     * @param request
     * @return
     */
    @ApiOperation("微信退款的回调通知处理")
    @PostMapping(value = "/refund", produces = "application/xml;charset=UTF-8")
    public String refundNotify(HttpServletRequest request) {
        log.info("微信退款异步回调通知开始");
        String result = responseXml("FAIL", "REFUND ERROR");
        // TODO
        return result;
    }

    /**
     * 组装支付回调结果为xml
     *
     * @param returnCode 返回码
     * @param returnMsg  返回信息
     * @return
     */
    private String responseXml(String returnCode, String returnMsg) {
        String xml = "<xml><return_code><![CDATA[" + returnCode
                + "]]></return_code><return_msg><![CDATA[" + returnMsg + "]]></return_msg></xml>";
        return xml;
    }

}
