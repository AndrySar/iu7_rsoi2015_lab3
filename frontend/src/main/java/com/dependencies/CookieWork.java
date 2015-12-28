package com.dependencies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 23.12.15.
 */
public class CookieWork {

    public static void setCookie(HttpServletResponse resp, String name,String role) {

        Cookie c = new Cookie(name, role);
        c.setMaxAge(3600);// время жизни файла
        resp.addCookie(c);
    }

    public static void deleteCookie(HttpServletResponse resp, String name)
    {
        Cookie c = new Cookie(name, null);
        c.setMaxAge(0);// время жизни файла
        resp.addCookie(c);
    }

    public static String  getValueCookie(HttpServletRequest request, String name)
    {

        try {

            Cookie[] cookies = request.getCookies();

            if (cookies != null) {


                for (int i = 0; i < cookies.length; i++) {

                    Cookie c = cookies[i];

                  if(c.getName().equals(name))
                      return c.getValue();

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(e.toString());

        }
        return null;
    }



}
