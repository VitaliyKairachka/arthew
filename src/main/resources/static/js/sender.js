function sendMessage() {
  stompClient.send("/app/login", {},
      JSON.stringify({
        'login': $("#login").val(),
        'password': $("#password").val()
      }));
}