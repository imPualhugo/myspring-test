<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 2021/6/3
  Time: 1:33 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加章节</title>
</head>
<body>
    章节名称 : <input id="name"> <br>
    内容 : <div id="content"></div> <br>
    <button  onclick="insertChapter()">提交</button> <button onclick="backChapter()">取消</button>

</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/wangeditor@latest/dist/wangEditor.min.js"></script>
<script>
    var editor ;
    //预加载
    $(function () {
        const E = window.wangEditor
        editor = new E("#content")
        editor.create()

        getParamter();
    })
    //返回图书详情页面
    function backChapter() {
        location.href = "/day18_bqg_back/jsp/chapter.jsp?bookId=" + bookId;
    }

    function  insertChapter(){
        var name = $("#name").val();
        var content = editor.txt.html();
        if (name == undefined || name == '' || content == undefined || content == '') {
            alert("对不起,章节内容不能为空")
            return;
        }

        $.post("/day18_bqg_back/chapter/insert", {'bookId': bookId, 'name': name, 'content': content},
            function (data) {
                if (data.code == -1) {
                    alert(data.message);
                }else{
                    location.href = "/day18_bqg_back/jsp/chapter.jsp?bookId=" + bookId;
                }

            }, "json");
    }


    //获取bookId
    var bookId ;
    function getParamter(){
        bookId = location.search.split("=")[1];
    }

</script>
</html>
