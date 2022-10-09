let stompClient = null;

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  }
  else {
    $("#conversation").hide();
  }
  $("#messages").html("");
}

function connect() {
  const socket = new SockJS('/websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log('Connected to: ' + frame);
    stompClient.subscribe('/topic/messages', function (message) {
      console.log('received: ' + message.body);
      const data = JSON.parse(message.body);
      if (Array.isArray(data)) {
        data.forEach(item => {
          showMessage(item);
        })
      } else {
        showMessage(data);
      }
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
  stompClient.send("/app/hello", {}, $("#name").val());
}

function showMessage(item) {
  const node = $(`
    <tr data-id="${item.id}">
      <td>${item.login}</td>
      <td>${item.fio}</td>
      <td>${item.role}</td>
     </tr>
  `)
  node.on("click", function (e) {
    e.preventDefault();
    const id = e.currentTarget.dataset.id
    stompClient.send(`/app/hello/${id}`, {}, $("#name").val());
  })
  $("#messages").append(node);
}

$(function () {
  $("form").on('submit', function (e) {
    e.preventDefault();
  });
  $( "#connect" ).click(function() { connect(); });
  $( "#disconnect" ).click(function() { disconnect(); });
  $( "#send" ).click(function() { sendName(); });
});