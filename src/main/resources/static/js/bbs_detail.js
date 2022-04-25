$(document).ready(function() {
		$("#modBtn").hide();
		$("#cancelModBtn").hide();
	})
	
	
	
	let showList = function(bno) {
	    $.ajax({
	        type: 'GET',       // 요청 메서드
	        url: '/comments?bno='+ bno,  // 요청 URI
	        success: function (result) {
	            $('#commentList').html(toHtml(result));
	        },
	        error: function () {
	            alert("error")
	        } // 에러가 발생했을 때, 호출될 함수
	    });
	}// $.ajax()	
	
	
	$(document).ready(function() {

		let bno = $('#bno').val();
		
		showList(bno);

		
	    $("#modBtn").click(function(){
	        let comment = $("input[name=comment]").val();
	        let cno = $(this).attr("data-cno");
			
	        $("#sendBtn").fadeIn();
	        $("#modBtn").fadeOut();
	        $("#cancelModBtn").fadeOut();
	        
	        if(comment.trim()==''){
	            alert("댓글을 입력해주세요.");
	            $("imput[name=comment]").focus()
	            return;
	        }
	        $.ajax({
	            type:'PATCH',       // 요청 메서드
	            url: '/comments/' +cno,  // 요청 URI
	            headers : { "content-type": "application/json"}, // 요청 헤더
	            data : JSON.stringify({cno:cno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
	            success : function(result){
	                alert(result);
	                showList(bno),
	                $("#commentDiv").val(''),
                    $("#commentDiv").focus();;
	            },
	            error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
	        }); // $.ajax()
	    });
	    
	    
	     $("#wrtRepBtn").click(function(){
	            let comment = $("input[name=replyComment]").val();
	            let pcno = $("#replyForm").parent().attr("data-pcno");

	            if(comment.trim()==''){
	                alert("댓글을 입력해주세요.");
	                $("imput[name=comment]").focus()
	                return;
	            }
	            $.ajax({
	                type:'POST',       // 요청 메서드
	                url: '/comments?bno=' +bno,  // 요청 URI
	                headers : { "content-type": "application/json"}, // 요청 헤더
	                data : JSON.stringify({pcno:pcno, bno:bno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
	                success : function(result){
	                    alert(result);
	                    showList(bno);
	                },
	                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
	            }); // $.ajax()

	            $("#replyForm").css("display", "none")
	            $("input[name=replyComment]").val('')
	            $("#replyForm").appendTo("body");
	        });

	     	$("#cancelRepBtn").click(function() {
	     		$("#replyForm").css("display","none");
	     		$("input[name=replyComment]").val('')
	            $("#replyForm").appendTo("body");

	     	})
	     
	        $("#sendBtn").click(function(){
	            let cno = $(this).attr("data-cno");
	            let comment = $("input[name=comment]").val();

	            if(comment.trim()==''){
	                alert("댓글을 입력해주세요.");
	                $("imput[name=comment]").focus();
	                
	            return;
	        }
	            $.ajax({
	                type:'POST',       // 요청 메서드
	                url: '/comments?bno=' +bno,  // 요청 URI
	                headers : { "content-type": "application/json"}, // 요청 헤더
	                data : JSON.stringify({bno:bno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
	                success : function(result){
	                    alert(result);
	                    showList(bno),
	                    $("#commentDiv").val(''),
	                    $("#commentDiv").focus();
	                },
	                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
	            }); // $.ajax()
	        });

	        $("#commentList").on("click", ".modBtn",function() {
	            let cno = $(this).parent().attr("data-cno");
	            let comment = $("span.comment", $(this).parent()).text();

	            $("input[name=comment]").val(comment);
	            $("#modBtn").attr("data-cno", cno);
	            $("#commentDiv").focus();
	           
	            $("#sendBtn").fadeOut();
	            $("#modBtn").fadeIn();
	            $("#cancelModBtn").fadeIn();
	            
	            
	        });

	        $("#commentList").on("click", ".replyBtn",function(){
	            $("#replyForm").appendTo($(this).parent());
	            $("#replyForm").css("display","block");
	        });
	        $("#commentList").on("click", ".delBtn",function(){
	            let cno = $(this).parent().attr("data-cno");
	            let bno = $(this).parent().attr("data-bno");
	            let pcno = $(this).parent().attr("data-pcno");
	            
	            $.ajax({
	                type: 'DELETE',       // 요청 메서드
	                url: '/comments/' + cno + '?bno=' + bno+ '&pcno=' +pcno,  // 요청 URI
	                success: function (result) {
	                    alert(result)
	                    showList(bno);
	                },
	                error: function () {
	                    alert("error")
	                } // 에러가 발생했을 때, 호출될 함수
	            });
	        });    
		
			//게시판 수정버튼
            $("#bbsModBtn").on("click", function(){
            	location.href = "/bbs/write?bno="+$('#bno').val();
            });

			//게시판 삭제버튼
            $("#delBtn").on("click", function(){
                let form = $("#form");
                form.attr("action", "/bbs/deleteBbs");
                form.attr("method", "post");
             	form.submit();
            });
			
			$("#cancelModBtn").on("click", function(){
				$("#modBtn").fadeOut();
				$("#cancelModBtn").fadeOut();
				$("#sendBtn").fadeIn();
				$("#commentDiv").val('');
				$("#commentDiv").focus();
			})
			
		});
	
	let toHtml = function (comments){
	    let tmp = "<ul>";

	    Array.from(comments).forEach(function (comment){
		    tmp += '<li data-cno=' + comment.cno
		    tmp += ' data-pcno=' + comment.pcno
		    tmp += ' data-bno=' + comment.bno + '>'
		if(comment.cno != comment.pcno)
		    tmp += 'ㄴ'
		    tmp += ' <span class="commentAuthor">' + comment.userNickname +'</span>'
		    tmp += ' <span class="commentSubtance">' + comment.comment +'</span>'
		    if($('#loginId').val() == comment.commenter){
		    	tmp += '<button class= "delBtn">삭제</button>'
		    	tmp += '<button class= "modBtn">수정</button>'
		    }
		    tmp += '<button class= "replyBtn">답글</button>'
		    tmp += '</li>'
	})
	return tmp + "</ul>";
	    }

	//current position
	var pos = 0;
	//number of slides
	var totalSlides = $('#slider-wrap ul li').length;
	//get the slide width
	var sliderWidth = $('#slider-wrap').width();


	$(document).ready(function(){
	  
	  
	  /*****************
	   BUILD THE SLIDER
	  *****************/
	  //set width to be 'x' times the number of slides
	  $('#slider-wrap ul#slider').width(sliderWidth*totalSlides);
	  
	    //next slide  
	  $('#next').click(function(){
	    slideRight();
	  });
	  
	  //previous slide
	  $('#previous').click(function(){
	    slideLeft();
	  });
	  
	  
	  
	  /*************************
	   //*> OPTIONAL SETTINGS
	  ************************/
	  //automatic slider
	  var autoSlider = setInterval(slideRight, 3000);
	  
	  //for each slide 
	  $.each($('#slider-wrap ul li'), function() { 

	     //create a pagination
	     var li = document.createElement('li');
	     $('#pagination-wrap ul').append(li);    
	  });
	  
	  //counter
	  countSlides();
	  
	  //pagination
	  pagination();
	  
	  //hide/show controls/btns when hover
	  //pause automatic slide when hover
	  $('#slider-wrap').hover(
	    function(){ $(this).addClass('active'); clearInterval(autoSlider); }, 
	    function(){ $(this).removeClass('active'); autoSlider = setInterval(slideRight, 3000); }
	  );
	  
	  

	});//DOCUMENT READY
	  


	/***********
	 SLIDE LEFT
	************/
	function slideLeft(){
	  pos--;
	  if(pos==-1){ pos = totalSlides-1; }
	  $('#slider-wrap ul#slider').css('left', -(sliderWidth*pos));  
	  
	  //*> optional
	  countSlides();
	  pagination();
	}


	/************
	 SLIDE RIGHT
	*************/
	function slideRight(){
	  pos++;
	  if(pos==totalSlides){ pos = 0; }
	  $('#slider-wrap ul#slider').css('left', -(sliderWidth*pos)); 
	  
	  //*> optional 
	  countSlides();
	  pagination();
	}



	  
	/************************
	 //*> OPTIONAL SETTINGS
	************************/
	function countSlides(){
	  $('#counter').html(pos+1 + ' / ' + totalSlides);
	}

	function pagination(){
	  $('#pagination-wrap ul li').removeClass('active');
	  $('#pagination-wrap ul li:eq('+pos+')').addClass('active');
	}
	
	$(".authorId").each(function() {
		const length = 5;
		$(this).each(function() {
			if($(this).text().length >= length) {
				$(this).text($(this).text().substr(0, length)+"...")
			}
		});
	});