package rest;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.database.DAO.Factory;
import com.database.DTO.FactoryDTO;
import com.database.logic.Session;
import com.database.logic.User;
import org.json.JSONObject;
import other.workSpace;
import server.jetty;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by user on 12.12.15.
 */

@Path("/sessions")
public class Sessions {

    @GET
    @Path("/session/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cheakSession(@PathParam("token") String token,
                                 @Context HttpServletRequest httpRequest)
    {
        jetty.printLogs(httpRequest);
        Session currentSession = null;
        User user = null;
        JSONObject json = new JSONObject();
        try {
               currentSession = Factory.getInstance().getSessionDAO().getSessionByToken(token);
               user = Factory.getInstance().getUserDAO().getUserById(currentSession.getUser());

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        if(user != null)
            return Response.ok(json.putOnce("login", user.getLogin().toString()).toString(),
                             MediaType.APPLICATION_JSON).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/session")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSession(@QueryParam("login") String login,
                                  @Context HttpServletRequest httpRequest)
    {
        jetty.printLogs(httpRequest);
        JSONObject json = new JSONObject();
        try {
            User user = Factory.getInstance().getUserDAO().getUserByLogin(login);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.WEEK_OF_YEAR, 1);  // Здесь отнимем грегорианскую! неделю
            java.util.Date date = c.getTime(); // получаем объект типа java.util.Date


            String token = workSpace.generateToken();
            Session newSession = new Session();
            newSession.setAccessToken(token);
            newSession.setDateCreate(new Date());
            newSession.setDateSpoiled(date);
            newSession.setUser(user.getId());

            if(Factory.getInstance().getSessionDAO().addSession(newSession))
                    return Response.ok(json.putOnce("token",token).toString(), MediaType.APPLICATION_JSON).build();
            else
                return Response.status(Response.Status.CONFLICT).build();


        }catch (java.sql.SQLException e)
        {
            e.printStackTrace();
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }

    }


//    @POST
//    @Path("/user")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createUser(@QueryParam("login") String login,
//                               @QueryParam("passwd") String passwd,
//                               @QueryParam("email") String email,
//                               @Context HttpServletRequest httpRequest)
//    {
//        if(login != null && passwd != null && email != null)
//        {
//            User user = new User();
//            user.setLogin(login);
//            user.setPasswd(passwd);
//            user.setAddress(email);
//            user.setPhone("12345");
//
//            try {
//
//                Factory.getInstance().getUserDAO().addUser(user);
//
//                return Response.status(201).build();
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return Response.status(400).build();
//    }

    @POST
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(String data,
                               @Context HttpServletRequest httpRequest)
    {
        jetty.printLogs(httpRequest);
       try{
           if(data != null)
           {
               User user = FactoryDTO.getInstance().getUserDTO().convertToObject(data);

               if(Factory.getInstance().getUserDAO().addUser(user))
                   return Response.status(201).build();
               else
                   return Response.status(502).build();

           }else
           {
               return Response.status(400).build();
           }

       }
       catch (Exception ex)
       {
            ex.printStackTrace();
            return Response.status(502).build();
       }
      //  return Response.status(502).build();
    }

    @GET
    @Path("/autho")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Authorization(@QueryParam("login") String login,
                                  @QueryParam("passwd") String passwd,
                                  @Context HttpServletRequest httpRequest)
    {
        jetty.printLogs(httpRequest);
        JSONObject json = new JSONObject();
        try {

            User user = Factory.getInstance().getUserDAO().getUserByLoginByPasswd(login, passwd);

            if(user != null)
                return Response.ok(json.putOnce("login",user.getLogin()).toString(), MediaType.APPLICATION_JSON).build();


        }catch (java.sql.SQLException ex)
        {
            ex.printStackTrace();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }



}
