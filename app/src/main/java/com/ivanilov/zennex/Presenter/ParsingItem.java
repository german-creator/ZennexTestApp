package com.ivanilov.zennex.Presenter;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParsingItem {

    Integer id;
    String description;
    String time;
    Integer rating;

    public ParsingItem (Integer id, String description, String time, Integer rating) {
        this.id = id;
        this.description = description;
        this.time = time;
        this.rating = rating;
    }



}
