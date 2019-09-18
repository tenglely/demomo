//加载文章内容
function  put_article_content() {
    $.ajax({
       url:"/user/findonearticle",
        type:"GET",
        success:function (result) {
            var title=$("<h2></h2>").append(result.extend.article.title);
            var date=result.extend.article.date;
            var number=result.extend.article.number;
            var state=result.extend.article.state;
            var username=result.extend.username;
            var p=$("<label></label>").append(state).append("&nbsp;&nbsp;")
                .append(username).append("&nbsp;&nbsp;").append("浏览量:&nbsp;"+number).append("&nbsp;&nbsp;发布时间:")
                .append(date);
            var content=result.extend.article.content;
            var d1=$("<div></div>").addClass("d2").append(title).append(p);
            var d2=$("<div></div>").addClass("d2").append(content);
            $("#list").append(d1).append(d2);
        }
    });
}

//加载个人分类
function put_peopletype() {
    $.ajax({
        url:"/user/persontype",
        type:"GET",
        success:function (result) {
            $.each(result.extend.plist,function(index,item){
                var a= $("<a></a>").append(item).addClass("a4 m1 f1 aforp").attr("href","#a1").attr("read",item);
                $("#petype").append(a).append("<br />");
            });
        }
    });
}

//点击个人分类内容后刷新文章列表
function upload_aticle_by_peopletype(type) {
    $.ajax({
       url:"/user/uploadlist/"+type,
       type:"GET",
        success:function (result) {
           var a=result.extend.list;
            $("#list").empty();
            puttothelist(a);
        }
    });
}

//查询文章，并放上去
function allArticle(pn) {
    $.ajax({
        url:"/user/allArticle/"+pn,
        type:"GET",
        success:function (result) {
            //数据放上去
            var a=result.extend.list.list;
            puttothelist(a);
        }
    });
}
//文章数据放上去
function puttothelist(result){
    $.each(result,function (index,item) {
        var title=$("<td></td>").append($("<h3></h3>").append(item.title));
        var c=/p/g;var d=/</g;var cc=/>/g;var dd=/\//g;var d1=/br/g;
        var a=item.content.replace(c,"");
        var aa=a.replace(d,"");
        var aaa=aa.replace(cc,"");
        var a1=aaa.replace(dd,"");
        var a2=a1.replace(d1,"");
        var content=$("<td></td>").append($("<span></span>").append(a2.substring(0,80)));
        var date=$("<td></td>").append($("<span></span>").append(item.date).append("&nbsp;|&nbsp;阅读次数：").append(item.number).append("次"));
        var t=$("<table></table>").addClass("t1").append($("<tr></tr>").append(title))
            .append($("<tr></tr>").append(content)).append($("<tr></tr>").append(date));
        var a3=$("<a></a>").addClass("a4").attr("href","/user/read.html/"+item.aid).append(t);
        var id=item.id;
        var b1=$("<buttou></buttou>").addClass("btn btn-info btn-sm see_a").attr("see_id",id).append("编辑");
        var b2=$("<button></button>").addClass("btn btn-danger btn-sm del_a").attr("del_id",id).append("删除");
        var tt=$("<table></table>").addClass("t2").append($("<tr></tr>").append($("<td></td>").attr("align","right").append(b1).append("&nbsp;&nbsp;").append(b2)))
        $("#list").append(a3).append(tt);
    });
}

//跳转到文章修改页
$(document).on("click",".see_a",function () {
    var aid=$(this).attr("see_id");
    $.ajax({
       url:"/user/putid/"+aid,
       type:"GET",
       success:function (result) {
           window.location.href="/user/upload.html";
       }
    });
});

//放入修改页的数据
function putarticle(){
    $.ajax({
        url:"/user/uploadtext",
        type:"GET",
        success:function (result) {
            $("#p1").append(result.extend.article.content);
            $("#text1").append(result.extend.article.content);
            $("#title").val(result.extend.article.title);
            var a=result.extend.list[0].type;
            $.each(result.extend.list,function(index,item){
               var a=item.doctype;
               if(a=="文章分类"){
                   if(item.type=="转载"){
                       $("#articleType").val("转载");
                   }
                   if(item.type=="翻译"){
                       $("#articleType").val("翻译");
                   }
               }
               if(a=="个人分类"){
                   $("#person").val(item.type);
               }
               if(a=="文章标签"){
                   if(item.type=="学习"){
                       $("input[name='articleSpan'][value='学习']").attr("checked",true);
                   }
                   if(item.type=="兴趣爱好"){
                       $("input[name='articleSpan'][value='兴趣爱好']").attr("checked",true);
                   }
                   if(item.type=="生活"){
                       $("input[name='articleSpan'][value='生活']").attr("checked",true);
                   }
                   if(item.type=="其他"){
                       $("input[name='articleSpan'][value='其他']").attr("checked",true);
                   }
               }
            });
        }
    });
}

//根据文章id删除一篇文章
$(document).on("click",".del_a",function () {
    //获取某一属性的值
    var nid=$(this).attr("del_id");
    if(confirm("你确定要删除这篇文章吗？")) {
        $.ajax({
            url: "/user/article/" + nid,
            type: "DELETE",
            success: function (result) {
                alert(result.msg);
                $("#list").empty();
                allArticle(1);
            }
        })
    }
});

//检查需重新编辑的文章的内容是否为空
function checkFormUpload() {
    if(document.getElementById("title").value.length<1||document.getElementById("title").value.length>15){
        $("#title").attr("placeholder","请输入15位以内的文章标题");
        document.getElementById("title").focus();
        return false;
    }
    if(document.getElementById("person").value.length<1){
        $("#person").attr("placeholder","个人分类不能为空，请填写");
        document.getElementById("person").focus();
        return false;
    }
    //修改文章数据
    uploadAticle_1();
}
//修改文章数据 _1
function uploadAticle_1() {
    $.ajax({
        url:"/user/uploadtext",
        type:"GET",
        success:function (result) {
            var list=result.extend.list;
            uploadAticle_2(result.extend.article.id,result.extend.article.uid,result.extend.article.aid,result.extend.article.date,result.extend.article.number,list);
        }
    });
}

/**
 * 修改文章数据 _2
 * @param id
 * @param uid
 * @param aid
 * @param date
 * @param number
 * @param list
 */
function uploadAticle_2(id,uid,aid,date,number,list) {
    var title=$("#title").val();
    var content=$("#text1").html();
    var state=$("#articleType").val();
    var reg=/%/g;
    var a=content.replace(reg,"%25");
    $.ajax({
        url:"/user/uploadArticle" ,
        data:"id="+id+"&uid="+uid+"&aid="+aid+"&title="+title+"&content="+a+"&date="+date+"&state="+state+"&number="+number,
        type:"POST",
        success:function (result) {
            alert(result.msg);
            alert(a);
            uploadClassify(list);
            window.location.href = "/user/article.html";
        }
    });
}
//修改分类
function uploadClassify(list) {
    $.each(list,function(index,item){
        var a=item.doctype;
        if(a=="文章分类"){
            var articleType=$("#articleType").val();
            //执行修改分类操作
            upload_c(item.id,articleType,a);
        }
        if(a=="个人分类"){
            var person=$("#person").val();
            upload_c(item.id,person,a);
        }
        if(a=="文章标签"){
            var articleSpan=$('input[name="articleSpan"]:checked').val();
            upload_c(item.id,articleSpan,a);
        }
    });
}

//执行修改分类操作
function upload_c(id,type,doctype) {
    $.ajax({
       url:"/user/uploadClassify",
       data:"id="+id+"&type="+type+"&doctype="+doctype,
       type:"POST",
        success:function (result) {
            console.log(result.msg);
        }
    });
}


//检查编辑的文章是否为空
function checkForm(){
    if(document.getElementById("title").value.length<1||document.getElementById("title").value.length>15){
        $("#title").attr("placeholder","请输入15位以内的文章标题");
        document.getElementById("title").focus();
        return false;
    }
    if(document.getElementById("person").value.length<1){
        $("#person").attr("placeholder","个人分类不能为空，请填写");
        document.getElementById("person").focus();
        return false;
    }
    //添加文章
    addArticle();
}

//往数据库添加文章
function addArticle() {
    var d=new Date();
    var m=d.getMonth()+1;
    var aid="a"+d.getFullYear()+m+d.getDate()+d.getHours()+d.getMinutes()+d.getSeconds();
    var date=d.getFullYear()+"-"+m+"-"+d.getDate()+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds()
    var title=$("#title").val();
    var content=$("#text1").html();
    var state=$("#articleType").val();
    //替换掉content里所有%为%25，不然报错 org.apache.tomcat.util.http.Parameters   : Character decoding failed
    var reg=/%/g;
    a=content.replace(reg,"%25");
    // alert(content);
    $.ajax({
        url:"/user/addArticle",
        data:"aid="+aid+"&title="+title+"&content="+a+"&date="+date+"&state="+state+"&number=1",
        type:"POST",
        success:function(result){
           alert(result.msg);
        }
    });
    //添加分类
    addClassify(aid);
}

//往数据库添加分类
function  addClassify(aid){
    //文章标签
    var articleSpan=$('input[name="articleSpan"]:checked').val();
    faddClassify(aid,articleSpan,"文章标签");
    //个人分类
    var person=$("#person").val();
    faddClassify(aid,person,"个人分类");
    //文章分类
    var articleType=$("#articleType").val();
    faddClassify(aid,articleType,"文章分类");
    window.location.href = "/user/article.html";
}
//执行往数据库添加分类
function faddClassify(aid,type,doctype) {
    $.ajax({
        url:"/user/addClassify" ,
        data:"aid="+aid+"&type="+type+"&doctype="+doctype,
        type:"POST",
        success:function(result){
            console.log(result.msg);
        }
    });
}