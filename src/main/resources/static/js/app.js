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
    $('.nav-link').on("click", onClickTab)
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
        $('.nav').css("display", "flex");

        $('#inputLogin').val("");
        $('#inputPassword').val("");
    } else {
        $("#login-form").css("display", "block");
        $("#buttonLogout").css("display", "none");
        $('.tab-content, .nav').css("display", "none");
    }

    const $adminButtons = $('.users-tab, .tasks-tab');

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

function onClickTab(e) {
    e.preventDefault();
    $('.nav-link').removeClass("active");
    $(e.currentTarget).addClass("active");

    const tabName = e.currentTarget.dataset.id;
    switch (tabName) {
        case "country":
            window.showCountriesTab();
            break;
        case "region":
            window.showRegionsTab();
            break;
        case "place":
            window.showPlacesTab();
            break;
        case "hotel":
            window.showHotelsTab();
            break;
        case "number":
            window.showNumbersTab();
            break;
        case "task":
            window.showTasksTab();
            break;
        case "user":
            window.showUsersTab();
            break;
    }
}