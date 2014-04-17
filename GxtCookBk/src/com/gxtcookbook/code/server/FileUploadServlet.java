package com.gxtcookbook.code.server;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

public class FileUploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 4680988538693461038L;
	private final Logger logger = Logger.getLogger(FileUploadServlet.class);
	
	public FileUploadServlet(){
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String status = "No Uploads !";
		
		if(ServletFileUpload.isMultipartContent(req)){
			try{
				FileItemFactory fileItemFactory = new DiskFileItemFactory();
                ServletFileUpload uploadHandlr = new ServletFileUpload(fileItemFactory);
                List<FileItem> uploadItems = uploadHandlr.parseRequest(req);
                handleFile(uploadItems, req.getSession());
                status = "Done Uploading " + uploadItems.get(0).getName();
                logger.info(status);
			} catch (FileUploadException ex) {
	            status = ex.getMessage();
	            logger.error(ex.getMessage());
	        } catch (Exception ex){
	            status = ex.getMessage();
	            logger.error(ex.getMessage());
	        }			
		}
		resp.getWriter().print(status);
        super.doPost(req, resp);		
	}

    private boolean ensureFilesDir(String path){
        File dir = new File(path);
        boolean status = dir.exists();
        if(!status){
            status = dir.mkdirs();
        }
        return status;
    }
	
	public void handleFile(List<FileItem> fileItems, HttpSession session) throws Exception {
		String filePath = "";
        String fileSeparator = System.getProperty("file.separator");
        String basepath = "files" + fileSeparator + "gtxuploads";
        String filesDir = session.getServletContext().getRealPath(basepath);		
		
		SimpleDateFormat fmt = new SimpleDateFormat("MMM-yyyy");
        String datePrefix = fmt.format(new Date());
		        
		File file = null;
		ensureFilesDir(filesDir);
		for (FileItem fileItem : fileItems) {
			if(!fileItem.isFormField()){
				filePath = filesDir + fileSeparator + datePrefix + "_" + fileItem.getName();
	            file = new File(filePath);
	            fileItem.write(file);
			}	            
		}
	}

}
