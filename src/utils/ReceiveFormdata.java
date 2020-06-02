package utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ReceiveFormdata {

	public ReceiveFormdata() {}
	
	/**
     * @param request
     * 根据请求解析请求中的参数(文件与非文件)返回Map集合,并将文件上传至服务器
     * @return
     */
	@SuppressWarnings("rawtypes")
	public static Map getFilePath(HttpServletRequest request,String preName,String directory,String stuId) {
        String upload_directory = "C:/Users/18237/Desktop/temp/"+stuId+"/"+directory;
        String slash_directory = "/";
        
        // 上传配置
        int memory_threshold   = 1024 * 1024 * 3;  // 3MB
        int max_file_size      = 1024 * 1024 * 40; // 40MB
        int max_request_size   = 1024 * 1024 * 50; // 50MB
 
 
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(memory_threshold);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置最大文件上传值
        upload.setFileSizeMax(max_file_size);
 
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(max_request_size);
 
        upload.setHeaderEncoding("UTF-8");
 
        String uploadPath = upload_directory;
        String fileName="";
        String filePath="";
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        Map<String,Object> paramMap = new HashMap<String,Object>();
 
        try {
            // 解析请求的内容提取文件数据
            //@SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                    	String imgName = new File(item.getName()).getName();//获得上传的文件名
                    	//以当前时间戳作为图片名
                    	if(imgName==null || imgName.isEmpty()) {
                    		
                    	}else {
                    		fileName = preName+imgName.substring(imgName.indexOf("."), imgName.length());
                            filePath = uploadPath +  slash_directory +fileName;
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                    	}
                        paramMap.put("fileName", fileName);
                        paramMap.put("filePath", filePath);
                        System.out.println(item.getName());
                    }else {
                        String value = item.getString("utf-8");
                        System.out.println(item.getFieldName());
                        paramMap.put(item.getFieldName(), value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paramMap;
    }

}
