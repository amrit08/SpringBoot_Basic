package com.apj.electronic.store.services.impl;

import com.apj.electronic.store.exceptions.BadApiRequest;
import com.apj.electronic.store.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);


    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {

        //abc.png
        String originalFilename = file.getOriginalFilename();
        logger.info("Filename :{}",originalFilename);
        String fileName = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithExtension = fileName+extension;
        String fullPathWithFileName = path+fileNameWithExtension;
        logger.info("full image path :{}",fullPathWithFileName);

        if (extension.equalsIgnoreCase(".png")||extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg"))
        {
            //file save
            logger.info("file extension is {}",extension);
            File folder = new File(path);
            if (!folder.exists())
            {
                //create the folder
                folder.mkdirs();

            }
            //upload file
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            return  fileNameWithExtension;

        }
        else
        {
            throw new BadApiRequest("File with this "+extension+" not allowed");

        }

    }

    @Override
    public InputStream getResource(String path, String name) throws IOException {

        String fullPath= path+File.separator+name;
        InputStream inputStream = Files.newInputStream(Path.of(fullPath));
        return inputStream ;
    }
}
