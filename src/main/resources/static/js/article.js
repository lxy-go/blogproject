
window.onload=function()//用window的onload事件，窗体加载完毕的时候
{
    getComment();
    console.log("xx");
};
function getArticleId() {
    var pathname = window.location.pathname;
    var articleId = pathname.split("/")[2];
    return articleId;
}
// 添加文章评论信息
var articleId = getArticleId();

// 增加评论
$('#addComment').click(function () {
    var articleId = getArticleId();
    var name = $('#commentName').val();
    var email = $('#commentEmail').val();
    var content = $('#commentContent').val();

    // 判断是否为空
    if ("" == name || "" == content) {
        $('#modal').modal();
        return;
    }

    // 不为空才能增加
    var comment = {
        name: name,
        email: email,
        content: content
    };
    // 提交AJAX请求
    $.ajax({
        url: "/api/comment/article/" + articleId,
        type: "POST",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(comment),
        success: function () {
            // 显示成功提示信息
            $('#addModal').modal();
            getComment();
        },
        error: function () {
            $('#addModal').modal();
        }
    })
});


function getComment(){
    console.log("zzzz")
    $.ajax({
        type: "get",
        url: "/api/comment/article/" + articleId,
        dataType: "json",
        success: function (json) {
            $('#commentList').empty();
            // 解析json对象，并向页面添加数据
            $.each(json.data, function (i, item) {
                $('#commentList').append(
                    '<div class="comment">' +
                    '<label class="commentName">' + item.name + '</label> <br />' +
                    '<p class="commentContent" style="border-style: inset;padding-left:10px;">' + item.content + '</p>' +
                    '</div></div>'
                );
            });
        }
    });
}

// 模态框确认按钮点击事件
$('#addConfirmBtn').click(function () {
    $("#addModal").modal("hide");
    clearAll();
});

function clearAll(){
    $('#commentName').val("");
    $('#commentEmail').val("");
    $('#commentContent').val("");
}