const kcal = document.querySelector(".kcal"); /* 개별 토탈 칼로리 */
const breakfastKcal = document.getElementById("breakfastKcal");
const lunchKcal = document.getElementById("lunchKcal").value;
const dinnerKcal = document.getElementById("dinnerKcal");
const totalKcal = document.querySelector(".totalKcal");

/*페이지 새로고침*/
function reload() {
  window.location.reload();
}

window.addEventListener();

totalKcal.innerHTML = `${lunchKcal}`;
