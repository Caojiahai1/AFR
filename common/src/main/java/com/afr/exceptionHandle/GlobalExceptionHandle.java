
package com.afr.exceptionHandle;

import com.afr.utils.MyConstant;
import com.afr.utils.MyLogger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yan liang
 * @create 2019/1/23
 * @since 1.0.0
 */
public class GlobalExceptionHandle implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        //如果是json格式的ajax请求
        if (request.getHeader("accept").indexOf("application/json") > -1
                || (request.getHeader("X-Requested-With")!= null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)) {
            response.setStatus(500);
            response.setContentType("application/json;charset=utf-8");
            try {
                response.getWriter().write(MyConstant.SYSTEM_ERROR);
                response.getWriter().flush();
            } catch (Exception e1) {
                // TODO Auto-generated catch block

            }
            return new ModelAndView();
        }
        else{//如果是普通请求
            MyLogger.logger.error(e.getMessage());
            request.setAttribute("exceptionMessage", e.getMessage());
            return new ModelAndView("error");
        }
    }
}