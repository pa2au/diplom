$("#sendEmail").submit(function (e) {
    e.preventDefault()
    sendEmail()
})

function sendEmail() {
    let formData = {
        email: $("#email").val()
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "https://lessons-api-university.herokuapp.com/api/users/email?email=" + formData.email,
        data: JSON.stringify(formData),
        data_type: "json",
        beforeSend: function (xhr) {
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            xhr.setRequestHeader(header, token)
        }
    }).done(function () {
        alert("На вашу почту была отправлена ссылка для восстоновления пароля")
        window.location.href = "/login"
    })
}