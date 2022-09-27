package com.hybrid.controllers.admins;

import com.hybrid.services.IMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping(path = "/marks")
public class ImportController {

    @Autowired
    private IMarkService iMarkService;

    @PostMapping(path = "/import-to-db")
    public void importTransactionsFromExcelToDb(@RequestPart(required = true) List<MultipartFile> files) {
        iMarkService.importToDb(files);

    }
}
