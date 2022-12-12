package com.keroles.jobify.Media;

import com.keroles.jobify.Exception.Exceptions.Media.FileNotSupportedException;
import com.keroles.jobify.Exception.Exceptions.Media.FileUploadFailedException;
import com.keroles.jobify.Exception.Exceptions.Media.MediaNotFoundException;
import com.keroles.jobify.Model.Entity.Media;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;

@Service
@Slf4j
public class MediaService {
    @Autowired
    private MediaUtil mediaUtil;
    @Value("${validate.message.media.file.img.not}")
    private  String IMG_NOT_FOUND;
    @Value("${validate.message.media.file.cv.not_supported}")
    private  String CV_NOT_SUPPORTED;
    @Value("${validate.message.media.file.cv.not}")
    private  String CV_NOT_FOUND;

    public enum ImageSection{
        USER_IMAGE,
        EMPLOYER_IMAGE,
        COMPANY_IMAGE,
        COMPANY_BACKGROUND
    }
    private Media storeFile(MultipartFile multipartFile, String path){
        Path directory_path=mediaUtil.getDirectory(path);
        String fileName= multipartFile.getOriginalFilename();
        Path fileFullPath=directory_path.resolve(fileName);
        log.error("dddddddddddddddddddddddd   {}",fileFullPath);
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileFullPath.toFile())) {
            fileOutputStream.write(multipartFile.getBytes());
            return Media.builder().name(fileName).type(multipartFile.getContentType()).build();
        }
        catch(Exception exception){
            log.error(exception.getMessage());
            throw new FileUploadFailedException();
        }

    }


    public Media storeUserImage(MultipartFile imageMultipartFile, long id,ImageSection imageSection){
        mediaUtil.checkImgType(mediaUtil.getMediaExtension(imageMultipartFile.getOriginalFilename()));
        Media stored_media=null;
        switch (imageSection){
            case USER_IMAGE:
                stored_media=storeFile(imageMultipartFile,mediaUtil.getUserImagePath(id));
                break;
            case EMPLOYER_IMAGE:
                stored_media=storeFile(imageMultipartFile,mediaUtil.getEmployerImagePath(id));
                break;
            case COMPANY_IMAGE:
                stored_media=storeFile(imageMultipartFile,mediaUtil.getCompanyLogoPath(id));
                break;
            case COMPANY_BACKGROUND:
                stored_media=storeFile(imageMultipartFile,mediaUtil.getCompanyBackgroundPath(id));
                break;
        }
        if(stored_media==null) throw new FileUploadFailedException();
        return stored_media;
    }
    public byte[] reStoreImage(long id,String imageName){
        String imageExtension=mediaUtil.getMediaExtension(imageName);
        mediaUtil.checkImgType(imageExtension);
        byte[] imageSource= mediaUtil.prepareFileBytes(mediaUtil.getUserImagePath(id)+"\\"+imageName);
        if (imageSource==null)
            throw new MediaNotFoundException(IMG_NOT_FOUND);
        return imageSource;

    }
    public Media storeCv(MultipartFile cvMultipartFile, long id){

        if (!mediaUtil.checkCvType(mediaUtil.getMediaExtension(cvMultipartFile.getOriginalFilename())))
            throw new FileNotSupportedException(CV_NOT_SUPPORTED);

        Media stored_media=storeFile(cvMultipartFile,mediaUtil.getUserCvPath(id));
        return stored_media;
    }

    public MediaFile downloadCv(long userId, String fileName, String fileType){
        String cvExtension=mediaUtil.getMediaExtension(fileName);
        if(!mediaUtil.checkCvType(cvExtension))
            throw new FileNotSupportedException(CV_NOT_SUPPORTED);
        String filePath=mediaUtil.getUserCvPath(userId)+fileName;
        MediaFile cvFile= MediaFile.builder()
                .file(mediaUtil.prepareFile(filePath))
                .fileBytes(mediaUtil.prepareFileBytes(filePath))
                .build();
        cvFile.setHeaders(mediaUtil.getFileHeaders(fileName,fileType,cvFile.getFile().length()));
        if (!cvFile.getFile().exists())
            throw new MediaNotFoundException(CV_NOT_FOUND);
        return cvFile;
    }




}
