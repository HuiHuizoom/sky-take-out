package com.sky.controller.admin;


import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
@Api(tags = "通用接口")
@RequestMapping("/admin/common")
public class CommonController {

    @Autowired
    private  AliOssUtil aliOssUtil;
    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info("正在上传文件:{}",file);

        try {
            //拿到文件
            String originalFilename = file.getOriginalFilename();
            //拿到文件后缀
            String extend = originalFilename.substring(originalFilename.lastIndexOf("."));
            //生成随机文件名
            String randomPath = UUID.randomUUID().toString() + extend;


            String filePath = aliOssUtil.upload(file.getBytes(), randomPath);

            return Result.success(filePath);
        } catch (IOException e) {
            log.error("上传失败 :{}",e);
        }


        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
