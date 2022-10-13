(function () {
    let pageTask = 0;

    window.addEventListener("load", function (event) {
        $("#searchTask").on("click", onClickSearch);
        $(".tab-content-task .next").on("click", onClickNext)
        $(".tab-content-task .prev").on("click", onClickPrev)

        $('#inputTaskID').on("keyup", onChangeTaskID)

        $('#createOrUpdateTask').on("click", onCreateTask)
        $('#deleteTask').on("click", onDeleteTask)

        setTimeout(subscribe, 100)
    });

    function onChangePage() {
        loadList()
        if (pageTask === 0) {
            $(".tab-content-task .prev").addClass("disabled")
        } else {
            $(".tab-content-task .prev").removeClass("disabled")
        }
    }

    window.showTasksTab = () => {
        $('.tab-content').css("display", "none");
        $('.tab-content-task').css("display", "block");
    }

    function onClickSearch(e) {
        e.preventDefault();
        loadList()
    }

    function loadList() {
        const taskID = $('#inputSearchTaskID').val();
        const taskName = $('#inputSearchTaskName').val();
        let path = "/app/task";

        if (taskID.length !== 0) {
            path = `/app/task/id/${taskID}`
        } else if (taskName.length !== 0) {
            path = `/app/task/name/${taskName}`
        }
        window.wsClient.send(
            path,
            {},
            JSON.stringify({page: pageTask, size: 5})
        );
    }

    function onClickNext(e) {
        e.preventDefault();
        pageTask += 1;
        onChangePage();
    }

    function onClickPrev(e) {
        e.preventDefault();
        pageTask = Math.max(pageTask - 1, 0)
        onChangePage();
    }

    function clearList() {
        $(".tab-content-task .messages").empty()
    }

    function subscribe() {
        const result = window.wsClient.subscribe('/topic/task', function (message) {
            clearList();
            showHeader();
            const data = JSON.parse(message.body);
            if (Array.isArray(data)) {
                data.forEach(item => {
                    showMessage(item);
                })
            } else {
                showMessage(data);
            }
        });
        window.clearCountries = () => {
            result.unsubscribe();
            clearList();
        }
    }

    function showHeader() {
        const node = $(`
        <tr>
          <td>ID</td>
          <td>Name</td>
          <td>Places</td>
          <td>Hotels</td>
        </tr>
  `);
        $(".tab-content-task .messages").append(node);
    }

    function showMessage(item) {
        if (item.isFound === false) {
            alert("Task not found")
            return
        }

        const node = $(`
        <tr data-id="${item.id}">
          <td>${item.id}</td>
          <td>${item.name}</td>
          <td>${item.description}</td>
          <td>${item.notification}</td>
         </tr>
  `);
        $(".tab-content-task .messages").append(node);
    }

    function onChangeTaskID() {
        const taskID = $("#inputTaskID").val();
        if (taskID.length === 0) {
            $("#createOrUpdateTask").text("Create")
        } else {
            $("#createOrUpdateTask").text(`Update task by ID ${taskID}`);
        }
    }

    function onCreateTask(e) {
        e.preventDefault();
        const taskID = $("#inputTaskID").val();
        const taskName = $("#inputTaskName").val();
        const userID = $("#inputUserIDForTask").val();

        const payload = {name: taskName, user: {id: userID}};

        let path = '/app/task/create';

        if (taskID.length !== 0) {
            path = `/app/task/update/${taskID}`
        }
        window.wsClient.send(
            path,
            {},
            JSON.stringify(payload)
        );
    }

    function onDeleteTask(e) {
        e.preventDefault();
        const taskID = $("#inputTaskDeleteID").val();
        const path = `/app/task/delete/${taskID}`;
        window.wsClient.send(
            path,
            {},
            "",
        );
    }
})();