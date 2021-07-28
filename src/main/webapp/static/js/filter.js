const filter = {
    init () {
        this.setEventListenersForFilters();
    },

    setEventListenersForFilters() {

        const categories = document.querySelectorAll("input[type=checkbox][name=category]");
        const suppliers =  document.querySelectorAll("input[type=checkbox][name=supplier]");
        let chosenCategories = []
        let chosenSuppliers = []

        let data = {
            category: chosenCategories,
            supplier: chosenSuppliers
        }

        categories.forEach(function(checkbox) {
            checkbox.addEventListener('change', function() {
                chosenCategories =
                    Array.from(categories) // Convert checkboxes to an array to use filter and map.
                        .filter(i => i.checked) // Use Array.filter to remove unchecked checkboxes.
                        .map(i => i.value) // Use Array.map to extract only the checkbox values from the array of objects.
                // get categories and suppliers
                data.category = chosenCategories
                data.supplier = chosenSuppliers

                // create queryString
                let s = filter.createQueryString(data)
                console.log(s)
                console.log(decodeURIComponent(s))

                // fetch product data
                // build new product div with fetched data

            })
        })

        suppliers.forEach(function(checkbox) {
            checkbox.addEventListener('change', function() {
                chosenSuppliers =
                    Array.from(suppliers) // Convert checkboxes to an array to use filter and map.
                        .filter(i => i.checked) // Use Array.filter to remove unchecked checkboxes.
                        .map(i => i.value) // Use Array.map to extract only the checkbox values from the array of objects.
                // get categories and suppliers
                data.category = chosenCategories
                data.supplier = chosenSuppliers

                // create queryString
                console.log(filter.createQueryString(data))
            })
        })

    },

    createQueryString(obj){
        let esc = encodeURIComponent;
        return Object.keys(obj)
            .map(k => esc(k) + '=' + esc(obj[k]))
            .join('&');
    },




}

filter.init();