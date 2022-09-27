package com.hybrid.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMarkService {
    void importToDb(List<MultipartFile> multipartfiles);
}
