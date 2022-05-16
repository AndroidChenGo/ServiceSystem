package cn.infrastructure.util;

import cn.domain.model.Timetable;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Map;

public class WebUtils {
    /**
     * 把Map中的值注入到对应的JavaBean属性中。
     * @param value
     * @param bean
     */
    public static <T> T copyParamToBean( Map value , T bean ){
        try {
            System.out.println("注入之前：" + bean);
            /**
             * 把所有请求的参数都注入到user对象中
             */
            BeanUtils.populate(bean, value);
            System.out.println("注入之后：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static Timetable getTimetable(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String freelancerId = request.getParameter("freelancerId");
        String content = request.getParameter("content");
        String time = request.getParameter("time");
        Timestamp timestamp = StringUtil.changeStringToTimestamp(time);
        Timetable timetable = new Timetable();
        timetable.setId(Integer.parseInt(id));
        timetable.setName(name);
        timetable.setContent(content);
        timetable.setFreelancerId(Integer.parseInt(freelancerId));
        return timetable;
    }

    public static void rememberMe(String userName, String password, String userType, HttpServletResponse response) {
        Cookie user = new Cookie("serviceuser", userName+"-"+password+"-"+userType+"-"+"yes");
        user.setMaxAge(1*60*60*24*7);
        response.addCookie(user);
    }

    public static void deleteCookie(String userName, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals("serviceuser")) {
                if (userName.equals(userName = cookies[i].getValue().split("-")[0])) {
                    Cookie cookie = new Cookie(cookies[i].getName(), null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    public static boolean isIntEmpty(Integer i){
        if(i==null || i==0){
            return true;
        }
        return false;
    }

    /**
     * 将字符串转换成为int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

}
