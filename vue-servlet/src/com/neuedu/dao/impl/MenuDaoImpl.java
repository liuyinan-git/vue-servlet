package com.neuedu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.IMenuDao;
import com.neuedu.exception.DbException;
import com.neuedu.pojo.MenuData;
import com.neuedu.pojo.SysMenu;
import com.neuedu.utils.DButil;

public class MenuDaoImpl implements IMenuDao {

	private String parentId;



	@Override
	public int count() throws DbException, SQLException, ClassNotFoundException {
		int count=0;
		   Connection conn=DButil.getConnection();
		   PreparedStatement ps=null;
		   ResultSet rs=null;
		    try {
		        ps=conn.prepareStatement("select count(*) from t_menu");
		        rs=ps.executeQuery();
		        if(rs.next()){//如果有该用户
		        	count=rs.getInt(1);
		           
		        }
		    } catch (SQLException e) {

		        e.printStackTrace();
		    }
		    DButil.closeDB(rs, ps, conn);
		    return count;
	}

	

	@Override
	public List<SysMenu> findAll(int pageSize, int currentPage)
			throws DbException, SQLException, ClassNotFoundException {
		//调用jdbc程序进行存储
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<SysMenu> menuList=new ArrayList();
		try {
			//加载驱动，定义连接参数，建立连接
			conn=DButil.getConnection();
			//预处理
			ps=conn.prepareStatement("select * from t_menu limit ?,?");
			ps.setInt(1, pageSize*(currentPage-1));
			ps.setInt(2, pageSize);
			rs=ps.executeQuery();
			//循环结果集，多条用while
			while(rs.next()){
				//循环到一条，就新实例化一个user
			    SysMenu dbmenu=new SysMenu();
			    dbmenu.setIcon(rs.getString("icon"));
			    dbmenu.setMenuId(rs.getInt("menuId"));
			    dbmenu.setMenuName(rs.getString("menuName"));
			    dbmenu.setOrderNum(rs.getString("orderNum"));
			    dbmenu.setParentId(rs.getInt("parentId"));
			    dbmenu.setParentName(rs.getString("parentName"));
			    dbmenu.setPath(rs.getString("path"));
			    dbmenu.setVisible(rs.getString("visible"));
				//把user放入集合
				menuList.add(dbmenu);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//异常详情，给程序看的
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			
		}
		finally {
			DButil.closeDB(rs, ps, conn);
		}
		return menuList;
}



	@Override
	public List<SysMenu> getMenuList(String parentId) throws DbException, SQLException, ClassNotFoundException {
		List<SysMenu> list=new ArrayList();
		   Connection conn=DButil.getConnection();
		   PreparedStatement ps=null;
		   ResultSet rs=null;
		    try {
		        ps=conn.prepareStatement("select * from t_menu where visible=0 and  parentId=?");
		        ps.setInt(1, Integer.parseInt(parentId));
		        rs=ps.executeQuery();
		        while(rs.next()){//如果有该用户
		        	
		        	SysMenu obj=new SysMenu();
		        	obj.setMenuId(rs.getInt("menuId"));
		        	obj.setMenuName(rs.getString("menuName"));
		        	obj.setPath(rs.getString("path"));
		        	obj.setIcon(rs.getString("icon"));
		        	obj.setVisible(rs.getString("visible"));
		        	obj.setParentId(rs.getInt("parentId"));
		            obj.setParentName(rs.getString("parentName"));
		            obj.setOrderNum(rs.getString("orderNum"));
		        	list.add(obj);
		        }
		    } catch (SQLException e) {

		        e.printStackTrace();
		    }
		    DButil.closeDB(rs, ps, conn);
		    return list;
	}



	@Override
	public List<MenuData> getMenuDataList(String parentId) throws ClassNotFoundException, SQLException {
		List<MenuData> list=new ArrayList();
		   Connection conn=DButil.getConnection();
		   PreparedStatement ps=null;
		   ResultSet rs=null;
		    try {
		        ps=conn.prepareStatement("select * from t_menu where visible=0 and  parentId=?");
		        ps.setInt(1, Integer.parseInt(parentId));
		        rs=ps.executeQuery();
		        while(rs.next()){//如果有该用户
		        	MenuData obj=new MenuData();
		        	obj.setId(rs.getInt("menuId"));
		        	obj.setLabel(rs.getString("menuName"));
		        	obj.setName(rs.getString("menuName"));
		        	list.add(obj);
		        }
		    } catch (SQLException e) {

		        e.printStackTrace();
		    }
		    DButil.closeDB(rs, ps, conn);
		    return list;
	}



	@Override
	public int save(SysMenu sm) throws DbException, SQLException, ClassNotFoundException {
		//调用jdbc程序进行存储
				Connection conn = null;
				PreparedStatement ps = null;
				try {
					//加载驱动，定义连接参数，建立连接
					conn=DButil.getConnection();
					//预处理
					ps = conn.prepareStatement("insert into t_menu values(null,?,?,?,?,?,?,?)");
					ps.setString(1, sm.getMenuName());
					ps.setString(2, sm.getPath());
					ps.setString(3, sm.getIcon());
					ps.setString(4, sm.getVisible());
					ps.setInt(5, sm.getParentId());
					ps.setString(6, sm.getParentName());
					ps.setString(7, sm.getOrderNum());					
					int num=ps.executeUpdate();
					return num;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();//异常详情，给程序看的
					throw new DbException("驱动错误",999);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DbException("数据库操作异常",999);
					
				}
				finally {
					DButil.closeDB(null, ps, conn);
				}
			}






	













	

}
