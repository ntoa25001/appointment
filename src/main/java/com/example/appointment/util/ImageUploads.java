package com.example.appointment.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ImageUploads {

        private final String UPLOAD_FOLDER = "E:\\appointment\\appointment\\src\\main\\resources\\static\\image";

        public boolean uploadImage(MultipartFile imageDoctor){
            boolean isUpload = false;
            try {
                Files.copy(imageDoctor.getInputStream(),
                        Paths.get(UPLOAD_FOLDER + File.separator, imageDoctor.getOriginalFilename()),
                        StandardCopyOption.REPLACE_EXISTING);
                isUpload = true;

            }catch (Exception e){
                e.printStackTrace();
            }
            return isUpload;
        }

        public boolean checkExisted(MultipartFile imageDoctor){
            boolean isExisted = false;
            try {
                File file = new File(UPLOAD_FOLDER + "\\" + imageDoctor.getOriginalFilename());
                isExisted = file.exists();
            }catch (Exception e){
                e.printStackTrace();
            }
            return isExisted;
        }
    }

