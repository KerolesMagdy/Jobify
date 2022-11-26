package com.keroles.jobify.Media;

import com.keroles.jobify.Exception.Exceptions.Media.FileNotSupportedException;
import com.keroles.jobify.Exception.Exceptions.Media.FileUploadFailedException;
import com.keroles.jobify.Exception.Exceptions.Media.MediaServerException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.keroles.jobify.Media.MediaUtil.FileState.SERVER_ERROR;

@Component
@Slf4j
@Data
public class MediaUtil {

    @Value("${file.base-path}")
    private  String BASE_PATH;
    @Value("${file.user.image-path}")
    private  String IMAGE_PATH;
    protected enum FileState{
        UPLOAD_SUCCESS,
        UPLOAD_FAILED,
        NOT_SUPPORTED,
        SERVER_ERROR
    }
    protected String getImagePath(){return BASE_PATH+IMAGE_PATH;}
    protected Path getDirectory(String path){
        Path directory_path= Paths.get(path).toAbsolutePath().normalize();
        try {
            Files.createDirectories(directory_path);
        } catch (IOException e) {
            log.error(e.getMessage());
            getUploadState(SERVER_ERROR,null);
        }
        return directory_path;
    }
    protected String getUploadState(FileState fileState,String file_path){
        switch (fileState){
            case NOT_SUPPORTED:
                throw new FileNotSupportedException();
            case UPLOAD_FAILED:
                throw new FileUploadFailedException();
            case SERVER_ERROR:
                throw new MediaServerException();
            case UPLOAD_SUCCESS:

                return file_path;
        }
        return null;
    }
}
