package com.dependencies;

import com.database.DTO.FactoryDTO;
import com.database.container.ModelContainer;
import com.database.logic.Mark;
import org.json.JSONObject;

/**
 * Created by user on 27.12.15.
 */
public class SolveModel {

    private static  String  mainUrl = "http://localhost:5002/api/models/marks";
    public static ModelContainer getModels()
    {
        ModelContainer modelContainer = null;
        try {
            String responceString = StreamRequest.sendGet(mainUrl);
            modelContainer = FactoryDTO
                    .getInstance()
                    .getModelDTO()
                    .convertToContainer(responceString);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  modelContainer;
    }

    public static ModelContainer getModels(int page, int per_page)
    {
        ModelContainer modelContainer = null;
        try {
            String responceString = StreamRequest.sendGet(mainUrl + "?page=" + page + "&per_page=" + per_page);
            modelContainer = FactoryDTO
                    .getInstance()
                    .getModelDTO()
                    .convertToContainer(responceString);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  modelContainer;
    }

    public static Mark getModel(int id)
    {
        Mark mark = null;
        try{
            String responceString =
                    StreamRequest.sendGet(mainUrl + "/" + Integer.toString(id));
            mark = FactoryDTO.getInstance().getModelDTO().convertToObject(responceString);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return mark;
    }

    public static int setModel(String data)
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

    public static int deleteModel(int id)
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


}
