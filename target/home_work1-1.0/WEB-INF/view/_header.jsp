<%--
  Created by IntelliJ IDEA.
  User: Adelya
  Date: 29.09.2021
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signin</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.2.6/gsap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value="/css/project.css"/>">
    <link rel="script" href="<c:url value="/js/javascript.js"/>">
    <script>
        window.onload = function (){
            btn = document.getElementById("changeTheam");
            body = document.getElementsByTagName('body')[0];
            btn.onclick = function (){
                body.style.backgroundColor = "FFCCCC";
            }

        }
    </script>
</head>
<body>

