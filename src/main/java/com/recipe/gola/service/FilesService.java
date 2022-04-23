package com.recipe.gola.service;

import java.io.File;
import java.util.*;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.recipe.gola.dto.*;
import com.recipe.gola.mapper.BbsMapper;

import lombok.Data;

@Service
@Data
public class FilesService {

	private static final Logger log = LoggerFactory.getLogger(FilesService.class);

	@Autowired
    BbsService bbsService;
	
	private final BbsMapper bbsMapper;
    
	
	public boolean fileUpload(MultipartFile[] upFiles, String parentKey, String fileUploadPath, String[] deleteFilesNo) throws Exception {

		//※※※※게시판 내용 저장이 성공하면 사진업로드 시작※※※
		//html에서 넘어온 input=file 객체가 있으면
		int result = 0;

		boolean resultFlag = true;

		//2. 파일 업로드
		if(upFiles.length > 0) {
    		
    		//2-1. 업로드 폴더가 없으면 생성
    		File dir = new File(fileUploadPath);

    		if (dir.exists() == false) {
    			dir.mkdirs();
    		}

    		//2-2. input=file 객체만큼 for문
    		for (MultipartFile file : upFiles) {    

    			if(!file.isEmpty()) {

    				/* 파일 확장자 */
    				final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
    				
    				/* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
    				final String saveName = getRandomString() + "." + extension;
    				final String fileName = file.getOriginalFilename();
    				final long fileSize = file.getSize();

    				log.info("::::: 원본 파일이름 file.getOriginalFilename() >>>>> "+file.getOriginalFilename());
    				log.info("::::: 파일 사이즈 file.size() >>>>> "+file.getSize());
    				log.info("::::: 저장 파일이름 saveName >>>>> "+saveName);			
    				
    				//2-3.파일정보 데이터베이스에 저장
    				FilesDTO filesDto = new FilesDTO();
    				filesDto.setBno(parentKey);				//부모 키
    				filesDto.setFileName(fileName);			//파일원본이름
    				filesDto.setFileSavename(saveName);		//파일저장이름
    				filesDto.setFileSize(Long.toString(fileSize));		//파일사이즈
    				filesDto.setFilePath(fileUploadPath+"/");				//파일업로드한 경로
    				
    				result = bbsService.insertFiles(filesDto);

    				//2-4. 파일저장이 성공시 메세지 출력
    				if(result >0) {
    					//2-5. 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성 
    					File target = new File(fileUploadPath, saveName);
    					file.transferTo(target);
    					
    				}
    				
    			}
    		}
    		
    		resultFlag = result > 0 ? true : false;
		}

		//3. 기존파일 삭제
		if(deleteFilesNo != null && deleteFilesNo.length > 0) {

			for (String fno : deleteFilesNo) {
				
				log.info("::::: 삭제 파일번호 fno >>>>> "+fno);			
				
				FilesDTO deleteFileDto = new FilesDTO();

				deleteFileDto = bbsService.selectDetailFiles(fno);
				
				final String fileSavename = deleteFileDto.getFileSavename();

				log.info("::::: 삭제 파일경로 >>>>> "+fileUploadPath+"/"+fileSavename);			
				
				File deleteTarget = new File(fileUploadPath,fileSavename); 

				if(deleteTarget.exists()) { // 파일이 존재하면 
					deleteTarget.delete(); // 파일 삭제 
				}
				//3-1. 기존파일정보 데이터베이스에서 삭제
				result = bbsService.deleteFiles(fno);

			}
			
			resultFlag = result > 0 ? true : false;
		}
		
		return resultFlag;
	}
	   
    //파일생성을 위한 랜덤난수값 생성
    private final String getRandomString() {
    	return UUID.randomUUID().toString().replaceAll("-", "");    	
    }
    
}