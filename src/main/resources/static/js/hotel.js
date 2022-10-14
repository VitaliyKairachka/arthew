(function () {
    let pageHotel = 0;

    window.addEventListener("load", function (event) {
        $("#searchHotel").on("click", onClickSearch);
        $(".tab-content-hotel .next").on("click", onClickNext)
        $(".tab-content-hotel .prev").on("click", onClickPrev)

        $('#inputHotelID').on("keyup", onChangeHotelID)

        $('#createOrUpdateHotel').on("click", onCreateHotel)
        $('#deleteHotel').on("click", onDeleteHotel)

        setTimeout(subscribe, 100)
    });

    function onChangePage() {
        loadList()
        if (pageHotel === 0) {
            $(".tab-content-hotel .prev").addClass("disabled")
        } else {
            $(".tab-content-hotel .prev").removeClass("disabled")
        }
    }

    window.showHotelsTab = () => {
        $('.tab-content').css("display", "none");
        $('.tab-content-hotel').css("display", "block");
    }

    function onClickSearch(e) {
        e.preventDefault();
        loadList()
    }

    function loadList() {
        const hotelID = $('#inputSearchHotelID').val();
        const hotelName = $('#inputSearchHotelName').val();
        let path = "/app/hotel";

        if (hotelID.length !== 0) {
            path = `/app/hotel/id/${hotelID}`
        } else if (hotelName.length !== 0) {
            path = `/app/hotel/name/${hotelName}`
        }
        window.wsClient.send(
            path,
            {},
            JSON.stringify({page: pageHotel, size: 5})
        );
    }

    function onClickNext(e) {
        e.preventDefault();
        pageHotel += 1;
        onChangePage();
    }

    function onClickPrev(e) {
        e.preventDefault();
        pageHotel = Math.max(pageHotel - 1, 0)
        onChangePage();
    }

    function clearList() {
        $(".tab-content-hotel .messages").empty()
    }

    function subscribe() {
        const result = window.wsClient.subscribe('/topic/hotel', function (message) {
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
          <td>Numbers</td>
          <td>Photos</td>
          <td>Place id</td>
        </tr>
  `);
        $(".tab-content-hotel .messages").append(node);
    }

    function showMessage(item) {
        if (item.isFound === false) {
            alert("Hotel not found")
            return
        }

        const node = $(`
        <tr data-id="${item.id}">
          <td>${item.id}</td>
          <td>${item.name}</td>
          <td>${item.numberCount}</td>
          <td>${item.photoCount}</td>
          <td>${item.place.id}</td>
         </tr>
  `);
        $(".tab-content-hotel .messages").append(node);
    }

    function onChangeHotelID() {
        const hotelID = $("#inputHotelID").val();
        if (hotelID.length === 0) {
            $("#createOrUpdateHotel").text("Create")
        } else {
            $("#createOrUpdateHotel").text(`Update hotel by ID ${hotelID}`);
        }
    }

    function onCreateHotel(e) {
        e.preventDefault();
        const hotelID = $("#inputHotelID").val();
        const hotelName = $("#inputHotelName").val();
        const placeID = $("#inputPlaceIDForHotel").val();

        const payload = {name: hotelName, place: {id: placeID}};

        let path = '/app/hotel/create';

        if (hotelID.length !== 0) {
            path = `/app/hotel/update/${hotelID}`
        }
        window.wsClient.send(
            path,
            {},
            JSON.stringify(payload)
        );
    }

    function onDeleteHotel(e) {
        e.preventDefault();
        const hotelID = $("#inputHotelDeleteID").val();
        const path = `/app/hotel/delete/${hotelID}`;
        window.wsClient.send(
            path,
            {},
            "",
        );
    }
})();