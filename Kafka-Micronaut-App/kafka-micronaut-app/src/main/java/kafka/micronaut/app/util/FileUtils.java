package kafka.micronaut.app.util;

import io.micronaut.http.multipart.CompletedFileUpload;
import kafka.micronaut.app.enums.FileType;
import kafka.micronaut.app.service.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class FileUtils {

    protected static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    /**
     * Validates file type based on the file name
     * @param fileName
     * @return
     */
    public boolean validateFileType(String fileName) {
        String ext = fileName.substring(fileName.length() - 3);
        LOG.info("File extention:" + ext);

        for(FileType type : FileType.values()) {
            if (type.getValue().equalsIgnoreCase(ext)) {
                return true;
            }
        }
        return false;
    }
}
