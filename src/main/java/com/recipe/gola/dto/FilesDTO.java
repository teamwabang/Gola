
package com.recipe.gola.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FilesDTO {
    private String fno;					//파일번호
    private String bno;					//게시판글번호 or userId
    private String fileName;			//파일명
    private String fileExt;				//파일확장자
    private String fileSize;			//파일사이즈
    private String fileSavename;			//파일저장명
    private String filePath;				//파일경로
    private String imgSrc;					//이미지주소
    private String deleteFilesNo[];				//파일삭제를 위한 삭제파일번호배열
	private MultipartFile[] upFile; 	//사진파일객체 배열
}
