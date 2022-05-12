let height = document.getElementById("height");
let weight = document.getElementById("weight");
let ageValue = document.getElementById("age");

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

calResult.style.fontWeight = "bold";
calResult.style.fontSize = "17px";
calResult.style.color = "green";
