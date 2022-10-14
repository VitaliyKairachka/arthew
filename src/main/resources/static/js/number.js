(function () {
    let pageNumber = 0;

    window.addEventListener("load", function (event) {
        $("#searchNumber").on("click", onClickSearch);
        $(".tab-content-number .next").on("click", onClickNext)
        $(".tab-content-number .prev").on("click", onClickPrev)

        $('#inputNumberID').on("keyup", onChangeNumberID)

        $('#createOrUpdateNumber').on("click", onCreateNumber)
        $('#deleteNumber').on("click", onDeleteNumber)

        setTimeout(subscribe, 100)
    });

    function onChangePage() {
        loadList()
        if (pageNumber === 0) {
            $(".tab-content-number .prev").addClass("disabled")
        } else {
            $(".tab-content-number .prev").removeClass("disabled")
        }
    }

    window.showNumbersTab = () => {
        $('.tab-content').css("display", "none");
        $('.tab-content-number').css("display", "block");
    }

    function onClickSearch(e) {
        e.preventDefault();
        loadList()
    }

    function loadList() {
        const numberID = $('#inputSearchNumberID').val();
        let path = "/app/number";

        if (numberID.length !== 0) {
            path = `/app/number/id/${numberID}`
        }
        window.wsClient.send(
            path,
            {},
            JSON.stringify({page: pageNumber, size: 5})
        );
    }

    function onClickNext(e) {
        e.preventDefault();
        pageNumber += 1;
        onChangePage();
    }

    function onClickPrev(e) {
        e.preventDefault();
        pageNumber = Math.max(pageNumber - 1, 0)
        onChangePage();
    }

    function clearList() {
        $(".tab-content-number .messages").empty()
    }

    function subscribe() {
        const result = window.wsClient.subscribe('/topic/number', function (message) {
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
          <td>Description</td>
          <td>Photos</td>
          <td>Hotel id</td>
        </tr>
  `);
        $(".tab-content-number .messages").append(node);
    }

    function showMessage(item) {
        if (item.isFound === false) {
            alert("Number not found")
            return
        }

        const node = $(`
        <tr data-id="${item.id}">
          <td>${item.id}</td>
          <td>${item.description}</td>
          <td>${item.photoCount}</td>
          <td>${item.hotel.id}</td>
         </tr>
  `);
        $(".tab-content-number .messages").append(node);
    }

    function onChangeNumberID() {
        const numberID = $("#inputNumberID").val();
        if (numberID.length === 0) {
            $("#createOrUpdateNumber").text("Create")
        } else {
            $("#createOrUpdateNumber").text(`Update number by ID ${numberID}`);
        }
    }

    function onCreateNumber(e) {
        e.preventDefault();
        const numberID = $("#inputNumberID").val();
        const descriptionNumber = $("#inputNumberDescription").val();
        const hotelID = $("#inputHotelIDForNumber").val();

        const payload = {description: descriptionNumber, hotel: {id: hotelID}};

        let path = '/app/number/create';

        if (numberID.length !== 0) {
            path = `/app/number/update/${numberID}`
        }
        window.wsClient.send(
            path,
            {},
            JSON.stringify(payload)
        );
    }

    function onDeleteNumber(e) {
        e.preventDefault();
        const numberID = $("#inputNumberDeleteID").val();
        const path = `/app/number/delete/${numberID}`;
        window.wsClient.send(
            path,
            {},
            "",
        );
    }
})();