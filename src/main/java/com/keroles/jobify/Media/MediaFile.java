package com.keroles.jobify.Media;

import com.keroles.jobify.Model.Entity.Media;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

import java.io.File;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaFile {

    private File file;
    private byte[] fileBytes;
    private HttpHeaders headers;
}
