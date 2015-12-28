package com.database.DTO.interf;

import com.database.container.CarContainer;
import com.database.container.ModelContainer;
import com.database.logic.Car;
import com.database.logic.Mark;

/**
 * Created by user on 25.12.15.
 */
public interface CarDTO {
    String convertToJsonstring(Car car);
    String convertToJsonstring(CarContainer car);
    CarContainer convertToContainer(String json);
    Car convertToObject(String json);
}
