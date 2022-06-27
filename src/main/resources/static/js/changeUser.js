function changeRole() {
    role = $("#role").val()
    if (role === 'TEACHER') {
        $("#faculty").prop("disabled", false)
        $("#group").prop("disabled", true)
    } else {
        $("#faculty").prop("disabled", true)
        $("#group").prop("disabled", false)
    }
}

$("#role").on("change", function () {
    changeRole()
})

$(document).ready(function () {
    changeRole()
})

$("#user").submit(function (e) {
    e.preventDefault()
    changeUser()
})

function changeUser() {
    let formData = {
        userId: $("#userId").val(),
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        patronymic: $("#patronymic").val(),
        mobilePhoneNumber: $("#mobilePhoneNumber").val(),
        username: $("#username").val(),
        email: $("#email").val(),
        facultyId: parseInt($("#faculty").val()),
        groupId: parseInt($("#group").val()),
        role: $("#role").val()
    }
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "https://lessons-api-university.herokuapp.com/api/users",
        data: JSON.stringify(formData),
        data_type: "json",
        beforeSend: function (xhr) {
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            xhr.setRequestHeader(header, token)
        }
    })
}