window.addEventListener("load", function (event) {
    init();
});

function init() {
    const user = getLocalUser();
    const isLoggedIn = user != null;
    onChangeLoggedInStatus(isLoggedIn, user?.role);

    const wsClient = getWSClient();
    wsClient.connect({}, function () {
        wsClient.subscribe('/topic/user/login', handleLoginTopic);
    });

    window.wsClient = wsClient

    $("#buttonLogin").on("click", onClickLogin)
    $("#buttonLogout").on("click", onClickLogout)
}

function getLocalUser() {
    const userJSON = localStorage.getItem("user");
    if (userJSON == null) {
        return null;
    }
    return JSON.parse(userJSON);
}

function saveLocalUser(user) {
    if (user == null) {
        localStorage.setItem("user", null);
    } else {
        localStorage.setItem("user", JSON.stringify(user));
    }
}

function getWSClient() {
    const socket = new SockJS('/websocket');
    return Stomp.over(socket);
}

function onChangeLoggedInStatus(isLoggedIn, role = null) {
    if (isLoggedIn) {
        $("#buttonLogout").css("display", "block")
        $("#login-form").css("display", "none");
        $('#buttons').css("display", "block");

        $('#inputLogin').val("");
        $('#inputPassword').val("");
    } else {
        $("#login-form").css("display", "block");
        $("#buttonLogout").css("display", "none");
        $('#buttons').css("display", "none");

        if (window.clearCountries != null) {
            window.clearCountries()
        }
    }

    const $adminButtons = $('#getUsersButton, #getTasksButton');

    if (role === 'ADMIN') {
        $adminButtons.css("display", "block")
    } else {
        $adminButtons.css("display", "none")
    }
}

function handleLoginTopic(message) {
    const user = JSON.parse(message.body);
    if (user.isSuccess) {
        saveLocalUser(user);
        onChangeLoggedInStatus(true, user?.role);
    } else {
        alert("Incorrect password or login")
    }
}

function onClickLogin() {
    const login = $('#inputLogin').val();
    const password = $('#inputPassword').val();
    wsClient.send(
        "/app/user/login",
        {},
        JSON.stringify({login, password}),
    );
}

function onClickLogout() {
    saveLocalUser(null);
    onChangeLoggedInStatus(false);
}