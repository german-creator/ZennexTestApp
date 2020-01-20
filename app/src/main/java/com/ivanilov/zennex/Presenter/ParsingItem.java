package com.ivanilov.zennex.Presenter;


import lombok.Getter;
import lombok.Setter;

/**
 * Класс - сущность, служащий для удобного использования данных, полученных с http://quotes.zennex.ru/api/v3/bash/quotes?sort=time
 * @autor Герман Иванилов
 * @version 1.0
 */

@Getter
@Setter
public class ParsingItem {

    Integer id;
    String description;
    String time;
    Integer rating;

    public ParsingItem(Integer id, String description, String time, Integer rating) {
        this.id = id;
        this.description = description;
        this.time = time;
        this.rating = rating;
    }


}
