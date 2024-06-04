package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WriteDAO {
	// dao : �����ͺ��̽� ���� ��ü�� ���ڷμ�

	// ���������� db���� ȸ������ �ҷ����ų� db�� ȸ������ ������

	private Connection conn; // connection:db�������ϰ� ���ִ� ��ü
	private PreparedStatement pstmt;
	private ResultSet rs;

	// mysql�� ������ �ִ� �κ�

	public WriteDAO() { // ������ ����ɶ����� �ڵ����� db������ �̷�� �� �� �ֵ�����

		try {

			String dbURL = "jdbc:mysql://localhost:3306/test"
					+ ""; // localhost:3306 ��Ʈ�� ��ǻ�ͼ�ġ�� mysql�ּ�
			String dbID = "root";
			String dbPassword = "20163291";
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);

		} catch (Exception e) {
			e.printStackTrace(); // ������ �������� ���
		}
	}
		
		public int write(User user) {

			String SQL = "INSERT INTO test.write VALUES (?,?,?,sysdate)";

			try {

				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, user.getTrip());
				pstmt.setString(2, user.getTitle());
				pstmt.setString(3, user.getMemo());

				return pstmt.executeUpdate();

			} catch (Exception e) {

				e.printStackTrace();

			}

			return -1; // DB ����

	}
}