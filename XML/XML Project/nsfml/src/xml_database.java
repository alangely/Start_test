public class xml_database {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//객체 선언
		DB_Conn dc = new DB_Conn();
		
		//update
		dc.update("insert into recipe values('fname', 'ffeature', 'flocation', 'fmater', 'fnum', 'frecipe', 'flocationx', 'flocationy')");	
		//delete
		//dc.delete("delete from xmldb where id='silencehell'");	
		//select
		dc.select("select * from recipe");
	
		try {
			//커서 위치 최상단으로 변경
			dc.rs.beforeFirst();
			
			//레코드 검색(데이터가 있는 경우에만 적용)
			while(dc.rs.next()) {
				System.out.println("- Find result -");
				System.out.println("ID : " + dc.rs.getString("fname"));
				System.out.println("PW : " + dc.rs.getString("ffeature"));
			}
		}
		catch(Exception e) {
			System.out.println("getString error:"+e);
		}
		
		//연결 해제
		dc.close();
	}
}