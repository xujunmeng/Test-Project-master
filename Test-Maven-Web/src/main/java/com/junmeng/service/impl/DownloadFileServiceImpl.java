package com.junmeng.service.impl;

import com.junmeng.service.BaseService;
import com.junmeng.service.DownloadFileService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by junmeng.xu on 2016/12/2.
 */
public class DownloadFileServiceImpl extends BaseService implements DownloadFileService {

    @Override
    public String excelFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String filePath = "D:\\test.xlsx";

        File file = new File(filePath);

        response.reset();

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");

        response.addHeader("Content-Disposition", "attachment; filename=abc.xlsx");

        int fileLength = (int)file.length();

        response.addHeader("Content-Length", "" + fileLength);

        response.setContentLength(fileLength);

        InputStream inStream = new FileInputStream(file);
        byte[] buf = new byte[4096];
        ServletOutputStream servletOS = response.getOutputStream();
        int readLength;
        while (((readLength = inStream.read(buf)) != -1)) {
            servletOS.write(buf, 0, readLength);
        }
        inStream.close();
        servletOS.flush();
        servletOS.close();

        return null;
    }
}
