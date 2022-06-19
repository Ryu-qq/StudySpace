package hello.jdbc.repository;


import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

/**
 * jdbc Driver Manager 사용
 */

@Slf4j
public class MemberRepositoryV0 {

    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values (?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate();
            return member;
        }catch (SQLException e){
            log.error("db error", e);
            throw e;
        }finally {
            close(con, pstmt, null);


        }
    }

    public Member findById(String memberId) throws SQLException {
        String sql = "select * from member where member_id =?";

        Connection con = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {
            con= getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);

            rs= pstmt.executeQuery();
            if(rs.next()){
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            }else{
                throw new NoSuchElementException("member not found memberId =" + memberId);
            }
        }catch (SQLException e){
            log.error("dv error", e);
            throw e;
        }finally {
            close(con, pstmt, rs);
        }
    }

    public void update(String memberId, int money) throws SQLException {
        String sql = "update member set money=? where member_id =?";

        Connection con = null;
        PreparedStatement pstmt =null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);

            int resultSize =pstmt.executeUpdate();
            log.info("resultSize ={}" + resultSize );

        }catch (SQLException e){
            log.error("db error", e);
            throw e;
        }finally {
            close(con, pstmt, null);
        }
    }

    public void delete(String memberId) throws SQLException {
        String sql = "delete member where member_id =?";

        Connection con = null;
        PreparedStatement pstmt =null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();

        }catch (SQLException e){
            log.error("db error", e);
            throw e;
        }finally {
            close(con, pstmt, null);
        }
    }


    private void close(Connection con, Statement statement, ResultSet rs){

        if(rs !=null){
            try {
                rs.close();
            }catch (SQLException e){
                log.info("error", e);
            }
        }

        if(statement != null){
            try {
                statement.close();
            }catch (SQLException e){
                log.info("error", e);
            }
        }

        if(con != null){
            try {
                con.close();

            }catch (SQLException e){
                log.info("error", e);

            }
        }

        if(con != null){
            try {
                con.close();

            }catch (SQLException e){
                log.info("error", e);

            }
        }

    }

    public Connection getConnection(){
        return DBConnectionUtil.getConnection();
    }
}
