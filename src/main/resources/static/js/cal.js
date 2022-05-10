let height = document.getElementById("height");
let weight = document.getElementById("weight");
let ageValue = document.getElementById("age");

let calResult = document.getElementById("calResult");

function getResult(event) {
  event.preventDefault();

  if (!document.querySelector("input[name='gender']:checked")) {
    alert("성별체크");
  }

  if (height.value == "") {
    alert("키 입력");
  }

  if (weight.value == "") {
    alert("몸무게 입력");
  }

  if (!document.querySelector("input[name='level']:checked")) {
    alert("활동레벨체크");
  }

  calResult.innerHTML = `✔ 당신의 하루 권장 칼로리는 ${Math.round(
    10 * weight.value +
      6.25 * height.value -
      5.0 * ageValue.options[ageValue.selectedIndex].value +
      Number(document.querySelector("input[name='gender']:checked").value) *
        document.querySelector("input[name='level']:checked").value
  )}Kcal 입니다.`;
}

calResult.style.fontWeight = "bold";
calResult.style.fontSize = "17px";
calResult.style.marginBottom = "3%";
