package com.hybird.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UpLoadFileUtils {

    public File handleUploadFile(MultipartFile uploadFile) throws IOException {
        File myUploadFolder = new File("/home/duchv/Desktop/duchv1_java_students/src/main/resources/static/storage");
        if (!myUploadFolder.exists()) { // ktra sự tồn tại của folder
            myUploadFolder.mkdirs(); // tạo mới nếu k tồn tại
        }
        // lưu file vào thư mục đã chọn
        File saveFile = null;
        try {
            String uuid= UUID.randomUUID().toString();
            String fileName= uuid+"_"+uploadFile.getOriginalFilename();
            saveFile = new File(myUploadFolder, uploadFile.getOriginalFilename());
            uploadFile.transferTo(saveFile);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return saveFile;
    }
}
