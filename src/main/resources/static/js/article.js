
// 增加评论
$('#addComment').click(function () {
    var pathname = window.location.pathname;
    var articleId = pathname.split("/")[3];
    var name = $('#commentName').val();
    var email = $('#commentEmail').val();
    var content = $('#commentContent').val();

    // 判断是否为空
    if ("" == name || "" == content) {
        $('#modal').modal();
        return;
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
        },
        error: function () {
            $('#addModal').modal();
        }
    })
});

// 模态框确认按钮点击事件
$('#addConfirmBtn').click(function () {
    // 刷新页面
    location.reload();
});