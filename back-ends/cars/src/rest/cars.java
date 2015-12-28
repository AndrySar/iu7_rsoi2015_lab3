package rest;

/**
 * Created by user on 12.12.15.
 */
import com.database.DTO.FactoryDTO;
import com.database.container.CarContainer;
import com.database.container.ModelContainer;
import com.database.logic.Mark;
import org.eclipse.jetty.server.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URI;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.database.logic.Car;
import com.database.DAO.*;
import other.WorkSpace;

@Path("/carsapi")
public class cars {

    public String[] carsFields = {"model", "power", "year", "color", "cost", "marks_id"};

    public String[] marksFields = {"name", "contry", "info"};

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @HttpMethod("PATCH")
    public @interface PATCH {
    }


    private JSONObject JSONArrayToJSONObject(JSONArray array, String field) {
        JSONObject json = new JSONObject();

        for (int i = 0; i < array.length(); i++)
            json.append(field, array.get(i));

        return json;
    }

    private JSONObject createJsonAsList(int page, int per_page, JSONArray array, String nameDB, String field) {
        JSONObject json = new JSONObject();

        for (int i = 0; i < array.length(); i++)
            json.append(field, array.get(i));

        int total = 5;   ////////jetty_server.db.countRecord(nameDB);

        int pages = (int) Math.ceil((double) total / (double) per_page);

        json.put("page", page);
        json.put("per_page", per_page);
        json.put("pages", pages);
        json.put("total", total);

        if ((page >= pages) || (page > 2000 / per_page))
            return null;

        return json;
    }

    private JSONObject createJson(JSONArray array, String field) {
        JSONObject json = new JSONObject();

        for (int i = 0; i < array.length(); i++)
            json.append(field, array.get(i));

        return json;
    }


    //region API автомобилей

    @GET
    @Path("/cars")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCars(@QueryParam("page") int page,
                            @DefaultValue("20") @QueryParam("per_page") int per_page)
    {
        String json = null;

        try {

            List<Car> cars = Factory.getInstance().getCarDAO().getAllCarsLimit(per_page, page * per_page);

            int total = Factory.getInstance().getCarDAO().getAllCars().size();
            int pages = (int) Math.ceil((double) total / (double) per_page);

            if ((page >= pages) || (page > 2000 / per_page)){
                json = null;}
            else
            {
                CarContainer carsContainer = new CarContainer(cars, page, pages, per_page, total);
                json = FactoryDTO.getInstance().getCarDTO().convertToJsonstring(carsContainer);
            }


            if (json != null)
                return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();

        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }


    @GET
    @Path("/cars/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCar(@PathParam("id") int id) {

        String json = null;

        try {

            Car car = Factory.getInstance().getCarDAO().getCarById(id);
            json = FactoryDTO.getInstance().getCarDTO().convertToJsonstring(car);


            if (json != null)
                return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity("404 Not Found").build();

        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }


    @POST
    @Path("/cars")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setCar(String data,
                           @Context HttpServletRequest httpRequest) {

        try {

            if(data == null || data.equals("")) return Response.status(400).build();

            Car car = FactoryDTO.getInstance().getCarDTO().convertToObject(data);

            if(car == null) return Response.status(404).build();

            Integer number = Factory.getInstance().getCarDAO().addCar(car);

            if(number != null) {
                URI uri = new URI(httpRequest.getContextPath() + "/" + number.toString());
                return Response.created(uri).build();
            }
            else
                return Response.status(502).build();

        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }


    @PUT
    @Path("/cars")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCar(String data) {
        try {

            if(data != null || data.equals("")) {

                Car car = FactoryDTO.getInstance().getCarDTO().convertToObject(data);

                if (Factory.getInstance().getCarDAO().updateCar(car))
                    return Response.status(201).build();
                else
                    return Response.status(404).build();

            }else
                return Response.status(400).build();

        } catch (Exception ex) {

            return Response.serverError().build();
        }
    }

//    @PATCH
//    @Path("/cars/{id}")
//    @Produces("text/plain")
//    public Response updateCarSomeFields(@PathParam("id") int id,
//                                        @Context HttpServletRequest httpRequest) {
//        try {
//
//            try {
//
////                    Map<String, String> map = jetty_server.oath.queryToMap(httpRequest.getQueryString());
////                    Map<String, String> map2 =  new HashMap<String, String>();
////
////                    for(String str: this.carsFields)
////                    {
////                        if(map.get(str) != null)
////                            map2.put(str, map.get(str));
////                    }
////
////                    if(!jetty_server.db.GetCarFull(id).next())
////                        return Response.status(Response.Status.NOT_FOUND).entity("404 Not Found").build();
////
////                    if(jetty_server.db.updateCars(id, map2))
////                        return Response.ok().build();
//
//                Map<String, String> map = WorkSpace.queryToMap(httpRequest.getQueryString());
//                Map<String, String> map2 = new HashMap<String, String>();
//
//                for (String str : this.carsFields) {
//                    if (map.get(str) != null)
//                        map2.put(str, map.get(str));
//                }
//
//                Mark mark = Factory.getInstance().getMarkDAO().getMarkById(Integer.parseInt(map2.get("mark_id")));
//
//                if (mark == null)
//                    return Response.status(Response.Status.BAD_REQUEST).build();
//
//                Car car = new Car();
//                car.setModel(map2.get("model"));
//                car.setPower(Integer.parseInt(map2.get("power")));
//                car.setCost(Integer.parseInt(map2.get("cost")));
//                car.setYear(map2.get("year"));
//                car.setMark(mark);
//
//
//                Factory.getInstance().getCarDAO().updateCar(car);
//
//                return Response.ok().build();
//
//
//            } catch (Exception ex) {
//                System.out.print("Ошибка");
//            }
//
//            return Response.serverError().build();
//
//        } catch (Exception ex) {
//            return Response.serverError().build();
//        }
//    }


    @DELETE
    @Path("/cars/{id}")
    @Produces("text/plain")
    public Response deleteCar(@PathParam("id") int id) {

        String json = null;
        try {

            Car car = Factory.getInstance().getCarDAO().getCarById(id);

            if(car == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            if(Factory.getInstance().getCarDAO().deleteCar(car))
                return Response.status(204).build();
            else
                return Response.status(502).build();


        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }


    @GET
    @Path("/cars/mark/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarByMark(@PathParam("id") int id) {

        String json = null;

        try {

            List<Car> cars = Factory.getInstance().getCarDAO().getCarsByMark(id);


            if(cars == null) return Response.status(Response.Status.NOT_FOUND).entity("404 Not Found").build();


            CarContainer carsContainer = new CarContainer(cars, 0,0,0,0);
            json = FactoryDTO.getInstance().getCarDTO().convertToJsonstring(carsContainer);


            if (json != null)
                return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity("404 Not Found").build();

        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

//    @GET
//    @Path("/cars")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getCarsByModel(@QueryParam("markId") int id,
//                                   @QueryParam("page") int page,
//                                   @DefaultValue("20") @QueryParam("per_page") int per_page) {
//
//        String json = null;
//
//        try {
//
//            List<Car> cars = Factory.getInstance().getCarDAO().getCarsByMark(id);
//
////            CarContainer carsContainer = new CarContainer(cars, page, pages, per_page, total);
////            json = FactoryDTO.getInstance().getCarDTO().convertToJsonstring(carsContainer);
//
//
//            if (json != null)
//                return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();
//            else
//                return Response.status(Response.Status.NOT_FOUND).entity("404 Not Found").build();
//
//        } catch (Exception ex) {
//            return Response.serverError().build();
//        }
//    }

}

