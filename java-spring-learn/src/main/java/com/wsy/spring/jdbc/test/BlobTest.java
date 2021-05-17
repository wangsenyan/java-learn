package com.wsy.spring.jdbc.test;

import com.wsy.spring.jdbc.util.JDBCUtils;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * max_allowed_packet=16M mysql.ini
 * TinyBlob 255
 * Blob 65k
 * MediumBlob 16M
 * LongBlob 4G
 */
public class BlobTest {
    public int testInsert(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "insert into ums_admin(username,picture) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,"朱茵");
            FileInputStream is = new FileInputStream(new File("C:\\Users\\wjm\\Pictures\\d.jpg"));
            ps.setBlob(2,is);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,ps);
        }
        return 0;
    }
    public void testSelect(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            String sql = "select username,picture from ums_admin where id = ? ";
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,12);
            rs = ps.executeQuery();
            if(rs.next()){
                String username = rs.getString(1);
                System.out.println(username);
                //将blob类型的字段下载下来
                Blob picture = rs.getBlob("picture");
                is = picture.getBinaryStream();
                //FileChannel channel = new FileOutputStream(new File("C:\\Users\\wjm\\Pictures\\explain_down.png")).getChannel();
                os = new FileOutputStream(new File("C:\\Users\\wjm\\Pictures\\explain_down.png"));
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer))!=-1)
                    os.write(buffer,0,len);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,ps,rs);
            try {
                if(is!=null)
                   is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(os!=null)
                   os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new BlobTest().testSelect();
    }
}
