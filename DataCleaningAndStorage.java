import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.FileReader;
import java.sql.PreparedStatement;
public class DataCleaningAndStorage{
	public static void main(String[] args)throws java.sql.SQLException,java.io.IOException{
		String file = "C:\\Users\\91992\\OneDrive\\Desktop\\JDBC\\Garvage Data\\breast-cancer.arff";
		String jdbcURL = "jdbc:mysql://localhost:3306/CleanDataStorage";
		String userID = "root";
		String pass = "root";
		
		 Connection con = null;
		 PreparedStatement stmt = null;
		try{
			 con = DriverManager.getConnection(jdbcURL,userID,pass);
			BufferedReader data = new BufferedReader(new FileReader(file));
			
			String line;
			while((line = data.readLine())!=null){
				if(line.equalsIgnoreCase("@data")){
					String line1;
					while((line1 = data.readLine())!=null){
						String data1 = line1.replace("'","");
						String[] str = data1.split(",");
						
						 String sql = "INSERT INTO breast_cancer VALUES(?,?,?,?,?,?,?,?,?,?)";

						stmt = con.prepareStatement(sql);
						for(int i=0;i<str.length;i++){
							stmt.setString(i+1,str[i]);

						}
							stmt.executeUpdate();
					}
				}
			}
		}
		finally{
			stmt.close();
			con.close();
		}
	}
	
}