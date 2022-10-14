(function () {
    let pagePlace = 0;

    window.addEventListener("load", function (event) {
        $("#searchPlace").on("click", onClickSearch);
        $(".tab-content-place .next").on("click", onClickNext)
        $(".tab-content-place .prev").on("click", onClickPrev)

        $('#inputPlaceID').on("keyup", onChangePlaceID)

        $('#createOrUpdatePlace').on("click", onCreatePlace)
        $('#deletePlace').on("click", onDeletePlace)

        setTimeout(subscribe, 100)
    });

    function onChangePage() {
        loadList()
        if (pagePlace === 0) {
            $(".tab-content-place .prev").addClass("disabled")
        } else {
            $(".tab-content-place .prev").removeClass("disabled")
        }
    }

    window.showPlacesTab = () => {
        $('.tab-content').css("display", "none");
        $('.tab-content-place').css("display", "block");
    }

    function onClickSearch(e) {
        e.preventDefault();
        loadList()
    }

    function loadList() {
        const placeID = $('#inputSearchPlaceID').val();
        const placeName = $('#inputSearchPlaceName').val();
        let path = "/app/place";

        if (placeID.length !== 0) {
            path = `/app/place/id/${placeID}`
        } else if (placeName.length !== 0) {
            path = `/app/place/name/${placeName}`
        }
        window.wsClient.send(
            path,
            {},
            JSON.stringify({page: pagePlace, size: 5})
        );
    }

    function onClickNext(e) {
        e.preventDefault();
        pagePlace += 1;
        onChangePage();
    }

    function onClickPrev(e) {
        e.preventDefault();
        pagePlace = Math.max(pagePlace - 1, 0)
        onChangePage();
    }

    function clearList() {
        $(".tab-content-place .messages").empty()
    }

    function subscribe() {
        const result = window.wsClient.subscribe('/topic/place', function (message) {
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
          <td>Hotels</td>
          <td>Photos</td>
          <td>Region id</td>
        </tr>
  `);
        $(".tab-content-place .messages").append(node);
    }

    function showMessage(item) {
        if (item.isFound === false) {
            alert("Place not found")
            return
        }

        const node = $(`
        <tr data-id="${item.id}">
          <td>${item.id}</td>
          <td>${item.name}</td>
          <td>${item.hotelCount}</td>
          <td>${item.photoCount}</td>
          <td>${item.region.id}</td>
         </tr>
  `);
        $(".tab-content-place .messages").append(node);
    }

    function onChangePlaceID() {
        const placeID = $("#inputPlaceID").val();
        if (placeID.length === 0) {
            $("#createOrUpdatePlace").text("Create")
        } else {
            $("#createOrUpdatePlace").text(`Update place by ID ${placeID}`);
        }
    }

    function onCreatePlace(e) {
        e.preventDefault();
        const placeID = $("#inputPlaceID").val();
        const placeName = $("#inputPlaceName").val();
        const regionID = $("#inputRegionIDForPlace").val();

        const payload = {name: placeName, region: {id: regionID}};

        let path = '/app/place/create';

        if (placeID.length !== 0) {
            path = `/app/place/update/${placeID}`
        }
        window.wsClient.send(
            path,
            {},
            JSON.stringify(payload)
        );
    }

    function onDeletePlace(e) {
        e.preventDefault();
        const placeID = $("#inputPlaceDeleteID").val();
        const path = `/app/place/delete/${placeID}`;
        window.wsClient.send(
            path,
            {},
            "",
        );
    }
})();