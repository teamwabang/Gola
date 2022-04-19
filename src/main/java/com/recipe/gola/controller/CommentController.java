package com.recipe.gola.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.recipe.gola.config.auth.PrincipalDetails;
import com.recipe.gola.dto.*;
import com.recipe.gola.service.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RestController("/comment")
public class CommentController {
	
	private static final Logger log = LoggerFactory.getLogger(CommentController.class);
	
    @Autowired
    CommentService service;

    //    {
//        "pcno":0,
//            "comment" : "hihihi",
//            "commenter" : "asdf"
//    }
    
    // 댓글을 수정하는 메서드
    @PatchMapping("/comments/{cno}")   // /comments/26  PATCH
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDTO dto,HttpSession session, @AuthenticationPrincipal PrincipalDetails principaldetail) {
        String commenter = (String)session.getAttribute("userId");
//        String commenter = "asdf";
        dto.setCommenter(principaldetail.getUserNickname());
        dto.setCno(cno);
        System.out.println("dto = " + dto);

        try {
            if(service.modify(dto)!=1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    //    
//        "pcno":0,
//            "comment" : "hi"
//    }
    // 댓글을 등록하는 메서드
    @PostMapping("/comments")   // /ch4/comments?bno=1085  POST
    public ResponseEntity<String> write(@RequestBody CommentDTO dto, Integer bno, HttpSession session, @AuthenticationPrincipal PrincipalDetails principaldetail ) {
        String commenter = (String)session.getAttribute("getUserNickname");
 //       String commenter = "asdf";

        log.info(">>>>>>>>>>>>>>>>>>>>>>> principaldetail.getUserNickname()"+principaldetail.getUsername());
        
        dto.setCommenter(principaldetail.getUserNickname());
        dto.setBno(bno);
        System.out.println("dto = " + dto);

        try {
            if(service.write(dto)!=1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
     }
    }// 지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/comments/{cno}")  // DELETE /comments/1?bno=1085  <-- 삭제할 댓글 번호
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session, @AuthenticationPrincipal PrincipalDetails principaldetail) {
        String commenter = (String)session.getAttribute("UserNickname");
//        String commenter = "asdf";

        	System.out.println("cno :"+cno+" bno :"+bno);
        try {
            int rowCnt = service.remove(cno, bno, principaldetail.getUserNickname());

            if(rowCnt!=1)
                throw new Exception("Delete Failed");

            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    // 지정된 게시물의 모든 댓글을 가져오는 메서드
    @GetMapping("/comments")  // /comments?bno=1080   GET
    public ResponseEntity<List<CommentDTO>> list(Integer bno) {
        List<CommentDTO> list = null;
        try {
            list = service.getList(bno);
            return new ResponseEntity<List<CommentDTO>>(list, HttpStatus.OK);  // 200
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<CommentDTO>>(HttpStatus.BAD_REQUEST); // 400
        }
    }


}
