package com.keroles.jobify.Media;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static com.keroles.jobify.Media.MediaUtil.FileState.*;

@Service
@Slf4j
public class MediaService {
    @Autowired
    private MediaUtil mediaUtil;


    private String storeFile(MultipartFile multipartFile, long id, String path){
        Path directory_path=mediaUtil.getDirectory(path);
        String file_Name= StringUtils.cleanPath(id+"_"+multipartFile.getOriginalFilename());
        Path file_full_path=directory_path.resolve(file_Name);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file_full_path.toFile())) {
            fileOutputStream.write(multipartFile.getBytes());
            return mediaUtil.getUploadState(UPLOAD_SUCCESS,file_full_path.toString());
        }
        catch(Exception exception){
            log.error(exception.getMessage());
            return mediaUtil.getUploadState(UPLOAD_FAILED,null);
        }

    }
    public String storeImage(MultipartFile multipartFile, long id){
        String stored_path=storeFile(multipartFile,id,mediaUtil.getImagePath());
        return stored_path;
    }


}
