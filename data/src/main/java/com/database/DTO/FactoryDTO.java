package com.database.DTO;

import com.database.DTO.impl.CarDTOImpl;
import com.database.DTO.impl.ModelDTOImpl;
import com.database.DTO.impl.SessionDTOImpl;
import com.database.DTO.impl.UserDTOImpl;
import com.database.DTO.interf.CarDTO;
import com.database.DTO.interf.ModelDTO;
import com.database.DTO.interf.SessionDTO;
import com.database.DTO.interf.UserDTO;

/**
 * Created by user on 25.12.15.
 */
public class FactoryDTO {

    private static ModelDTO modelDTO = null;
    private static SessionDTO sessionDTO = null;
    private static CarDTO carDTO = null;
    private static UserDTO userDTO = null;
    private static FactoryDTO instance = null;


    public static synchronized FactoryDTO getInstance(){
        if (instance == null){
            instance = new FactoryDTO();
        }
        return instance;
    }

    public UserDTO getUserDTO(){
        if (userDTO == null){
            userDTO = new UserDTOImpl();
        }
        return userDTO;
    }

    public SessionDTO getSessionDTO() {
        if (sessionDTO == null){
            sessionDTO = new SessionDTOImpl();
        }
        return sessionDTO;
    }

    public CarDTO getCarDTO() {
        if (carDTO == null){
            carDTO = new CarDTOImpl();
        }
        return carDTO;
    }

    public ModelDTO getModelDTO() {
        if(modelDTO == null){
            modelDTO = new ModelDTOImpl();
        }
        return modelDTO;
    }

}
