<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 2021/6/1
  Time: 3:18 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的作品</title>
    <style>
        #books img{
            width: 100px;
        }

    </style>
</head>
<body>
    <div>
        添加作品 : <button onclick="openInsert()">新增</button>
    </div>
    <div id="booksDiv">
        <div >
            <input id="search" style="width: 300px"> <button onclick="search()">搜索</button>
        </div>
        <table id="books">

        </table>
        <div id="pages"></div>
    </div>
    <div id="book" style="display: none">
        <input type="hidden" id="id">
        封面图 : <input id="file" type="file" onchange="uploadPhoto()"> <input id="image" type="hidden"> <br>
        作品名称 : <input id="name"> <br>
        类型 : <select id="typeId"></select> <br>
        简介 : <textarea id="introduce"></textarea> <br>
        <button onclick="insertBook()">提交</button> <button onclick="openInsert()">取消</button>
    </div>
</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script>
    $(function () {
        getBooks();
        getTypeAll();
    })
    //搜索功能
    function search() {
        getBooks(1)
    }
    //打开新增作品的输入框
    function openInsert() {
        $("#booksDiv").toggle();
        $("#book").toggle();

        $("#book input").val('');
        $("#typeId").val(1);
        $("#introduce").val('');
    }
    //打开输入框修改内容
    function openUpdate(id) {
        //显示修改框
        openInsert();
        //查询作品信息并展示
        $.post("/day18_bqg_back/book/getById", {"id": id}, function (data) {
            //将查询的出来的作品信息填充到输入框中
            console.log(data)
            if (data.code == -1) {
                alert(data.message);
            }else{
                $("#id").val(data.data.id);
                $("#name").val(data.data.name);
                $("#image").val(data.data.image);
                $("#typeId").val(data.data.typeId);
                $("#introduce").val(data.data.introduce);
            }
        },"json");
    }

    //如果隐藏的id存在,就说明是修改,否则就是新增
    function insertBook() {
        var name = $("#name").val();
        var image = $("#image").val();
        var typeId = $("#typeId").val();
        var introduce = $("#introduce").val();

        if(name==''||image==''||typeId==''||introduce==''){
            alert("对不起,作品信息不完整")
            return;
        }
        var id = $("#id").val();
        if (id == undefined || id == '') {
            $.post("/day18_bqg_back/book/insert",
                {
                    "name": name,
                    'image':image,
                    "typeId":typeId,
                    'introduce':introduce
                }, function (data) {
                if (data.code == 0) {
                    alert("对不起,请先登录");
                    location.href = "login.jsp";
                    return;
                }
                if (data.code == -1) {
                    alert(data.message);
                }else{
                    openInsert();
                    getBooks();
                }

            },"json");
        }else{
            $.post("/day18_bqg_back/book/update", {'id':id,"name": name,'image':image,"typeId":typeId,'introduce':introduce}, function (data) {
                if (data.code == 0) {
                    alert("对不起,请先登录");
                    location.href = "login.jsp";
                    return;
                }
                if (data.code == -1) {
                    alert(data.message);
                }else{
                    openInsert();
                    getBooks();
                }

            },"json");
        }

    }


    function deleteBook(id) {

        $.get("/day18_bqg_back/book/delete",{"id":id},function (data) {
            if (data.code == -1) {
                alert(data.message);
            }else{
                getBooks();
            }
        },"json")
    }


    //查询类型
    var pageN;
    function getBooks(pageNo) {
        //自己获取条件
        if (pageNo == undefined) {
            pageNo = 1;
        }
        var search = $("#search").val();

        $.ajax({
            url: "/day18_bqg_back/book/"+ pageNo,
            data:{"name":search},
            dataType: "json",
            success: function (data) { //code com.banyuan.message data
                if (data.code == 0) {
                    alert("对不起,请先登录");
                    location.href = "login.jsp";
                    return;
                }
                if (data.code == -1) {
                    alert(data.message);
                } else {
                    var list = data.data.list;
                    var html = "<tr><td>封面图</td><td>作品名称</td><td>类型</td><td>简介</td><td>创建时间</td><td>修改时间</td><td>操作</td></tr>";
                    list.forEach(function (item){
                        console.log(item.id);
                        html += "<tr>" +
                            "<td><a href='/day18_bqg_back/jsp/chapter.jsp?bookId="+item.id+"'> <img src='/day18_bqg_back/file/getImage?imageName="+item.image+"'></a></td>" +
                            "<td>" + item.name + "</td>" +
                            "<td>" + item.typeName + "</td>" +
                            "<td>" + item.introduce + "</td>" +
                            "<td>" + item.stringCreateTime + "</td>" +
                            "<td>" + item.stringUpdateTime + "</td>" +
                            "<td><button onclick='openUpdate(" + item.id + ")'>修改</button><button onclick='deleteBook(" + item.id + ")'>删除</button></td>" +
                            "</tr>";
                    });
                    $("#books").html(html);

                    //拼接分页
                    var pageData = data.data;
                    var pages = pageData.count % pageData.pageSize == 0 ? pageData.count / pageData.pageSize : pageData.count / pageData.pageSize + 1;
                    var pageHtml = "";
                    for (let i = 1; i <= pages; i++) {
                        if(i==pageData.pageNo){
                            pageHtml += "<button style='background-color: blue;color: white'>"+i+"</button>"
                        }else{
                            pageHtml += "<button  onclick='getTypes("+i+")'>"+i+"</button>"
                        }
                    }
                    $("#pages").html(pageHtml);
                    pageN = pageData.pageNo;
                }
            }
        });
    }
    //上传图片
    function uploadPhoto() {
        var files = $("#file")[0].files;
        if(files.length==0){
            alert("对不起,请选择图片");
            return;
        }
        //创建FormData对象
        var formData = new FormData();
        formData.append('photoName', files[0]);
        $.ajax({
            url:"/day18_bqg_back/file/upload",
            type:"post",
            data:formData,
            contentType:false,
            processData:false,
            dataType: "json",
            success: function (data) {
                if (data.code == -1) {
                    alert(data.message);
                }else{
                    var imageName = data.data;
                    $("#image").val(imageName);
                }
            }
        })
    }
    //获取所有的类型
    function getTypeAll() {
        $.post("/day18_bqg_back/type/getAll", {}, function (data) {
            if (data.code == -1) {
                alert(data.message);
            }else{
                var list = data.data;
                var options = "";
                list.forEach(function (item) {
                    options += "<option value='" + item.id + "'>" + item.name + "</option>";
                });
                $("#typeId").html(options);
            }

        },"json");
    }
</script>
</html>
