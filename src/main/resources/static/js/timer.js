var time = 0;
var running = 0;
var timerid = 0;

function startPause() {
    if (running == 0) {
        
        //시작버튼
        running = 1;
        increment();
        document.getElementById('stopTime').innerHTML="";
        document.getElementById("start").innerHTML = "STOP";
        document.getElementById("startPause").style.backgroundColor = "rgba(255,0,0,0.5)";
        

    }
    else {
        //일시정시버튼
        running = 0;
        clearTimeout(timerid);
        var date = new Date();
        var nowDate = date.getDate();
        var nowMonth = date.getMonth() + 1;
        var hour = date.getHours();
        if(hour<10){
            hour = '0'+hour;
        }
        var min = date.getMinutes();
        if(min<10){
            min = '0'+min;
        } 
        var sec = date.getSeconds();
        if(sec<10){
            sec = '0'+sec;
        }
        document.getElementById('stopTime').innerHTML= "PAUSE : "+hour+":"+min+":"+sec;
        document.getElementById("start").innerHTML = "CONTINUE";
        document.getElementById("startPause").style.backgroundColor = "rgba(51,170,51,0.5)";
    }
}
//리셋
function reset() {
    running = 0;
    time = 0;
    clearTimeout(timerid);
    document.getElementById('stopTime').innerHTML="";
    document.getElementById("start").innerHTML = "START";
    document.getElementById("output").innerHTML = "<b>00:00:00</b>";
    document.getElementById("startPause").style.backgroundColor = "rgba(51,170,51,0.5)";
}
//타이머 시간측정 
function increment() {
    if (running == 1) {
        timerid = setTimeout(function () {
            time++;
            var hours = Math.floor(time / 3600 );
            var mins = Math.floor(time % 3600 / 60 );
            var secs = time % 3600 % 60;
            if (hours < 10) {
                hours = "0" + hours;
            }
            if (mins < 10) {
                mins = "0" + mins;
            }
            if (secs < 10) {
                secs = "0" + secs;
            }
            document.getElementById("output").innerHTML = "<b>"+hours + ":" + mins + ":" + secs+"</b>";
            increment();
        }, 1000)
    }
}

var clockTarget = document.getElementById("clock");
//상단 현재 시간
function clock() {
    var date = new Date();
    // date Object를 받아오고 
    var month = date.getMonth() + 1;
    // 달을 받아옵니다 
    var clockDate = date.getDate();
    // 몇일인지 받아옵니다 
    var day = date.getDay();
    // 요일을 받아옵니다. 
    var week = ['일', '월', '화', '수', '목', '금', '토'];
    // 요일은 숫자형태로 리턴되기때문에 미리 배열을 만듭니다. 
    var hours = date.getHours();
    // 시간을 받아오고 
    var minutes = date.getMinutes();
    // 분도 받아옵니다.
    var seconds = date.getSeconds();
    // 초까지 받아온후 
    clockTarget.innerText = `${month}월 ${clockDate}일 ${week[day]}요일 ` +
        `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;
    // 월은 0부터 1월이기때문에 +1일을 해주고 
    // 시간 분 초는 한자리수이면 시계가 어색해보일까봐 10보다 작으면 앞에0을 붙혀주는 작업을 3항연산으로 했습니다. 
}

function init() {
    clock();
    // 최초에 함수를 한번 실행시켜주고 
    setInterval(clock, 1000);
    // setInterval이라는 함수로 매초마다 실행을 해줍니다.
    // setInterval은 첫번째 파라메터는 함수이고 두번째는 시간인데 밀리초단위로 받습니다. 1000 = 1초 
}

init();