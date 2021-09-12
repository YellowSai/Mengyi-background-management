package com.jianzheng.server.controller.api;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.jianzheng.server.config.AppConfig;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import net.scode.commons.exception.ScodeRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author shawve
 * @date 2020/5/30 19:23
 * @description 文件上传
 */
@Slf4j
@RestController
@RequestMapping("/api/upload")
public class UploadController {

        @Autowired
        private AppConfig appConfig;

        @ApiOperation(value = "上传文件到服务器", notes = "成功则返回文件保存的url地址")
        @PostMapping
        public R uploadFile(@RequestParam @NotNull(message = "上传文件不能为空") MultipartFile file) {
            //获取原文件名
            String filename = file.getOriginalFilename();
            //获取文件后缀名
            String suffix = filename.substring(filename.lastIndexOf('.'));
            //生成新文件名
            String newFileName = UUID.randomUUID().toString() + suffix;
            //生成日期目录
            String datePath = new DateTime().toString("yyyy-MM");
            String filePath = datePath + "/" + newFileName;
            //生成文件保存路径
            File outFile = FileUtil.file(appConfig.getUploadBasePath(), filePath);
            log.debug("outFile:{}", outFile.getAbsoluteFile());
            //文件输入输出缓冲流
            BufferedInputStream is = null;
            BufferedOutputStream os = null;

            //将文件进行保存
            try {
                is = new BufferedInputStream(file.getInputStream());
                os = FileUtil.getOutputStream(outFile);
                IoUtil.copy(is, os);
            } catch (IOException e) {
                throw new ScodeRuntimeException("文件保存失败！");
            } finally {
                IoUtil.close(is);
                IoUtil.close(os);
            }
            String fileUrl = appConfig.getUploadBaseUrl() + filePath;
            HashMap<String, Object> map = new HashMap<>();
            map.put("url", fileUrl);
            return R.data(map);
        }
    }

