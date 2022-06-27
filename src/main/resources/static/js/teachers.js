$(document).ready(function () {
    getUsers()
})

function renderUsers(data) {
    let users = ""
    $.each(data, function (index, user) {
        users += `
            <tr>
                <td scope="row">${user.lastName}</td>
                <td scope="row">${user.firstName}</td>
                <td scope="row">${user.patronymic}</td>
                <td scope="row">${user.username}</td>
                <td scope="row">${user.faculty.name}</td>
                <td scope="row">${user.verifyUser.isActive ? 'Активирован' : 'Неактивирован'}</td>
                <td scope="row">${user.isEnabled ?
            '<a data-id=' + user.id + ' class="deactivate btn btn-danger">Деактивировать</a>' :
            '<a data-id=' + user.id + ' class="activate btn btn-success">Активировать</a>'}
                    <a data-id=${user.id} class="delete btn btn-danger">Удалить</a>
                    <a href="/user/change/${user.id}" class="change btn btn-warning">Изменить</a>
                </td>
            </tr>
        `.trim()
        document.querySelector("#users tbody").innerHTML = users
    });
}

function getUsers() {
    $.get("https://lessons-api-university.herokuapp.com/api/users?role=TEACHER", function (data) {
        renderUsers(data)
    })
}


$("#users").on("click", function (event) {
    if (event.target.className == "activate btn btn-success") {
        activateUser($(event.target).data("id"));
    } else if (event.target.className == "deactivate btn btn-danger") {
        deactivateUser($(event.target).data("id"))
    } else if (event.target.className == "delete btn btn-danger") {
        deleteUser($(event.target).data("id"))
    } else if (event.target.className == "change btn btn-warning") {

    }
})

function deleteUser(id) {
    $.ajax({
        type: "DELETE",
        url: "https://lessons-api-university.herokuapp.com/api/users/delete/" + id,
        beforeSend: function (xhr) {
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            xhr.setRequestHeader(header, token)
        }
    }).done(function () {
        alert("Пользователь удален!")
        getUsers()
    })
}

function activateUser(id) {
    $.ajax({
        type: "PUT",
        url: "https://lessons-api-university.herokuapp.com/api/users/activate/" + id,
        beforeSend: function (xhr) {
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            xhr.setRequestHeader(header, token)
        }
    }).done(function () {
        alert("Пользователь активирован!")
        getUsers()
    })
}

function deactivateUser(id) {
    $.ajax({
        type: "PUT",
        url: "https://lessons-api-university.herokuapp.com/api/users/deactivate/" + id,
        beforeSend: function (xhr) {
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            xhr.setRequestHeader(header, token)
        }
    }).done(function () {
        alert("Пользователь деактивирован!")
        getUsers()
    })
}



