package com.product.luffy.controller;

import com.product.luffy.po.File;
import com.product.luffy.service.FileService;
import com.product.luffy.utils.Exception.ProductRuntimeException;
import com.product.luffy.utils.response.HttpResultCode;
import com.product.luffy.utils.response.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Resource(name = "com.product.luffy.service.impl.FileService")
    private FileService fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    ResponseObject<File> uploadFile(@RequestParam("image") MultipartFile multipartFile) {
        ResponseObject<File> responseObject = new ResponseObject<File>();
        LOGGER.debug(">>>>>>>>>> uploadFile 시작 " + multipartFile.getName() + ", " + multipartFile.getOriginalFilename());

        File file = fileService.insertFile(multipartFile);
        responseObject.setData(file);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return responseObject;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadFile(@RequestParam("fileId") String fileId) {

        File fileAuth = fileService.selectFileAuthYn(fileId);
        if(fileAuth == null || fileAuth.getFileAuthYn() == null) {
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "not download Auth");
        }

        java.io.File file;

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("fileId", fileId);
        File fileInfo = fileService.selectFile(paramMap);
        file = new java.io.File(fileInfo.getFilePath());

        byte[] fileContent = null;

        try {
            fileContent = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.IMAGE_JPEG);
        header.setContentLength(fileContent.length);

        return new HttpEntity<byte[]>(fileContent, header);
    }
}
