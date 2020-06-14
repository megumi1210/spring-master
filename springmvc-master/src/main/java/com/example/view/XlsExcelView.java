package com.example.view;

import com.example.service.ExcelExportService;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class XlsExcelView extends AbstractXlsView {

    //文件名
    private  String filename = null;

    //导出视图自定义接口
    private ExcelExportService  excelExportService = null;

    public XlsExcelView(ExcelExportService excelExportService) {
        this.excelExportService = excelExportService;
    }

    public XlsExcelView(String viewName , ExcelExportService excelExportService){
        this.setBeanName(viewName);
        this.excelExportService = excelExportService;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //没有自定义接口
        if(excelExportService == null){
            throw  new RuntimeException("导出服务接口不能为空");
        }
        //文件名不为空,为空则使用请求路径中的字符串名作为文件名
        if(!StringUtils.isEmpty(filename)){
            //进行字符转换
            String reqCharset = request.getCharacterEncoding();
            reqCharset = reqCharset == null ? "UTF-8" :reqCharset;
            filename = new String(filename.getBytes(reqCharset), StandardCharsets.ISO_8859_1);
            //设置下面的文件名,提供下载
           // response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-disposition","attachment;filename="+ filename);

            //回调接口方法，使用自定义生成 Excel 文档
            excelExportService.makeWorkBook(model,workbook);
        }
    }

    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        return super.createWorkbook(model,request);
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ExcelExportService getExcelExportService() {
        return excelExportService;
    }

    public void setExcelExportService(ExcelExportService excelExportService) {
        this.excelExportService = excelExportService;
    }


}
