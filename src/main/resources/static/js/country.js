(function () {
    let pageCountry = 0;

    window.addEventListener("load", function (event) {
        $("#searchCountry").on("click", onClickSearch);
        $(".tab-content-country .next").on("click", onClickNext)
        $(".tab-content-country .prev").on("click", onClickPrev)

        $('#inputCountryID').on("keyup", onChangeCountryID)

        $('#createOrUpdateCountry').on("click", onCreateCountry)
        $('#deleteCountry').on("click", onDeleteCountry)

        setTimeout(subscribe, 100)
    });

    function onChangePage() {
        loadList()
        if (pageCountry === 0) {
            $(".tab-content-country .prev").addClass("disabled")
        } else {
            $(".tab-content-country .prev").removeClass("disabled")
        }
    }

    window.showCountriesTab = () => {
        $('.tab-content').css("display", "none");
        $('.tab-content-country').css("display", "block");
    }

    function onClickSearch(e) {
        e.preventDefault();
        loadList()
    }

    function loadList() {
        const countryID = $('#inputSearchCountryID').val();
        const countryName = $('#inputSearchCountryName').val();
        let path = "/app/country";

        if (countryID.length !== 0) {
            path = `/app/country/id/${countryID}`
        } else if (countryName.length !== 0) {
            path = `/app/country/name/${countryName}`
        }
        window.wsClient.send(
            path,
            {},
            JSON.stringify({page: pageCountry, size: 5})
        );
    }

    function onClickNext(e) {
        e.preventDefault();
        pageCountry += 1;
        onChangePage();
    }

    function onClickPrev(e) {
        e.preventDefault();
        pageCountry = Math.max(pageCountry - 1, 0)
        onChangePage();
    }

    function clearList() {
        $(".tab-content-country .messages").empty()
    }

    function subscribe() {
        const result = window.wsClient.subscribe('/topic/country', function (message) {
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
          <td>Regions</td>
          <td>Places</td>
          <td>Hotels</td>
        </tr>
  `);
        $(".tab-content-country .messages").append(node);
    }

    function showMessage(item) {
        if (item.isFound === false) {
            alert("Country not found")
            return
        }

        const node = $(`
        <tr data-id="${item.id}">
          <td>${item.id}</td>
          <td>${item.name}</td>
          <td>${item.regionCounter}</td>
          <td>${item.placeCounter}</td>
          <td>${item.hotelCounter}</td>
         </tr>
  `);
        $(".tab-content-country .messages").append(node);
    }

    function onChangeCountryID() {
        const countryID = $("#inputCountryID").val();
        if (countryID.length === 0) {
            $("#createOrUpdateCountry").text("Create")
        } else {
            $("#createOrUpdateCountry").text(`Update country by ID ${countryID}`);
        }
    }

    function onCreateCountry(e) {
        e.preventDefault();
        const countryID = $("#inputCountryID").val();
        const countryName = $("#inputCountryName").val();

        const payload = {name: countryName};

        let path = '/app/country/create';

        if (countryID.length !== 0) {
            path = `/app/country/update/${countryID}`
        }
        window.wsClient.send(
            path,
            {},
            JSON.stringify(payload)
        );
    }

    function onDeleteCountry(e) {
        e.preventDefault();
        const countryID = $("#inputCountryDeleteID").val();
        const path = `/app/country/delete/${countryID}`;
        window.wsClient.send(
            path,
            {},
            "",
        );
    }
})();