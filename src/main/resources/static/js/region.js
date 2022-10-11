window.addEventListener("load", function(event) {
    $("#getRegionButton").on("click", onClick);

    setTimeout(subscribe, 100)
});

function onClick() {
    window.wsClient.send(
        "/app/region",
        {},
        JSON.stringify({page: 0, size: 100}) //TODO page
    );
}

function clearList() {
    $("#messages").empty()
}

function subscribe() {
    const result = window.wsClient.subscribe('/topic/region', function (message) {
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
    window.clearRegions = () => {
        result.unsubscribe();
        clearList();
    }
}

function showHeader() {
    const node = $(`
        <tr>
          <td>Name</td>
          <td>Places</td>
          <td>Hotels</td>
          <td>Country id</td>
        </tr>
  `);
    $("#messages").append(node);
}

function showMessage(item) {
    const node = $(`
        <tr data-id="${item.id}">
          <td>${item.name}</td>
          <td>${item.placeCounter}</td>
          <td>${item.hotelCounter}</td>
          <td>${item.country.id}</td>
         </tr>
  `)
    node.on("click", function (e) {
        e.preventDefault();
        const id = e.currentTarget.dataset.id;
        wsClient.send(`/app/region/id/${id}`, {}, "");
    })
    $("#messages").append(node);
}
