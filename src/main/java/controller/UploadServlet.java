package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		  maxFileSize = 1024 * 1024 * 10, // 10MB
		  maxRequestSize = 1024 * 1024 * 50 // 50MB
		)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// This is the directory where uploaded files will be saved.
	// Change this to match the directory path on your server.
	private static final String SAVE_DIR = "Resources";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html;charset=UTF-8");

	    // Create a directory for saving the uploaded file
	    String appPath = request.getServletContext().getRealPath("");
	    String savePath = appPath + File.separator + SAVE_DIR;
	    File fileSaveDir = new File(savePath);
	    if (!fileSaveDir.exists()) {
	        fileSaveDir.mkdir();
	    }

	    // Get the file part from the request
	    Part filePart = request.getPart("file");

	    // Get the name of the uploaded file
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	    
	    //String fileName = "123.png";
	    // Create a file object for the uploaded file
	    File file = new File(savePath + File.separator + fileName);

	    // Delete the file if it already exists
	    if (file.exists()) {
	        Files.delete(file.toPath());
	    }

	    // Save the file to the file system
	    filePart.write(file.getAbsolutePath());

	    // Send a response back to the client
	    PrintWriter out = response.getWriter();
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>File Uploaded</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>File Uploaded Successfully</h1>");
	    out.println("</body>");
	    out.println("</html>");
	}

}
