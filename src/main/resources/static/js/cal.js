let height = document.getElementById("height");
let weight = document.getElementById("weight");
let ageValue = document.getElementById("age");

const recommendBtn = document.getElementById("recommendBtn");

const noneTb = document.getElementById("noneTb");
const lowTb = document.getElementById("lowTb");
const mediumTb = document.getElementById("mediumTb");
const highTb = document.getElementById("highTb");

const noneTh = document.getElementById("noneTh");
const lowTh = document.getElementById("lowTh");
const mediumTh = document.getElementById("mediumTh");
const highTh = document.getElementById("highTh");

const SHADOW = "0 0 5px #0fbad8,0 0 5px #0fbad8,0 0 5px #fff,0 0 5px #0fbad8,0 0 5px #0fbad8,0 0 5px #0fbad8,0 0 5px #0fbad8,0 0 5px #fff";

let calResult = document.getElementById("calResult");

function getResult() {
  event.preventDefault();

  if (!document.querySelector("input[name='gender']:checked")) {
    swal({
      title: "성별을 선택해주세요",
      icon: "info",
      button: "확인",
    });
    return false;
  }

  if (height.value == "") {
    swal({
      title: "키(cm)를 입력해주세요",
      icon: "info",
      button: "확인",
    });
  }

  if (weight.value == "") {
    swal({
      title: "몸무게(kg)를 입력해주세요",
      icon: "info",
      button: "확인",
    });
  }

  if (!document.querySelector("input[name='level']:checked")) {
    swal({
      title: "활동레벨을 선택해주세요",
      icon: "info",
      button: "확인",
    });
  }

  if (
    weight.value != "" &&
    height.value != "" &&
    document.querySelector("input[name='gender']:checked").value &&
    document.querySelector("input[name='level']:checked").value
  ) {
    calResult.innerHTML = `🥑 당신의 하루 권장 칼로리는 ${Math.round(
      (10 * weight.value +
        6.25 * height.value -
        5.0 * ageValue.options[ageValue.selectedIndex].value +
        Number(document.querySelector("input[name='gender']:checked").value)) *
        document.querySelector("input[name='level']:checked").value
    )}Kcal 입니다.`;
  }
}

function getDiet() {
  if (calResult.textContent == "") {
    swal({
      title: "하루 권장 칼로리를 확인해주세요",
      icon: "info",
      button: "확인",
    });
  } else {
    window.location.href = 'diet';
  }
}

/* 활동 레벨 버튼 클릭시 TABLE 포커스 */
function focusTable(event) {
  if (event.target.outerText == noneTh.outerText) {
    (noneTb.style.textShadow = SHADOW) &&
      (lowTb.style.textShadow = "none") &&
      (mediumTb.style.textShadow = "none") &&
      (highTb.style.textShadow = "none");
  } else if (event.target.outerText == lowTh.outerText) {
    (lowTb.style.textShadow = SHADOW) &&
      (noneTb.style.textShadow = "none") &&
      (mediumTb.style.textShadow = "none") &&
      (highTb.style.textShadow = "none");
  } else if (event.target.outerText == mediumTh.outerText) {
    (mediumTb.style.textShadow = SHADOW) &&
      (noneTb.style.textShadow = "none") &&
      (lowTb.style.textShadow = "none") &&
      (highTb.style.textShadow = "none");
  } else if (event.target.outerText == highTh.outerText) {
    (highTb.style.textShadow = SHADOW) &&
      (noneTb.style.textShadow = "none") &&
      (mediumTb.style.textShadow = "none") &&
      (lowTb.style.textShadow = "none");
  }
}

calResult.style.fontWeight = "bold";
calResult.style.fontSize = "17px";
calResult.style.color = "#77b347;";