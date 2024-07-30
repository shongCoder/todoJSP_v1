package org.example.w2.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.w2.common.PageInfo;
import org.example.w2.common.StringUtil;
import org.example.w2.todo.dao.TodoDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("doGet list");

        // get방식으로 전달받은 페이지값(파라미터) 할당
        String pageStr = req.getParameter("page");

        log.info("pageStr: " + pageStr);

        // 문자열인 파라미터 int로 변형
        int page = StringUtil.getInt(pageStr, 1);

        try {

            // 총 데이터 건수
            int total = TodoDAO.INSTANCE.getTotal();

            // 페이징에 필요한 정보 받기
            // page, start, end, prev, next
            PageInfo pageInfo = new PageInfo(page, 10, total);

            // 목록 데이터 받기
            List<TodoVO> todoList = TodoDAO.INSTANCE.list(page);

            // todo 리스트 jsp로 전송
            req.setAttribute("list", todoList);

            // 페이징에 필요한 정보 jsp로 전송
            req.setAttribute("pageInfo", pageInfo);

            req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);

            log.info("todoList; " + todoList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }// end catch

    }
}
