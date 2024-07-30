package org.example.w2.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.w2.common.StringUtil;
import org.example.w2.todo.dao.TodoDAO;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = {"/todo/get", "/todo/edit"})
@Log4j2
public class TodoGetEditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet get");

        // 클라이언트가 요청한 URI를 반환한다
        String uri = req.getRequestURI();

        // /todo/get 마지막 슬래쉬 뒤의 단어 할당
        String jspName = uri.substring(uri.lastIndexOf('/') + 1);

        String tnoString = req.getParameter("tno");
        Integer tno = StringUtil.getInt(tnoString, 1);

        String pageStr = req.getParameter("page");
        int page = StringUtil.getInt(pageStr, 1);

        try {
            Optional<TodoVO> result = TodoDAO.INSTANCE.get(tno);
            TodoVO vo = result.orElseThrow();
            req.setAttribute("todo", vo);
            req.setAttribute("page", page);
            log.info(vo);
            // 클라이언트가 요청한 URI에 따라 화면 설정
            req.getRequestDispatcher("/WEB-INF/todo/" + jspName + ".jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doPost edit");

        String uri = req.getRequestURI();

        log.info("uri: {} " + uri);

        String job = uri.substring(uri.lastIndexOf('/') + 1);

        if(job.equals("get")){
            resp.sendRedirect("/todo/list");
            return;
        }

        String tnoStr = req.getParameter("tno");
        Integer tno = StringUtil.getInt(tnoStr, 1);

        String title = req.getParameter("title");
        String writer = req.getParameter("writer");

        TodoVO vo = TodoVO.builder()
                .tno(tno)
                .title(title)
                .writer(writer)
                .build();

        log.info("UPDATE VO: " + vo);

        try{
            boolean result = TodoDAO.INSTANCE.update(vo);
            resp.sendRedirect("/todo/get?tno=" + tno);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
