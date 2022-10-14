(function () {
    let pageRegion = 0;

    window.addEventListener("load", function (event) {
        $("#searchRegion").on("click", onClickSearch);
        $(".tab-content-region .next").on("click", onClickNext)
        $(".tab-content-region .prev").on("click", onClickPrev)

        $('#inputRegionID').on("keyup", onChangeRegionID)

        $('#createOrUpdateRegion').on("click", onCreateRegion)
        $('#deleteRegion').on("click", onDeleteRegion)

        setTimeout(subscribe, 100)
    });

    function onChangePage() {
        loadList()
        if (pageRegion === 0) {
            $(".tab-content-region .prev").addClass("disabled")
        } else {
            $(".tab-content-region .prev").removeClass("disabled")
        }
    }

    window.showRegionsTab = () => {
        subscribe()
        $('.tab-content').css("display", "none");
        $('.tab-content-region').css("display", "block");
    }

    function onClickSearch(e) {
        e.preventDefault();
        loadList()
    }

    function loadList() {
        const regionID = $('#inputSearchRegionID').val();
        const regionName = $('#inputSearchRegionName').val();
        let path = "/app/region";

        if (regionID.length !== 0) {
            path = `/app/region/id/${regionID}`
        } else if (regionName.length !== 0) {
            path = `/app/region/name/${regionName}`
        }
        window.wsClient.send(
            path,
            {},
            JSON.stringify({page: pageRegion, size: 5})
        );
    }

    function onClickNext(e) {
        e.preventDefault();
        pageRegion += 1;
        onChangePage();
    }

    function onClickPrev(e) {
        e.preventDefault();
        pageRegion = Math.max(pageRegion - 1, 0)
        onChangePage();
    }

    function clearList() {
        $(".tab-content-region .messages").empty()
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
          <td>Places</td>
          <td>Hotels</td>
          <td>Country id</td>
        </tr>
  `);
        $(".tab-content-region .messages").append(node);
    }

    function showMessage(item) {
        if (item.isFound === false) {
            alert("Region not found")
            return
        }

        const node = $(`
        <tr data-id="${item.id}">
          <td>${item.id}</td>
          <td>${item.name}</td>
          <td>${item.placeCount}</td>
          <td>${item.hotelCount}</td>
          <td>${item.country.id}</td>
         </tr>
  `);
        $(".tab-content-region .messages").append(node);
    }

    function onChangeRegionID() {
        const regionID = $("#inputRegionID").val();
        if (regionID.length === 0) {
            $("#createOrUpdateRegion").text("Create")
        } else {
            $("#createOrUpdateRegion").text(`Update region by ID ${regionID}`);
        }
    }

    function onCreateRegion(e) {
        e.preventDefault();
        const regionID = $("#inputRegionID").val();
        const regionName = $("#inputRegionName").val();
        const countryID = $("#inputCountryIDForRegion").val();

        const payload = {name: regionName, country: {id: countryID}};

        let path = '/app/region/create';

        if (regionID.length !== 0) {
            path = `/app/region/update/${regionID}`
        }
        window.wsClient.send(
            path,
            {},
            JSON.stringify(payload)
        );
    }

    function onDeleteRegion(e) {
        e.preventDefault();
        const regionID = $("#inputRegionDeleteID").val();
        const path = `/app/region/delete/${regionID}`;
        window.wsClient.send(
            path,
            {},
            "",
        );
    }
})();