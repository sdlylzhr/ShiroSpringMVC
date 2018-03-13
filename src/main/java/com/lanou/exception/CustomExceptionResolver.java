package com.lanou.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lizhongren1.
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception ex) {
        // DispatcherServlet 在进行HandlerMapping,
        // 调用HandlerAdapter, 执行Handler的过程中, 如果遇到异常就会执行这个方法


        // handler参数是最终要执行的方法, 本质是handlerMethod
        // ex参数就是接受到的异常信息

        ex.printStackTrace();

        try {
            request.getRequestDispatcher("/WEB-INF/error.jsp")
                    .forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
