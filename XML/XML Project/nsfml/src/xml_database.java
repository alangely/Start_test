public class xml_database {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//��ü ����
		DB_Conn dc = new DB_Conn();
		
		//update
		dc.update("insert into recipe values('fname', 'ffeature', 'flocation', 'fmater', 'fnum', 'frecipe', 'flocationx', 'flocationy')");	
		//delete
		//dc.delete("delete from xmldb where id='silencehell'");	
		//select
		dc.select("select * from recipe");
	
		try {
			//Ŀ�� ��ġ �ֻ������ ����
			dc.rs.beforeFirst();
			
			//���ڵ� �˻�(�����Ͱ� �ִ� ��쿡�� ����)
			while(dc.rs.next()) {
				System.out.println("- Find result -");
				System.out.println("ID : " + dc.rs.getString("fname"));
				System.out.println("PW : " + dc.rs.getString("ffeature"));
			}
		}
		catch(Exception e) {
			System.out.println("getString error:"+e);
		}
		
		//���� ����
		dc.close();
	}
}