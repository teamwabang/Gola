package com.recipe.gola.dto;

import java.util.*;

import lombok.Data;

@Data
public class BbsDTO {
    private String bno;
    private String  title;
    private String  content;
    private String  writer;
    private Date	regDate;
}
