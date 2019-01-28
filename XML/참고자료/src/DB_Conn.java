public class DB_Conn {

	java.sql.Connection	conn;
	java.sql.Statement	stmlt;
	java.sql.ResultSet	rs;

	DB_Conn(){
		connect();
	}

	//연결 - 자동 실행
	void connect() {

		// 사용환경 별 연결 포트 및 ID/PW 확인
		String dbInfo = "jdbc:mysql://localhost:3306/xml_project";
		String dbID = "root";
		String dbPW = "1234";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = java.sql.DriverManager.getConnection(dbInfo, dbID, dbPW);
			this.stmlt = this.conn.createStatement();
		}
		catch(Exception e) {
			System.out.println("connection error:"+e);
		}
	}

	//수정 시 사용
	void update(String dbCommand) {
		try {
			this.stmlt.executeUpdate(dbCommand);
			System.out.println("- Update Success -");
		}
		catch(Exception e) {
			System.out.println("update error:"+e);
		}
	}

	//삭제 시 사용
	void delete(String dbCommand) {
		try {
			this.stmlt.executeUpdate(dbCommand);
			System.out.println("- Delete Success -");
		}
		catch(Exception e) {
			System.out.println("delete error:"+e);
		}
	}

	//검색 시 사용
	void select(String dbCommand) {
		try {
			this.rs = this.stmlt.executeQuery(dbCommand);
		}
		catch(Exception e) {
			System.out.println("select error:"+e);
		}
	}


	void close() {
		try {
			conn.close();
		}
		catch(Exception e) {
			System.out.println("close error:"+e);
		}
	}
}
