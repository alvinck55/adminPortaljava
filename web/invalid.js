/*function sendInfo(){
    var username = document.form.username.value();
    var password = document.form.password.value();
    if(window.XMLHttpRequest){
        request = new XMLHttpRequest();
    }
    else if{window.ActiveXObject}{
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }
    try{
        request.onreadystatechange=getInfol;
        request.open("GET","/AdminPortal_war_exploded/LoginServlet",true);
        request.send();
    }
    catch(e){
        alert("exception");
    }
}
function getInfo(){
    if(request.readyState == 4){
        var response = request.responseText;
        document.getElementById("serverResponse").innerHTML=response;
    }
}*/