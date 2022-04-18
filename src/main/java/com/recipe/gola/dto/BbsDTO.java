package com.recipe.gola.dto;

import java.util.*;

import lombok.Data;

@Data
public class BbsDTO extends FilesDTO{
    private String bno;
    private String  title;
    private String  content;
    private String  writer;
    private Date	regDate;
    private String level;
    private String schTxt;
}
