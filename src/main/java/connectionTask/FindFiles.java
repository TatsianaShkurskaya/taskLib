package connectionTask;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FindFiles {

	public static ArrayList<FTPFile> listFilesWithFolders(FTPClient client, String str){
		
		ArrayList<FTPFile> files = new ArrayList<FTPFile>();
		try{
			for (FTPFile file : client.listFiles()) {
		        	if (file.isDirectory()){
		        		System.out.println("FTPDir: " + "/"+ file.getName());
		        	}
		        	else {
		        		System.out.println("FTPFile: " + file.getName() + "; "
				            + FileUtils.byteCountToDisplaySize(file.getSize()));
					}
		        	files.add(file);
		 	}
		}catch(IOException e){
			System.out.println("Unable to connect FTP");
		}
	    return files;
	}
	
	public static void downloadFile(FTPClient client, String str){
		FileOutputStream fos;
		try{
			fos = new FileOutputStream(str);
			client.retrieveFile(str, fos);
		    System.out.println("SUCCESSFUL DOWNLOADING");
		    fos.close();
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		catch(IOException e){
			System.out.println("Unable to connect FTP");
		}
	}
	
}

