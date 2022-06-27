$(document).ready(function () {
    getGroups()
})

function getGroups() {
    $.get("https://lessons-api-university.herokuapp.com/api/groups", function (data) {
        renderGroups(data)
    })
}

function renderGroups(data) {
    let groups = "";
    $.each(data, function (index, group) {
        groups += `
            <tr>
                <td scope="row">${group.faculty.name}</td>
                <td scope="row">${group.name}</td>
                <td scope="row">
                    <a class="btn btn-danger" onclick="deleteGroup(${group.id})">Удалить</a>
                    <a class="btn btn-secondary" href="/group/update/${group.id}">Обновить</a>
                </td>
            </tr>
        `.trim()
        document.querySelector("#group tbody").innerHTML = groups
    })
}

function deleteGroup(id) {
    $.ajax({
        type: "DELETE",
        url: window.location.hostname + "/api/groups/" + id,
        beforeSend: function (xhr) {
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            xhr.setRequestHeader(header, token)
        }
    }).done(function () {
        alert("Группа удалена!")
        getGroups()
    })
}