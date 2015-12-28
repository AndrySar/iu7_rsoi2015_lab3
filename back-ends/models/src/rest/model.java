package rest;

import com.database.DTO.FactoryDTO;
import com.database.DTO.interf.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.database.DAO.*;
import com.database.container.*;
import com.database.logic.*;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Created by user on 24.12.15.
 */
@Path("/models")
public class model {

    @GET
    @Path("/marks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMarks(@DefaultValue("0") @QueryParam("page") int page,
                             @DefaultValue("20") @QueryParam("per_page") int per_page,
                             @Context HttpServletRequest httpRequest)
    {
        String json = null;

        try
        {
            try {

                List<Mark> marks = Factory.getInstance().getMarkDAO().getAllMarksLimit(per_page, page * per_page);

                int total = Factory.getInstance().getMarkDAO().getAllMarks().size();
                int pages = (int) Math.ceil((double) total / (double) per_page);

                if ((page >= pages) || (page > 2000 / per_page)){
                    json = null;}
                else
                {
                    ModelContainer modelContainer = new ModelContainer(marks, page, pages, per_page, total);
                    json = FactoryDTO.getInstance().getModelDTO().convertToJsonstring(modelContainer);
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if(json != null)
                return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();


        }catch (Exception ex)
        {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/marks/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getMarks(@PathParam("id") int id,
                              @Context HttpServletRequest httpRequest)
    {
        String json = null;
        try
        {
            try {

                    Mark mark = Factory.getInstance().getMarkDAO().getMarkById(id);

                    json = FactoryDTO.getInstance().getModelDTO().convertToJsonstring(mark);

//                    if(mark != null) {
//                        json.append("marks", mark.toString());
//
//                    }

//                    Iterator iterator = mark.getCars().iterator();
//
//                    while (iterator.hasNext()) {
//                        json.append("cars", iterator.next().toString());
//                    }


            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if(json != null)
                return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity("404 Not Found").build();


        }catch (Exception ex)
        {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }


    @POST
    @Path("/marks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response  setMark(String data,
                             @Context HttpServletRequest httpRequest)
    {
        try{
            if(data == null || data.equals("")) return Response.status(400).build();

            Mark mark = FactoryDTO.getInstance().getModelDTO().convertToObject(data);


            Integer number = Factory.getInstance().getMarkDAO().addMark(mark);

            if(number != null) {
                URI uri = new URI(httpRequest.getContextPath() + "/" + number.toString());
                return Response.created(uri).build();
            }
            else
                return Response.status(502).build();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return Response.status(502).build();
        }
    }


//    @PUT
//    @Path("/marks/{id}")
//    @Produces("text/plain")
//    public Response  updateMark(@PathParam("id") int id,
//                                @Context HttpServletRequest httpRequest)
//    {
//        JSONObject json = null;
//        String access_token = httpRequest.getHeader("access_token");
//
//        try
//        {
//            if(jetty_server.db.checkAccessToken(access_token).next())
//            {
//                try {
//
//                    Map<String, String> map = jetty_server.oath.queryToMap(httpRequest.getQueryString());
//                    Map<String, String> map2 =  new HashMap<String, String>();
//
//                    for(String str: this.marksFields)
//                    {
//                        if(map.get(str) != null)
//                            map2.put(str, map.get(str));
//                        else
//                            return Response.status(Response.Status.BAD_REQUEST).build();
//                    }
//
//                    if(!jetty_server.db.getFullMarks(id).next())
//                        return Response.status(Response.Status.NOT_FOUND).entity("404 Not Found").build();
//
//                    if(jetty_server.db.updateMarks(id, map))
//                        return Response.ok().build();
//
//                } catch (Exception ex) {
//                    System.out.print("Ошибка");
//                }
//
//                return Response.notModified().build();
//            }else
//            {
//                return Response.status(Response.Status.FORBIDDEN).build();
//            }
//        }catch (Exception ex) {
//            return Response.serverError().build();
//        }
//    }
//
//
//    @PATCH
//    @Path("/marks/{id}")
//    @Produces("text/plain")
//    public Response  updateMarksSomeFields(@PathParam("id") int id,
//                                           @Context HttpServletRequest httpRequest)
//    {
//        String access_token = httpRequest.getHeader("access_token");
//
//        try
//        {
//            if(jetty_server.db.checkAccessToken(access_token).next())
//            {
//                try {
//
//                    Map<String, String> map = jetty_server.oath.queryToMap(httpRequest.getQueryString());
//                    Map<String, String> map2 =  new HashMap<String, String>();
//
//                    for(String str: this.marksFields)
//                    {
//                        if(map.get(str) != null)
//                            map2.put(str, map.get(str));
//                    }
//
//                    if(!jetty_server.db.getFullMarks(id).next())
//                        return Response.status(Response.Status.NOT_FOUND).entity("404 Not Found").build();
//
//                    if(jetty_server.db.updateMarks(id, map2))
//                        return Response.ok().build();
//
//                } catch (Exception ex) {
//                    System.out.print("Ошибка");
//                }
//
//                return Response.serverError().build();
//            }else
//            {
//                return Response.status(Response.Status.FORBIDDEN).type("text/plain").entity("403 Forbidden").build();
//            }
//        }catch (Exception ex) {
//            return Response.serverError().build();
//        }
//    }


    @DELETE
    @Path("/marks/{id}")
    @Produces("text/plain")
    public Response  deleteMark(@PathParam("id") int id)
    {
        try
        {
            Mark mark = Factory.getInstance().getMarkDAO().getMarkById(id);

            if(mark == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            if(Factory.getInstance().getMarkDAO().deleteMark(mark))
                return Response.status(204).build();
            else
                return Response.status(502).build();


        }catch (Exception ex) {
            return Response.serverError().build();
        }
    }


}
