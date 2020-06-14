package com.example.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.Map;

/**
 *  导出 excel的服务类，抽象生成 excel 的文件规则
 */
public interface ExcelExportService {

    /**
     *  生成 excel 文件规则
     * @param model 数据模型
     * @param workbook excel workbook
     */
    public void  makeWorkBook(Map<String,Object> model , Workbook workbook);
}
