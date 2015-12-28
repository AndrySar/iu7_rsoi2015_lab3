package com.database.DTO.interf;

import com.database.container.ModelContainer;
import com.database.logic.Mark;

/**
 * Created by user on 25.12.15.
 */
public interface ModelDTO {
    String convertToJsonstring(Mark mark);
    String convertToJsonstring(ModelContainer model);
    ModelContainer convertToContainer(String json);
    Mark convertToObject(String json);
}
