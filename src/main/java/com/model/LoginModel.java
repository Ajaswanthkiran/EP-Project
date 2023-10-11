package com.model;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import com.entity.Activity;
import com.entity.Login;
import com.entity.Notice;
import com.entity.Registration;


@Stateless
public class LoginModel implements DataBase{

	@Override
	public String login(Login l) {
		// TODO Auto-generated method stub
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
    
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ajk", "root", "1234");
			
            String sql = "SELECT * FROM project WHERE username = ? OR email=? AND password = ?";
             stmt = conn.prepareStatement(sql);
            stmt.setString(1, l.getUsername());
            stmt.setString(2, l.getUsername());
            stmt.setString(3, l.getPassword());
             rs = stmt.executeQuery();

            if (rs.next()) {
                return "home.xhtml";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", null));
                return null;
            }
        } catch (Exception e) {
            // Handle any errors
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error occurred", null));
            return null;
        } finally {
            // Close connections
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
	}

	@Override
	public String reg(Registration r) {
		 Connection conn = null;
	        PreparedStatement stmt = null;

	        try {
	            // Perform database interaction
	        	Class.forName("com.mysql.cj.jdbc.Driver");
				
				
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ajk", "root", "1234");
				
				
	            String sql = "INSERT INTO project (username,email, password) VALUES (?,?, ?)";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, r.getUsername());
	            stmt.setString(2, r.getEmail());
	            stmt.setString(3, r.getPassword());
	            stmt.executeUpdate();

	            return "reguss.xhtml";
	        } catch (Exception e) {
	            // Handle any errors
	            e.printStackTrace();
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration failed", null));
	            return null;
	        } finally {
	            // Close connections
	            try {
	                if (stmt != null) {
	                    stmt.close();
	                }
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	}

	@Override
	public String activity(Activity a) {
		Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Perform database interaction
        	Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ajk", "root", "1234");
			
			
			String sql = "INSERT INTO project_Activity (name,img,desp,loc) VALUES (?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, a.getName());
            stmt.setString(3, a.getDec());
            InputStream fis=  a.getImg().getInputStream();
//    		
            stmt.setBinaryStream(2, fis,fis.available());
            stmt.setString(4,a.getLoc());
            stmt.executeUpdate();

            return "Activity Updated";
        } catch (Exception e) {
            // Handle any errors
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration failed", null));
            return null;
        } finally {
            // Close connections
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	@SuppressWarnings("resource")
	@Override
	public List<Activity> getSportsActivities() {
        List<Activity> activities = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
      
        try {
            // Perform database interaction
        	Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ajk", "root", "1234");
			
			
            String query = "SELECT * FROM project_Activity";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int i=1;
            // Process the result set
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("desp");
                String loc = resultSet.getString("loc");
                Blob blob = resultSet.getBlob("img");
                byte byteArray[] = blob.getBytes(1,(int)blob.length());
                FileOutputStream outPutStream = new FileOutputStream("C:/Users/16307/workspace/AjkProject/src/main/webapp/image/"+i+".jpg");
                outPutStream.write(byteArray);
                Activity a = new Activity();
                a.setImgPath("image/"+i+".jpg");
                a.setDec(description);
                a.setLoc(loc);
                a.setName(name);
                activities.add(a);
                i++;
            }

            // Close the database resources
            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return activities;
    }
	
	@Override
	public String notice(Notice n) {
		Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Perform database interaction
        	Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ajk", "root", "1234");
			
			
			String sql = "INSERT INTO project_notices (notice,des) VALUES (?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, n.getNotice());
            
            stmt.setString(2, n.getDes());
            
            stmt.executeUpdate();

            return "Notice Inserted";
        } catch (Exception e) {
            // Handle any errors
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notice not inserted", null));
            return null;
        } finally {
            // Close connections
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

	@Override
	public List<Notice> getNotices() {
		List<Notice> notice = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
      
        try {
            // Perform database interaction
        	Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ajk", "root", "1234");
			
			
            String query = "SELECT * FROM project_notices";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int i=1;
            // Process the result set
            while (resultSet.next()) {
                String note = resultSet.getString("notice");
                String description = resultSet.getString("des");
                
                
               
                Notice a = new Notice();
                
                a.setDes(description);
                
                a.setNotice(note);
                notice.add(a);
               
            }

            // Close the database resources
            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return notice;
	}

}

	
