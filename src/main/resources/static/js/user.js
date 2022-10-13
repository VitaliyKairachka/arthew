(function () {
    let pageUser = 0;

    window.addEventListener("load", function (event) {
        $("#searchUser").on("click", onClickSearch);
        $(".tab-content-user .next").on("click", onClickNext)
        $(".tab-content-user .prev").on("click", onClickPrev)

        $('#inputUserID').on("keyup", onChangeUserID)

        $('#createOrUpdateUser').on("click", onCreateUser)
        $('#deleteUser').on("click", onDeleteUser)

        setTimeout(subscribe, 100)
    });

    function onChangePage() {
        loadList()
        if (pageUser === 0) {
            $(".tab-content-user .prev").addClass("disabled")
        } else {
            $(".tab-content-user .prev").removeClass("disabled")
        }
    }

    window.showUsersTab = () => {
        $('.tab-content').css("display", "none");
        $('.tab-content-user').css("display", "block");
    }

    function onClickSearch(e) {
        e.preventDefault();
        loadList()
    }

    function loadList() {
        const userID = $('#inputSearchUserID').val();
        const userName = $('#inputSearchUserName').val();
        let path = "/app/user";

        if (userID.length !== 0) {
            path = `/app/user/id/${userID}`
        } else if (userName.length !== 0) {
            path = `/app/user/login/${userName}`
        }
        window.wsClient.send(
            path,
            {},
            JSON.stringify({page: pageUser, size: 5})
        );
    }

    function onClickNext(e) {
        e.preventDefault();
        pageUser += 1;
        onChangePage();
    }

    function onClickPrev(e) {
        e.preventDefault();
        pageUser = Math.max(pageUser - 1, 0)
        onChangePage();
    }

    function clearList() {
        $(".tab-content-user .messages").empty()
    }

    function subscribe() {
        const result = window.wsClient.subscribe('/topic/user', function (message) {
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
          <td>Login</td>
          <td>FIO</td>
          <td>Role</td>
        </tr>
  `);
        $(".tab-content-user .messages").append(node);
    }

    function showMessage(item) {
        if (item.isFound === false) {
            alert("User not found")
            return
        }

        const node = $(`
        <tr data-id="${item.id}">
          <td>${item.id}</td>
          <td>${item.login}</td>
          <td>${item.fio}</td>
          <td>${item.role}</td>
         </tr>
  `);
        $(".tab-content-user .messages").append(node);
    }

    function onChangeUserID() {
        const userID = $("#inputUserID").val();
        if (userID.length === 0) {
            $("#createOrUpdateUser").text("Create")
        } else {
            $("#createOrUpdateUser").text(`Update user by ID ${userID}`);
        }
    }

    function onCreateUser(e) {
        e.preventDefault();
        const userID = $("#inputUserID").val();
        const userLogin = $("#inputUserName").val();
        const userPassword = $("#inputUserPassword").val();
        const userFIO = $("#inputUserFIO").val();
        const userRole = $("#inputUserRole").val();

        const payload = {
            login: userLogin,
            password: userPassword,
            fio: userFIO,
            role: userRole
        };

        let path = '/app/user/create';

        if (userID.length !== 0) {
            path = `/app/user/update/${userID}`
        }
        window.wsClient.send(
            path,
            {},
            JSON.stringify(payload)
        );
    }

    function onDeleteUser(e) {
        e.preventDefault();
        const userID = $("#inputUserDeleteID").val();
        const path = `/app/user/delete/${userID}`;
        window.wsClient.send(
            path,
            {},
            "",
        );
    }
})();