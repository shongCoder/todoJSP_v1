package org.example.w2.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.w2.todo.dao.TodoDAO;

import java.io.IOException;

@WebServlet(value = "/todo/register")
@Log4j2
public class TodoRegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet register");
        req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doPost register");

        // 입력받은(제출한) 값 가져오기
        String titleStr = req.getParameter("title");
        String writerStr = req.getParameter("writer");

        log.info("title: " + titleStr + ", writer: " + writerStr);

        TodoVO vo = TodoVO.builder()
                .title(titleStr)
                .writer(writerStr)
                .build();

        log.info("TodoVO: " + vo);

        try {
            Integer tno = TodoDAO.INSTANCE.insert(vo);
            log.info("reg tno: " + tno);
            // 왜 get 아니고 list지??
            resp.sendRedirect("/todo/get?tno=" + tno);
        } catch (Exception e) {
            log.error("Error during insert", e);
            resp.sendRedirect("/todo/register?error");
        }

    }
}
