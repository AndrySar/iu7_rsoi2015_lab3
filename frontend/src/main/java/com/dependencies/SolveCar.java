package com.dependencies;

import com.database.DTO.FactoryDTO;
import com.database.container.CarContainer;
import com.database.container.ModelContainer;
import com.database.logic.Car;

/**
 * Created by user on 28.12.15.
 */
public class SolveCar {

    private static  String  mainUrl = "http://localhost:5001/api/carsapi/cars";

    public static CarContainer getCars()
    {
        CarContainer carContainer = null;
        try {
            String responceString = StreamRequest.sendGet(mainUrl);
            carContainer = FactoryDTO
                    .getInstance()
                    .getCarDTO()
                    .convertToContainer(responceString);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  carContainer;
    }

    public static CarContainer getCars(int page, int per_page)
    {
        CarContainer carContainer = null;
        try {
            String responceString = StreamRequest.sendGet(mainUrl + "?page=" + page + "&per_page=" + per_page);
            carContainer = FactoryDTO
                    .getInstance()
                    .getCarDTO()
                    .convertToContainer(responceString);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  carContainer;
    }

    public static Car getCar(int id)
    {
        Car car = null;
        try{
            String responceString =
                    StreamRequest.sendGet(mainUrl + "/" + Integer.toString(id));
            car = FactoryDTO.getInstance().getCarDTO().convertToObject(responceString);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return car;
    }

    public static int setCar(String data)
    {
        try{
            int code = StreamRequest.sendPost(mainUrl, data);
            return code;
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return 502;
    }

    public static int deletCar(int id)
    {
        try{
            int code = StreamRequest.sendDelete(mainUrl + "/" + Integer.toString(id));
            return code;
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return 502;
    }

    public static CarContainer getCarsByModel(int id)
    {
        CarContainer carContainer = null;
        try {
            String responceString = StreamRequest.sendGet(mainUrl + "/mark/" + Integer.toString(id));
            carContainer = FactoryDTO
                    .getInstance()
                    .getCarDTO()
                    .convertToContainer(responceString);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  carContainer;
    }

    public static int updateCar(String data)
    {
        try{
            int code = StreamRequest.sendPut(mainUrl, data);
            return code;
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return 502;
    }


}
