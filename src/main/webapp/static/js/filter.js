const filter = {
    init: function () {
        this.setEventListenersForFilters();
    },

    setEventListenersForFilters() {

        const categories = document.querySelectorAll("input[type=checkbox][name=category]");
        const suppliers =  document.querySelectorAll("input[type=checkbox][name=supplier]");

        let chosenCategories = []
        categories.forEach(function(checkbox) {
            checkbox.addEventListener('change', function() {
                chosenCategories =
                    Array.from(categories) // Convert checkboxes to an array to use filter and map.
                        .filter(i => i.checked) // Use Array.filter to remove unchecked checkboxes.
                        .map(i => i.value) // Use Array.map to extract only the checkbox values from the array of objects.
                console.log(chosenCategories)
                // get categories and suppliers
                // create queryString
                // fetch product data
                // build new product div with fetched data
            })
        })

        let chosenSuppliers = []
        suppliers.forEach(function(checkbox) {
            checkbox.addEventListener('change', function() {
                chosenSuppliers =
                    Array.from(suppliers) // Convert checkboxes to an array to use filter and map.
                        .filter(i => i.checked) // Use Array.filter to remove unchecked checkboxes.
                        .map(i => i.value) // Use Array.map to extract only the checkbox values from the array of objects.

                console.log(chosenSuppliers)
            })
        })

    }


}

filter.init();