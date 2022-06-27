$("#resetPassword").submit(function (e) {
    e.preventDefault()
    if ($("#password1").val() === $("#password2").val()) {
        resetPassword()
    } else {
        alert("Пароли не совпадают!")
    }
})

function resetPassword() {
    let formData = {
        userId: $("#userId").val(),
        password: $("[name='password']").val()
    }
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "https://lessons-api-university.herokuapp.com/api/users/resetPassword",
        data: JSON.stringify(formData),
        data_type: "json",
        beforeSend: function (xhr) {
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            xhr.setRequestHeader(header, token)
        }
    }).done(function () {
        alert("Пароль был изменен!")
        window.location.href = "/login"
    })


}