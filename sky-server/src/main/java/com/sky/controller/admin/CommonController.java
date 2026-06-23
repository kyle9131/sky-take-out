package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    // 本地存储路径（图片会存到这个文件夹，你可以改成自己想要的盘符/路径）
    private static final String BASE_PATH = "D:/cangqiong_project/uploads/";

    /**
     * 文件上传（本地存储版）
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传：{}", file);

        try {
            // 1. 原始文件名
            String originalFilename = file.getOriginalFilename();
            // 2. 截取后缀名，比如 .png .jpg
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 3. 用 UUID 生成新文件名，避免重名覆盖
            String fileName = UUID.randomUUID().toString() + extension;

            // 4. 确保存储文件夹存在，不存在就创建
            File dir = new File(BASE_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 5. 把上传的文件保存到本地
            file.transferTo(new File(BASE_PATH + fileName));

            // 6. 返回可以访问这张图片的网址
            String url = "http://localhost:8080/uploads/" + fileName;
            log.info("文件上传成功，访问地址：{}", url);
            return Result.success(url);

        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
        }

        return Result.error("文件上传失败");
    }
}