<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 2021/6/3
  Time: 9:55 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍详情</title>
    <style>
        .chapter{
            display: inline-block;
            margin: 20px;
        }

    </style>
</head>
<body>
    <%--    作品信息--%>
    <div id="book">
        <img id="bookImage" src=""> <br>
        <label>书名: </label><div id="bookName"></div>
        <label>简介: </label><div id="bookIntr"></div>
        <label>修改时间: </label><div id="bookeCreateDate"></div>
    </div>
    <button onclick="toAddPage()">添加章节</button>
    <hr>

    <%--    章节信息--%>

    <div id="chapters">

    </div>
</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script>
    $(function () {
        //获取参数
        getParamter();
        //获取图书信息
        getBookById();
        //获取章节信息
        getChapters();
    });

    //跳转到添加页面
    function toAddPage() {
        location.href = "/day18_bqg_back/jsp/chapterAdd.jsp?bookId=" + bookId;
    }


    function deleteChapter(id) {
        $.post("/day18_bqg_back/chapter/delete", {"id": id}, function (data) {
            if (data.code == -1) {
                alert(data.message);
            }else{
                getChapters();
            }
        },"json");
    }
    function getChapters() {
        $.post("/day18_bqg_back/chapter/get", {"bookId": bookId}, function (data) {
            if (data.code == -1) {
                alert(data.message);
            }else{
                var chapters = data.data;
                var html = "";
                chapters.forEach(function (item) {
                    html += "<div class='chapter'>" +
                        "<div>"+item.name+"</div>" +
                        "<div><a href='/day18_bqg_back/jsp/chapterUpdate.jsp?id="+item.id+"&bookId="+item.bookId+"'>修改</a><a href='javascript:void(0)' onclick='deleteChapter("+item.id+")'>删除</a> </div>" +
                        "</div>"
                });
                $("#chapters").html(html);
            }
        },"json");
    }

    //获取作品信息
    function getBookById() {
        $.post("/day18_bqg_back/book/getById", {'id': bookId}, function (data) {
            if (data.code==-1) {
                alert(data.message);
            }else{
                var book = data.data;
                $("#bookImage").prop("src", "/day18_bqg_back/file/getImage?imageName=" + book.image);
                $("#bookName").text(book.name);
                $("#bookIntr").text(book.introduce);
                $("#bookeCreateDate").text(book.stringUpdateTime);
            }

        },"json");
    }

    //获取bookId
    var bookId ;
    function getParamter(){
        bookId = location.search.split("=")[1];
    }
</script>
</html>
