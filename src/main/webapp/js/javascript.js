window.onload = function (){
    btn = document.getElementById("changeTheam");
    body = document.getElementsByTagName('body')[0];
    btn.onclick = function (){
        body.style.backgroundColor = "FFCCCC";
    }

}