const kcal = document.querySelector(".kcal"); /* 개별 토탈 칼로리 */
const breakfastKcal = document.getElementById("breakfastKcal");
const lunchKcal = document.getElementById("lunchKcal");
const dinnerKcal = document.getElementById("dinnerKcal");
const totalKcal = document.querySelector(".totalKcal");

/*날짜*/
let today = new Date();   
let year = today.getFullYear(); //년
let month = ('0' + (today.getMonth() + 1));  //월
let date = today.getDate(); //일

/*페이지 새로고침*/
function reload() {
  window.location.reload();
}

window.onload = function () {
  /*총 칼로리 출력*/
  totalKcal.innerHTML = `총 칼로리 : ${
    Number(breakfastKcal.textContent) +
    Number(lunchKcal.textContent) +
    Number(dinnerKcal.textContent)
  } Kcal`;
  // 캡쳐 버튼 클릭 이벤트 등록
  document.getElementById("captureBtn").addEventListener("click", function (e) {
    // 마우스 커서 모양 변경 (eidt_cursor 클래스 추가)
    document.querySelector("body").classList.add("edit_cursor");
    screenshot(e);
  });
};
/* * */
function screenshot(e) {
  var startX, startY;
  var height = window.innerHeight;
  var width = window.innerWidth;
  // 배경을 어둡게 깔아주기 위한 div 객체 생성
  var $screenBg = document.createElement("div");
  $screenBg.id = "screenshot_background";
  $screenBg.style.borderWidth = "0 0 " + height + "px 0";
  // 마우스 이동하면서 선택한 영역의 크기를 보여주기 위한 div 객체 생성
  var $screenshot = document.createElement("div");
  $screenshot.id = "screenshot";
  document.querySelector("body").appendChild($screenBg);
  document.querySelector("body").appendChild($screenshot);
  var selectArea = false;
  var body = document.querySelector("body");
  // 마우스 누르는 이벤트 함수
  var mousedown = function (e) {
    e.preventDefault();
    selectArea = true;
    startX = e.clientX;
    startY = e.clientY;
    // 이벤트를 실행하면서 이벤트 삭제 (한번만 동작하도록)
    body.removeEventListener("mousedown", mousedown);
  };
  // 마우스 누르는 이벤트 등록
  body.addEventListener("mousedown", mousedown);
  // 클릭한 마우스 떼는 이벤트 함수
  var mouseup = function (e) {
    selectArea = false;
    // (초기화) 마우스 떼면서 마우스무브 이벤트 삭제
    body.removeEventListener("mousemove", mousemove);
    // (초기화) 스크린샷을 위해 생성한 객체 삭제
    $screenshot.parentNode.removeChild($screenshot);
    $screenBg.parentNode.removeChild($screenBg);
    var x = e.clientX;
    var y = e.clientY;
    var top = Math.min(y, startY);
    var left = Math.min(x, startX);
    var width = Math.max(x, startX) - left;
    var height = Math.max(y, startY) - top;
    html2canvas(document.body).then(function (canvas) {
      //전체 화면 캡쳐 // 선택 영역만큼 crop
      var img = canvas.getContext("2d").getImageData(left, top, width, height);
      var c = document.createElement("canvas");
      c.width = width;
      c.height = height;
      c.getContext("2d").putImageData(img, 0, 0);
      save(c);
      // crop한 이미지 저장
    });
    body.removeEventListener("mouseup", mouseup);
    // 마우스 커서 기본으로 변경
    document.querySelector("body").classList.remove("edit_cursor");
  };
  body.addEventListener("mouseup", mouseup);
  // 마우스무브 이벤트 함수
  function mousemove(e) {
    var x = e.clientX;
    var y = e.clientY;
    $screenshot.style.left = x;
    $screenshot.style.top = y;
    if (selectArea) {
      //캡쳐 영역 선택 그림
      var top = Math.min(y, startY);
      var right = width - Math.max(x, startX);
      var bottom = height - Math.max(y, startY);
      var left = Math.min(x, startX);
      $screenBg.style.borderWidth =
        top + "px " + right + "px " + bottom + "px " + left + "px";
    }
  }
  body.addEventListener("mousemove", mousemove);
  // 캡쳐한 이미지 저장
  function save(canvas) {
    if (navigator.msSaveBlob) {
      var blob = canvas.msToBlob();
      return navigator.msSaveBlob(blob, "오늘의 추천 식단.jpg");
    } else {
      var el = document.getElementById("target");
      el.href = canvas.toDataURL("image/jpeg");
      el.download = "오늘의 추천 식단 " + year+month+date +".jpg" ;
      el.click();
    }
  }
}
