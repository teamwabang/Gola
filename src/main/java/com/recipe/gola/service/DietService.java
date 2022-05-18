package com.recipe.gola.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.gola.dto.RecipeDTO;
import com.recipe.gola.mapper.DietMapper;
import com.recipe.gola.mapper.RecipeMapper;

import lombok.Data;

@Service
@Data
public class DietService {
   
   private final Logger logger = LoggerFactory.getLogger(this.getClass());

   @Autowired
   private final DietMapper dietMapper;

   public List<RecipeDTO> list(String kcal, String mealType, String keyword) {
      
      //화면에서 받은 하루 총 칼로리
      int data = Integer.parseInt(kcal);
      
      
      //데이터베이스에 메뉴 합산 최대 칼로리값 
      Double maxKcal = 1250.0;
      
      //끼니 타겟 칼로리 (아침 or 점심 or 저녁)
      Double targetKcal = 0.0;
      
      List<RecipeDTO> list = null;
      RecipeDTO recipeDTO = new RecipeDTO();
      recipeDTO.setKeyword(keyword);
      recipeDTO.setMealType(mealType);
      
      
      //   mealType = B 아침 L 점심 D 저녁
      if("B".equals(mealType)) {
         targetKcal = Double.parseDouble(getCalPercentData(data, 20));   //아침 백분율 20%
      }else if("L".equals(mealType)) {
         targetKcal = Double.parseDouble(getCalPercentData(data, 40));   //점심백분율 40%
      }else {
         targetKcal = Double.parseDouble(getCalPercentData(data, 40));   //저녁 백분율 40%   
      }
      
      //한끼 권장칼로리가 데이터베이스에 메뉴 합산 최대 칼로리값 보다 크면 최대 칼로리로 셋팅한다.
      if(maxKcal < targetKcal) {
    	  targetKcal = maxKcal;
      }

      
      while(true) {
         
         list = dietMapper.list(recipeDTO);
         
         double sumDbKcal = 0;

         for(RecipeDTO dto : list) {
            sumDbKcal += Double.parseDouble(dto.getKcal());
         }
         
         System.out.println("누적한 totKcal : "+sumDbKcal);
         System.out.println("필요한 Kcal : "+targetKcal);
         
         Double minErrorValue = Double.parseDouble(getCalPercentData(targetKcal, 95));
         Double maxErrorValue = Double.parseDouble(getCalPercentData(targetKcal, 105));
         
         System.out.println("최소 오차범위 권장칼로리 : "+minErrorValue);
         System.out.println("최대 오차범위 권장칼로리 : "+maxErrorValue);
         System.out.println("dataBase 의 합계 칼로리  : "+sumDbKcal);
         
         //데이터베이스의 칼로리 총합이 오차범위 권장칼로리보다 작으면
         if(sumDbKcal <= maxErrorValue) {
            //오차범위 권장 칼로리가 데이터베이스 총합 칼로리보다 크면  while 탈출
            if(minErrorValue < sumDbKcal) {
               break;
            }
         }
      }
      
      //}while(value != 10);  // 입력받은 값이 10이 아닐 경우에는 계속 반복합니다.
      System.out.println(":::: while 반복문 종료:::: ");
      System.out.println("return list : "+list.toString());
      
      return list;
      

   }
   
   /**
    * [요약 설명] 백분율 값 구하기
    * 1. 퍼센트 값을 구하기 위해서는 먼저 원본값을 100으로 나눠서 1퍼센트 값을 구한 후 >> 지정된 퍼센트를 곱합니다
    * 2. String.valueOf : string 형태로 데이터 형변환을 수행합니다
    * 3. String.format : string 형태로 특정 데이터를 포맷해서 형변환을 수행합니다
    * */
   public static String getCalPercentData(double value, double perc) {
      // 결과 반환할 변수 선언 실시
      String result = "";
      
      // 인풋으로 들어온 데이터 확인 실시
      System.out.println("original value : " + value+ " original perc " + perc);
      
      // 인풋으로 들어온 데이터 변수에 삽입
      double checkValue = 0.0;
      double checkPerc = 0.0;
      try {
         // 원본으로 들어온 값을 100으로 나눠서 1퍼센트 값을 구합니다
         checkValue = (value / 100);
         
         // 1퍼센트 구합값을 사용해 인풋으로 들어온 퍼센트 값을 곱합니다
         checkPerc = (checkValue*perc);
         System.out.println("checkValue : " + checkValue);
         System.out.println("checkPerc : " + checkPerc);
         System.out.println("");
         
         // 결과 반환 변수에 데이터 삽입 실시
         result = String.valueOf(checkPerc); // 소수점 계산 그대로 반환
         //result = String.format("%.1f", checkPerc); // 소수점 1자리 까지만 출력
         //result = String.format("%.2f", checkPerc); // 소수점 2자리 까지만 출력
      }
      catch(Exception e) {
         e.printStackTrace();
      }
      return result;
   }
   
}