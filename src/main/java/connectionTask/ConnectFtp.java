package connectionTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ConnectFtp {

		private FTPClient client = new FTPClient();
		private String filename = "ftp.mozilla.org";

		public void connectFtp(){
			try{
				client.connect(filename);
				boolean login = client.login("anonymous", "anonymous");

				if (login) {
					System.out.println("Login success...");
					System.out.println("Print 'STOP' to finish work\nPrint 'BACK' to see the previous directory\n");
				} else {
					System.out.println("Login fail...");
				}
		    
				client.enterLocalPassiveMode();
				client.setFileType(FTP.BINARY_FILE_TYPE);
			}catch(IOException e){
					System.out.println("Unable to connect FTP");
			}
		}
		
		public void useFtp() {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        ArrayList<FTPFile> files = new ArrayList<FTPFile>();
	        String x = "";
	        
	        try{
	        	do{
	        		files = FindFiles.listFilesWithFolders(client,x);
	        		System.out.println("Choose directory or file (with its expansion)...");
	        		x = br.readLine();//?????????????????
	        		if (x.equals("BACK")){
	        			client.changeToParentDirectory();
	        			x = client.printWorkingDirectory();
	        			continue;
	        		}
	        	
	        		for (FTPFile file: files){
	        			if (file.getName().equals(x))
	        				if (file.isDirectory())
	        					client.changeWorkingDirectory(file.getName());
	        				else	
	        					FindFiles.downloadFile(client, x);
	        		}
	        	}while(!x.equals("STOP"));
	        	
	        	br.close();
	        }catch(IOException e){
	        	System.out.println("Unable to connect FTP");
	        }
		}
		
		public void disconnectFtp(){
			try{
				boolean logout = client.logout();
				if (logout) {
					System.out.println("Logout from FTP server...");
				}
				client.disconnect();
			}catch(IOException e){
	        	System.out.println("Unable to connect FTP");
	        }
        }
}
