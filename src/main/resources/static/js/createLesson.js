var min = Number.POSITIVE_INFINITY;
var max = Number.NEGATIVE_INFINITY;
var arr;

$(document).ready(function () {
    renderLessons($("#group").val())
})

$("#group").change(function () {
    renderLessons($("#group").val())
})

$("#week").change(function () {
    renderLessons($("#group").val())
})

$("#lesson").submit(function (e) {
    if (!$("#weekTypeChislitel").is(":checked") && !$("#weekTypeZnamenatel").is(":checked")) {
        alert("Числитель или знаменатель не могут быть одновременно не выбраны!")
    } else {
        e.preventDefault()
        createLesson();
    }
})

function createLesson() {
    let formData = {
        disciplineId: parseInt($("#discipline").val()),
        weekId: parseInt($("#week").val()),
        timeLessonId: parseInt($("#timeLesson").val()),
        lecture: $("#lecture").is(":checked"),
        weekTypeChislitel: $("#weekTypeChislitel").is(":checked"),
        weekTypeZnamenatel: $("#weekTypeZnamenatel").is(":checked"),
        groupId: parseInt($("#group").val()),
        link: $("#link").val(),
        link2: $("#link2").val(),
        teacherId: $("#teacherId").val()
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "https://lessons-api-university.herokuapp.com/api/lessons",
        data: JSON.stringify(formData),
        data_type: "json",
        beforeSend: function (xhr) {
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            xhr.setRequestHeader(header, token)
        },
        success: function (data) {
            alert(data.responseText)
        },
        error: function (data) {
            alert(data.responseText)
            window.location.href = "https://lessons-api-university.herokuapp.com/teacher/lesson/create"
        }
    })
}

// function findMinMax(data, min, max) {
//     let tmp;
//     for (let i = 0; i < data.length; i++) {
//         tmp = data[i].numTimeLesson;
//         if (tmp <= min) {
//             window.min = tmp
//         }
//         if (tmp >= max) {
//             window.max = tmp
//         }
//     }
// }

// function createArray(data) {
//     let array = new Array();
//     // if (i <= data.length - 1) {
//     //     if (data[i].numTimeLesson - 1 == i) {
//     //         array.push(data[i])
//     //     }
//     // } else {
//     //     array.push(null)
//     // }
//     for (let i = 0; i < data.length; i++) {
//         let k;
//         // if (i == 0 &&) {
//         //     j = data[i].numTimeLesson - 1;
//         // } else {
//         //     j = data[i - 1].numTimeLesson - 1;
//         // }
//         if (i == 0 && data[i].numTimeLesson - 1 != 0) {
//             k = 0
//         } else if (i == 0) {
//             k = data[i].numTimeLesson - 1;
//         } else {
//             k = data[i - 1].numTimeLesson - 1;
//         }
//         let length
//         if (data[i].numTimeLesson - 1 == 0) {
//             length = data[i].numTimeLesson
//         } else if (i == data.length) {
//             le
//         }
//         else if () {
//             length = data[i].numTimeLesson - 1
//         }
//         for (let j = k; j < length; j++) {
//             if (data[i].numTimeLesson - 1 == j) {
//                 array.push(data[i])
//             } else if (data[i].numTimeLesson - 1 == j + 1) {
//                 array.push(data[i])
//             } else {
//                 array.push(null)
//             }
//         }
//     }
//     console.log(array)
// }


function renderLessons(id) {
    $.get("https://lessons-api-university.herokuapp.com/api/all-lesson-by-week-groupId/" + $("#week").val() + "/" + id, function (data) {
        let lessons = ''
        let typeLesson = ''
        let typeWeek = ''

        $.each(data, function (index, lesson) {
            lessons += `
                    <tr>
                        <td scope="row">${lesson.timeLesson}</td>
                        <td scope="row">${lesson.discipline}</td>
                        <td scope="row">${lesson.group}</td>
                        <td scope="row">${lesson.teacher}</td>
                `
            if (lesson.isLecture) {
                typeLesson = 'Лекция'
            } else {
                typeLesson = 'Лабораторная'
            }
            lessons += `<td scope="row">${typeLesson}</td>`
            if (lesson.weekTypeZnamenatel && lesson.weekTypeChislitel) {
                typeWeek = 'Каждую неделю'
            } else if (lesson.weekTypeZnamenatel) {
                typeWeek = 'Знаменатель'
            } else {
                typeWeek = 'Числитель'
            }
            lessons += `<td>${typeWeek}</td></tr>`
        })
        document.querySelector("#lessons tbody").innerHTML = lessons
    })
}