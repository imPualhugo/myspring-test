<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 2021/5/28
  Time: 9:15 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>相册</title>
    <style>
        .photo {
            display: inline-block;
            margin: 20px;
            width: 250px;
        }
        .photo img{
            width: 100%;
        }
    </style>
</head>
<body>
    <div>
        添加图片 :
        <input id="name" placeholder="请输入图片的名称">
        <input type="hidden" id="uqName">
        <input type="file" id="photo" onchange="uploadPhoto()">

        <button onclick="addPhoto()">提交</button>
    </div>
    <div id="content">
        <div class="photo">
                <img src="">
                <div>原始名称</div>
                <div>时间</div>
                <div>
                    <button onclick="updatePhoto()">修改</button>
                    <button onclick="deletePhoto()">删除</button>
                    <a href="">下载</a>
                </div>
        </div>
    </div>
</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script>

    $(function(){
        getParameter();
        getPhotos();
    })
    //更新图片的名称
    function updatePhoto(id) {
        var name = prompt("请输入新的名称");
        if (name == undefined || name == '') {
            alert("对不起,名称不能为空");
            return;
        }
        $.post("/day15/photo/update", {'id': id, 'name': name}, function (data) {
            if (data.code == -1) {
                alert(data.message);
            }else{
                location.reload();
            }
        },"json");
    }
    //删除图片
    function deletePhoto(id) {
        $.post("/day15/photo/delete", {'id': id}, function (data) {
            if (data.code == -1) {
                alert(data.message);
            }else{
                location.reload();
            }
        },"json");
    }

    function addPhoto() {
        var name  =  $("#name").val();
        if (name == undefined || name == '') {
            alert("对不起,请输入图片名称");
            return;
        }
        var uqName = $("#uqName").val();
        if (uqName == undefined || uqName == '') {
            alert("对不起,请先选择图片");
            return;
        }

        $.post("/day15/photo/insert", {'name': name, 'uqName': uqName, 'folderId': folderId}, function (data) {
            if (data.code == -1) {
                alert(data.message);
            }else{
                location.reload();
            }

        },"json");

    }
    function uploadPhoto() {
        var files = $("#photo")[0].files;
        if(files.length==0){
            alert("对不起,请选择图片");
            return;
        }
        //创建FormData对象
        var formData = new FormData();
        formData.append('photoName', files[0]);
        $.ajax({
            url:"/day15/photo/upload",
            type:"post",
            data:formData,
            contentType:false,
            processData:false,
            dataType: "json",
            success: function (data) {
                if (data.code == -1) {
                    alert(data.message);
                }else{
                    var list = data.data;
                    $("#uqName").val(list[0]);
                }
            }
        })
    }

    //获取url中的参数的值
    //?folderId=6
    var folderId;
    function getParameter(){
        var str = location.search;
        folderId = str.substring(str.lastIndexOf("=")+1);
    }

    function getPhotos() {
        $.ajax({
            url: "/day15/photo/get",
            data: {'folderId': folderId},
            dataType: "json",
            success: function (data) {
                if(data.code==-1){
                    alert(data.message);
                }else{
                    var list = data.data;
                    var html ='';
                    list.forEach(function(photo){
                        html += '<div class="photo">'+
                            '<img src="/day15/photo/getImage?uqName='+photo.uqName+'">'+
                            '<div>'+photo.name+'</div>'+
                            '<div>'+photo.createDate+'</div>'+
                            '<div>'+
                                '<button onclick="updatePhoto('+photo.id+')">修改</button>'+
                                '<button onclick="deletePhoto('+photo.id+')">删除</button>'+
                                '<a href="/day15/photo/download?id='+photo.id+'">下载</a>'+
                            '</div>'+
                        '</div> ';
                    });
                    $("#content").html(html);
                }
            }
        });
    }

</script>
</html>
