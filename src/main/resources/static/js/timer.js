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

function reset() {
    running = 0;
    time = 0;
    clearTimeout(timerid);
    document.getElementById('stopTime').innerHTML="";
    document.getElementById("start").innerHTML = "START";
    document.getElementById("output").innerHTML = "<b>00:00:00</b>";
    document.getElementById("startPause").style.backgroundColor = "rgba(51,170,51,0.5)";
}

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

function clock() {
    var date = new Date();

    var month = date.getMonth() + 1;

    var clockDate = date.getDate();

    var day = date.getDay();

    var week = ['일', '월', '화', '수', '목', '금', '토'];

    var hours = date.getHours();

    var minutes = date.getMinutes();

    var seconds = date.getSeconds();

    clockTarget.innerText = `${month}월 ${clockDate}일 ${week[day]}요일 ` +
        `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;

}

function init() {
    clock();
    setInterval(clock, 1000);
}

init();