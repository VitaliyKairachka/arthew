let stompClient = null;

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  } else {
    $("#conversation").hide();
  }
  // $("#greetings").html("");
}

function connect() {
  const socket = new SockJS('/ws');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/login', function (login) {
      window.location.href = "StartPage.html";
      // login(JSON.parse(login.booleanValue).content);
    });
  });
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
}

function sendName() {
  stompClient.send("/app/login", {},
      JSON.stringify({
        'login': $("#login").val(),
        'password': $("#password").val()
      }));
}

function login(boolean) {
  window.location.href = "StartPage.html";
}

$(function () {
  $("form").on('submit', function (e) {
    e.preventDefault();
  });
  $("#connect").click(function () {
    connect();
    sendName();
  });
  $("#disconnect").click(function () {
    disconnect();
  });
  // $("#send").click(function () {
  //   sendName();
  // });
});