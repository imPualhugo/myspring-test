<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改章节</title>
</head>
<body>
章节名称 : <input id="name"> <br>
内容 : <div id="content"></div> <br>
<button  onclick="updateChapter()">提交</button> <button onclick="backChapter()">取消</button>

</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/wangeditor@latest/dist/wangEditor.min.js"></script>
<script>
    var editor ;
    //预加载
    $(function () {
        //初始化富文本编辑器
        const E = window.wangEditor
        editor = new E("#content")
        editor.create()

        getParamter();
        //根据id获取章节的内容
        getChapterById();
    })
    //根据id获取章节内容
    function getChapterById() {
        $.post("/day18_bqg_back/chapter/getById", {'id': id},function (data) {
                if (data.code == -1) {
                    alert(data.message);
                }else{
                    $("#name").val(data.data.name);
                    editor.txt.html(data.data.content);
                }

            }, "json");
    }

    //返回图书详情页面
    function backChapter() {
        location.href = "/day18_bqg_back/jsp/chapter.jsp?bookId=" + bookId;
    }

    function  updateChapter(){
        var name = $("#name").val();
        var content = editor.txt.html();
        if (name == undefined || name == '' || content == undefined || content == '') {
            alert("对不起,章节内容不能为空")
            return;
        }

        $.post("/day18_bqg_back/chapter/update", {'id': id, 'name': name, 'content': content},
            function (data) {
                if (data.code == -1) {
                    alert(data.message);
                }else{
                    location.href = "/day18_bqg_back/jsp/chapter.jsp?bookId=" + bookId;
                }

            }, "json");
    }


    //获取bookId
    var id ;
    var bookId ;
    function getParamter(){
        var ps = location.search.split("&");
        id = ps[0].split("=")[1];
        bookId = ps[1].split("=")[1];
    }

</script>
</html>
