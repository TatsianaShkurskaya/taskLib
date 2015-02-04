package connectionTask;

public class DoTask {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		ConnectFtp connection = new ConnectFtp();
		connection.connectFtp();
		connection.useFtp();
		connection.disconnectFtp();
	}

}
