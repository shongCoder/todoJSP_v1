package org.example.w2.todo.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.C;
import org.example.w2.common.ConnectionUtil;
import org.example.w2.todo.TodoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public enum TodoDAO {

    INSTANCE;

    TodoDAO() {

    }

    public List<TodoVO> list(int page) throws Exception {

        log.info("List");

        //몇 번째 데이터 부터 시작해서 보여줄건지 값 계산
        // 1페이지면 1부터
        // 2페이지면 11부터
        // 3페이지면 21부터
        int skip = (page - 1) * 10;

        String query = """
                
                SELECT * FROM tbl_todo
                WHERE tno > 0
                AND delflag = 0
                ORDER BY tno desc
                LIMIT ?, 10
                
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        // skip 값을 offset에 대입
        ps.setInt(1, skip);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<TodoVO> todoList = new ArrayList<>();

        while (rs.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(rs.getInt("tno"))
                    .title(rs.getString("title"))
                    .writer(rs.getString("writer"))
                    .regDate(rs.getTimestamp("regdate"))
                    .modDate(rs.getTimestamp("moddate"))
                    .delFlag(rs.getBoolean("delflag"))
                    .build();
            todoList.add(vo);
        }// end while


        return todoList;
    }

    public Integer insert(TodoVO vo) throws Exception {
        log.info("insert");

        String sql = """
                INSERT INTO tbl_todo (title, writer)
                VALUES (?, ?)
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, vo.getTitle());
        ps.setString(2, vo.getWriter());

        int count = ps.executeUpdate();

        if(count != 1) {
            throw new Exception("Abnormal insertion");
        }// end if

        // 두 번째 쿼리를 위해 연결을 끊고 다시 쿼리 실행
        ps.close();
        ps = con.prepareStatement("SELECT LAST_INSERT_ID()");

        @Cleanup ResultSet rs = ps.executeQuery();
        rs.next();

        int tno = rs.getInt(1);

        return tno;
    }

    // Optional<T> : null이 올 수 있는 값을 감싸는 Wrapper 클래스
    public Optional<TodoVO> get(Integer tno) throws Exception {

        log.info("get");

        String query = """
                SELECT tno, title, writer, regdate, moddate, delflag
                FROM tbl_todo
                WHERE tno = ?
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, tno);
        @Cleanup ResultSet rs = ps.executeQuery();

        if( !rs.next() ) { // 다음 데이터가 없는 경우
            return Optional.empty(); // 빈 데이터
        }// end if

        TodoVO vo = TodoVO.builder()
                .tno(rs.getInt("tno"))
                .title(rs.getString("title"))
                .writer(rs.getString("writer"))
                .regDate(rs.getTimestamp("regdate"))
                .modDate(rs.getTimestamp("moddate"))
                .delFlag(rs.getBoolean("delflag"))
                .build();

        // 값이 반드시 존재해야 하는 경우
        return Optional.of(vo);
    }

    public boolean delete(Integer tno) throws Exception {

        log.info("delete");

        String sql = """
                UPDATE tbl_todo SET moddate = NOW(), delflag = TRUE
                WHERE tno = ?
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, tno);

        int count = ps.executeUpdate();

        // update sql문이 정상적으로 작동했는지 -> 1
        return count == 1;
    }

    public boolean update(TodoVO vo) throws Exception {

        log.info("update");

        String sql = """
                UPDATE tbl_todo
                SET title = ?, writer = ?, moddate = now()
                WHERE tno = ?
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, vo.getTitle());
        ps.setString(2, vo.getWriter());
        ps.setInt(3, vo.getTno());

        int count = ps.executeUpdate();

        return count == 1;
    }

    public int getTotal() throws Exception {

        log.info("getTotal");

        String query = "SELECT COUNT(tno) FROM tbl_todo WHERE tno > 0 AND delflag = FALSE";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        @Cleanup ResultSet rs = ps.executeQuery();

        rs.next();

        int total = rs.getInt(1);

        return total;
    }
}
