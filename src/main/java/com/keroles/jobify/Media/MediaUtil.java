package com.keroles.jobify.Media;

import com.keroles.jobify.Exception.Exceptions.Media.FileNotSupportedException;
import com.keroles.jobify.Exception.Exceptions.Media.MediaNotFoundException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Slf4j
@Data
public class MediaUtil {

    @Value("${file.base-path}")
    private  String BASE_PATH;
    @Value("${file.user.image-path}")
    private  String USER_IMAGE_PATH;
    @Value("${file.user.cv-path}")
    private  String USER_CV_PATH;
    @Value("${file.employer.image-path}")
    private  String EMPLOYER_IMAGE_PATH;
    @Value("${file.company.logo-path}")
    private  String COMPANY_LOGO_PATH;
    @Value("${file.company.background-path}")
    private  String COMPANY_BACKGROUND_PATH;
    @Value("${validate.message.media.file.server.failed}")
    private  String SERVER_FAILED;
    @Value("${validate.message.media.file.img.not_supported}")
    private  String IMG_NOT_SUPPORTED;

    protected enum FileState{
        UPLOAD_SUCCESS,
        UPLOAD_FAILED,
        FILE_NOT_SUPPORTED,
        SERVER_ERROR
    }
    protected byte[] prepareFileBytes(String filePath){
        Path file=new File(filePath).toPath();
        byte[] fileBytes;
        try {fileBytes=Files.readAllBytes(file);}
        catch (IOException exception) {throw new MediaNotFoundException();}
        return fileBytes;
    }
    protected File prepareFile(String filePath){
        File file=new File(filePath);
        log.error("prepareFile");
        return file;
    }
    protected String getUserImagePath(long userId){return BASE_PATH+ USER_IMAGE_PATH +"\\"+userId;}
    protected String getUserCvPath(long userId){return BASE_PATH+ USER_CV_PATH +"\\"+userId+"\\";}
    protected String getEmployerImagePath(long employerId){return BASE_PATH+ EMPLOYER_IMAGE_PATH +"\\"+employerId;}
    protected String getCompanyLogoPath(long companyId){return BASE_PATH+ COMPANY_LOGO_PATH +"\\"+companyId;}
    protected String getCompanyBackgroundPath(long companyId){return BASE_PATH+ COMPANY_BACKGROUND_PATH +"\\"+companyId;}
    protected Path getDirectory(String path){
        Path directory_path= Paths.get(path).toAbsolutePath().normalize();
        try {
            Files.createDirectories(directory_path);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new MediaNotFoundException(SERVER_FAILED);
        }
        return directory_path;
    }
    protected void checkImgType(String mediaExtension){
        if (mediaExtension.equals("png")
                || mediaExtension.equals("jpg")
                || mediaExtension.equals("jpeg"))return;
        throw new FileNotSupportedException(IMG_NOT_SUPPORTED);
    }
    protected boolean checkCvType(String mediaExtension){
        log.error("checkCvType  {}",mediaExtension);
        if (mediaExtension.equals("pdf")
                || mediaExtension.equals("docx")
                || mediaExtension.equals("dot")
                || mediaExtension.equals("dotx")
                || mediaExtension.equals("vnd.openxmlformats-officedocument.wordprocessingml.document"))return true;
        return false;
    }
    protected String getMediaExtension(String originalFilename){
        String[]imageType=originalFilename.split("\\.");
        if (imageType==null||imageType.length!=2)
            throw new FileNotSupportedException();

        return imageType[1];
    }
    protected HttpHeaders getFileHeaders(String fileName, String fileType, long contentLength){
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fileType));
        headers.setContentLength(contentLength);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);
        headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        headers.add(HttpHeaders.PRAGMA, "no-cache");
        headers.add(HttpHeaders.EXPIRES, "0");
        return headers;
    }


}
