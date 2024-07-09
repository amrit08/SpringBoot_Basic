package com.apj.electronic.store.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    public  String uploadFile(MultipartFile file , String path) throws IOException;

    public InputStream getResource(String path , String name) throws IOException;


}
