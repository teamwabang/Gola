package snippet;

public class Snippet {
	/* Google Web Fonts CDN */
	@import url('https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,500,700,900&display=swap');
	
	
	
	.gnb {
	    background-color: #fff;
	    padding: 10px;
	    margin-top: 40px;
	    border-radius: 10px;
	    padding-bottom: 10px;
	    height: 10%;
	    font-size: 18px;
	    font-weight: bold;
	
	}
	
	.gnb a {
	    /*border: 1px solid red;*/
	    width: 80px;
	    display: inline-block;
	    height: 30px;
	    line-height: 30px;
	    text-align: center;
	    position: relative;
	    overflow: hidden;
	    padding-right: 100px;
	    padding-left: 100px;
	}
	
	.gnb a:before,
	.gnb a:after {
	    position: absolute;
	    content: attr(data-link);
	    width: 100%;
	    transition: 0.3s;
	}
	
	.gnb a:before {
	    top: 0;
	    left: 0;
	}
	
	.gnb a:after {
	    top: 100%;
	    left: 0;
	    color: red;
	}
	
	.gnb a:hover:before {
	    top: -100%;
	}
	
	.gnb a:hover:after {
	    top: 0;
	}
	
	.headertop {
	    display: flex;
	    margin-top: 20px ;
	    display:inline-block;
	    text-align: center;
	
	}
	
	.headerlogo {
	    width: 100px;
	    text-align: center;
	    margin: 10px 0px;
	    padding-left: 100px;
	    padding-right: 200px;
	    justify-content: center;
	    text-align: center;
	}
	
	.headernav {
	    display: flex;
	    flex-direction: column;
	}
	
	.gnb2 {
	    font-size: 14px;
	    display: inline-block;
	    text-align: right;
	    margin-top: 50px;
	
	}
	
	.gnb2 button {
	    display: inline-block;
	    font-size: 16px;
	    color: black;
	    transition: 0.2s;
	    padding-bottom: 10px;
	    background-color:transparent;
	    border : none;
	}
	.gnb2 li {
		display:block;
	}
	
	.gnb2:hover button {
	    opacity: 0.3;
	}
	
	.gnb2 button:hover {
	    opacity: 1;
	}
	
	.gnb2 button:before {
	    content: '\f0da';
	    font-family: fontawesome;
	    margin-right: 10px;
	    opacity: 0;
	}
	
	.gnb2 button:hover:before {
	    opacity: 1;
	} 
}

